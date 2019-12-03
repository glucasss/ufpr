package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.oscarapp.utils.CustomJSONObjectRequest;
import com.example.oscarapp.utils.CustomVolleyRequestQueue;
import com.example.oscarapp.utils.Links;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultadoVoto extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    RequestQueue mQueue;
    TextView lblFilmeVotado;
    TextView lblDiretorVotado;
    String login;

    public static final String REQUEST_TAG = "MainVolleyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_voto);

        lblFilmeVotado = (TextView)findViewById(R.id.lblFilmeVotado);
        lblDiretorVotado = (TextView)findViewById(R.id.lblDiretorVotado);

        Intent it = getIntent();
        if(it != null){
            Bundle params = it.getExtras();
            if(params != null){
                String tela = params.getString("tela");
                if(tela.equals("Main")){
                    login = params.getString("login");
                }else{
                    lblFilmeVotado.setText(params.getString("nomeFilme"));
                    lblDiretorVotado.setText(params.getString("nomeDiretor"));
                }
            }
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        JSONObject loginJson = new JSONObject();
        try {
            loginJson.put("login", login);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonRequest =
                new CustomJSONObjectRequest(Request.Method.POST,
                        Links.BASE_URL+Links.USER+Links.GETVOTE, loginJson,
                        ResultadoVoto.this, ResultadoVoto.this);
        jsonRequest.setTag(REQUEST_TAG);

        mQueue.add(jsonRequest);

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        try {
            lblFilmeVotado.setText(((JSONObject) response).getString("nomeFilme"));
            lblDiretorVotado.setText(((JSONObject) response).getString("nomeDiretor"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
