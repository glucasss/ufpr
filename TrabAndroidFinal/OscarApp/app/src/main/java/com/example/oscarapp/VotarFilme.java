package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.oscarapp.FilmeVO.FilmeVO;
import com.example.oscarapp.utils.CustomJSONObjectRequest;
import com.example.oscarapp.utils.CustomVolleyRequestQueue;
import com.example.oscarapp.utils.Links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VotarFilme extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    ListView list;
    List<FilmeVO> listFilmeVO;
    FilmeVO[] arrayFilmeVO;
    ProgressDialog pd;

    public static final String REQUEST_TAG = "MainVolleyActivity";
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_filme);

        pd = new ProgressDialog(this);

    }

    @Override
    protected void onStart(){
        super.onStart();

        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET,
                Links.FILMES_URL, new JSONObject(), VotarFilme.this, VotarFilme.this);
        mQueue.add(jsonRequest);

        progressDialog(pd);

    }

    @Override
    protected void onResume(){

        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET,
                Links.FILMES_URL, new JSONObject(), VotarFilme.this, VotarFilme.this);
        jsonRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonRequest);

        progressDialog(pd);

        super.onResume();

    }

    @Override
    protected void onStop(){
        super.onStop();
        if(mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

        JSONArray filmes = new JSONArray();

        listFilmeVO = new ArrayList<FilmeVO>();
        FilmeVO filmeVO;

        try {
            filmes = ((JSONObject)response).getJSONArray("filme");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0; i < filmes.length(); i++){
            try {
                filmeVO = new FilmeVO();
                filmeVO.setNome(filmes.getJSONObject(i).getString("nome"));
                filmeVO.setId(filmes.getJSONObject(i).getInt("id"));
                filmeVO.setGenero(filmes.getJSONObject(i).getString("genero"));
                filmeVO.setFoto(filmes.getJSONObject(i).getString("foto"));
                listFilmeVO.add(filmeVO);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //converte list em array
        arrayFilmeVO = new FilmeVO[listFilmeVO.size()];
        arrayFilmeVO = listFilmeVO.toArray(arrayFilmeVO);

        list=(ListView) findViewById(R.id.list);
        ListCell adapter = new ListCell(VotarFilme.this, arrayFilmeVO);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent it = new Intent(VotarFilme.this, Detalhe.class);
                Bundle params = new Bundle();
                params.putInt("id", arrayFilmeVO[+arg2].getId());
                params.putString("nome", arrayFilmeVO[+arg2].getNome());
                params.putString("genero", arrayFilmeVO[+arg2].getGenero());
                params.putString("foto", arrayFilmeVO[+arg2].getFoto());
                it.putExtras(params);
                startActivity(it);
            }
        });

        pd.dismiss();

    }

    public void progressDialog(ProgressDialog p1){
        p1.setMessage("Buscando filmes\nAguarde");
        p1.setIndeterminate(false);
        p1.setCanceledOnTouchOutside(false);
        p1.setCancelable(false);
        p1.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
