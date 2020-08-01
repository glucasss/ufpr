package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.oscarapp.DB.DBOperations;
import com.example.oscarapp.FilmeVO.DiretorVO;
import com.example.oscarapp.utils.CustomJSONObjectRequest;
import com.example.oscarapp.utils.CustomVolleyRequestQueue;
import com.example.oscarapp.utils.Links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VotarDiretor extends AppCompatActivity implements Response.Listener, Response.ErrorListener{

    RadioGroup rgpDiretores;
    RequestQueue mQueue;
    List<DiretorVO> listDiretorVO;
    DBOperations diretorOperations;

    public static final String REQUEST_TAG = "MainVolleyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_diretor);

        rgpDiretores = (RadioGroup) findViewById(R.id.rgpDiretores);
        diretorOperations = new DBOperations(this);
        diretorOperations.open();

    }

    @Override
    protected void onStart(){
        super.onStart();

        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET,
                Links.DIRETORES_URL, new JSONObject(), VotarDiretor.this, VotarDiretor.this);
        jsonRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonRequest);

    }

    @Override
    protected void onResume(){
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

        JSONArray diretores = new JSONArray();
        listDiretorVO = new ArrayList<DiretorVO>();
        DiretorVO diretorVO;

        try{
            diretores = ((JSONObject)response).getJSONArray("diretor");
        } catch(JSONException e){
            e.printStackTrace();
        }

        for(int i=0; i<diretores.length(); i++){
            try{
                diretorVO = new DiretorVO();
                diretorVO.setNome(diretores.getJSONObject(i).getString("nome"));
                diretorVO.setId(diretores.getJSONObject(i).getInt("id"));
                listDiretorVO.add(diretorVO);
            } catch(JSONException e){
                e.printStackTrace();
            }
        }

        for(DiretorVO item : listDiretorVO){
            RadioButton rbn = new RadioButton(this);
            rbn.setId(View.generateViewId());
            rbn.setText(item.getNome());
            rbn.setTextSize(30);
            rgpDiretores.addView(rbn);
        }

        rgpDiretores.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                List<DiretorVO> listVO = new ArrayList<DiretorVO>();
                listVO = diretorOperations.getAllDiretor();

                for(DiretorVO item : listVO){
                    diretorOperations.deleteDiretor(item);
                }

                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(i);

                DiretorVO d = new DiretorVO();
                d.setId(checkedRadioButton.getId());
                d.setNome(checkedRadioButton.getText().toString());

                DiretorVO res = diretorOperations.addDiretor(d);

                Toast.makeText(VotarDiretor.this, "Votou " +checkedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
