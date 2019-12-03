package com.example.oscarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.example.oscarapp.DB.DBOperations;
import com.example.oscarapp.VO.VotedVO;
import com.example.oscarapp.VO.VotoDiretorVO;
import com.example.oscarapp.VO.VotoFilmeVO;
import com.example.oscarapp.dto.VotoDTO;
import com.example.oscarapp.utils.CustomJSONObjectRequest;
import com.example.oscarapp.utils.CustomVolleyRequestQueue;
import com.example.oscarapp.utils.ErrorHandle;
import com.example.oscarapp.utils.Links;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarVoto extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    TextView lblFilme;
    TextView lblDiretor;
    EditText iptToken;
    Button btnConfirmar;
    AlertDialog.Builder alert;
    RequestQueue mQueue;
    Bundle params;

    DBOperations dbOperations;

    public static final String REQUEST_TAG = "MainVolleyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_voto);

        dbOperations = new DBOperations(ConfirmarVoto.this);
        dbOperations.open();

        params = getIntent().getExtras();
        lblFilme = (TextView)findViewById(R.id.lblFilme);
        lblDiretor = (TextView)findViewById(R.id.lblDiretor);
        iptToken = (EditText)findViewById(R.id.iptToken);
        btnConfirmar = (Button)findViewById(R.id.btnConfirmar);

        lblFilme.setText(params.getString("nomeFilme"));
        lblDiretor.setText(params.getString("nomeDiretor"));

    }

    @Override
    protected void onStart(){
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        btnConfirmar.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {

               if(iptToken.getText().toString().length() > 0){
                   alert = new AlertDialog.Builder(ConfirmarVoto.this);
                   alert.setCancelable(false);
                   alert.setTitle("Voto");
                   alert.setMessage("Deseja confirmar o voto?");
                   alert.setPositiveButton("Sim",
                           new DialogInterface.OnClickListener() {
                                @Override
                               public void onClick(DialogInterface dialog, int which){
                                    VotoDTO votoDTO = new VotoDTO();
                                    votoDTO.setVotoDiretorVO(new VotoDiretorVO());
                                    votoDTO.setVotoFilmeVO(new VotoFilmeVO());
                                    votoDTO.setToken(Integer.parseInt(iptToken.getText().toString()));
                                    votoDTO.getVotoDiretorVO().setNome(params.getString("nomeDiretor"));
                                    votoDTO.getVotoFilmeVO().setNome(params.getString("nomeFilme"));
                                    Gson gson = new GsonBuilder().create();
                                    String json = gson.toJson(votoDTO);
                                    JSONObject jsonObject = new JSONObject();
                                    try{
                                        jsonObject = new JSONObject(json);
                                    } catch(JSONException e){
                                        e.printStackTrace();
                                    }

                                    CustomJSONObjectRequest jsonRequest =
                                            new CustomJSONObjectRequest(Request.Method.POST,
                                                    Links.BASE_URL+Links.USER+Links.VOTE, jsonObject,
                                                    ConfirmarVoto.this, ConfirmarVoto.this);
                                    jsonRequest.setTag(REQUEST_TAG);

                                    mQueue.add(jsonRequest);
                                }
                           });
                   alert.setNegativeButton("Cancelar",
                           new DialogInterface.OnClickListener(){
                            @Override
                               public void onClick(DialogInterface dialog, int which){
                                finish();
                            }
                           });
                   AlertDialog dialog = alert.create();
                   dialog.show();
               }else {
                   Toast.makeText(ConfirmarVoto.this, "Forneça o token!", Toast.LENGTH_SHORT).show();
               }

           }
       });

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if(error instanceof ServerError)
            Toast.makeText(ConfirmarVoto.this, ErrorHandle.parseVolleyError(error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(Object response) {

        /*
        Salva o voto na base
         */
        List<VotedVO> votedVOList = new ArrayList<VotedVO>();
        votedVOList = dbOperations.getAllVoted();

        if(!votedVOList.isEmpty()) {
            for (VotedVO item : votedVOList) {
                dbOperations.deleteVoted(item);
            }
        }

        VotedVO k = new VotedVO();
        k.setId(1);
        k.setVoted("VOTED");
        dbOperations.addVoted(k);

        Toast.makeText(ConfirmarVoto.this, "Voto registrado com sucesso!", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(ConfirmarVoto.this, ResultadoVoto.class);
        Bundle paramsx = new Bundle();
        paramsx.putString("tela", "final");
        paramsx.putString("nomeFilme", params.getString("nomeFilme"));
        paramsx.putString("nomeDiretor", params.getString("nomeDiretor"));
        it.putExtras(paramsx);
        startActivity(it);
        finish();
    }
}
