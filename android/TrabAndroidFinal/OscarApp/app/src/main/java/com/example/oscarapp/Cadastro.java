package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.oscarapp.utils.CustomJSONObjectRequest;
import com.example.oscarapp.utils.CustomVolleyRequestQueue;
import com.example.oscarapp.utils.ErrorHandle;
import com.example.oscarapp.utils.Links;

import org.json.JSONException;
import org.json.JSONObject;

public class Cadastro extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    public static final String REQUEST_TAG = "MainVolleyActivity";

    EditText iptSenha;
    EditText iptLogin;
    Button btnEnviar;
    TextView lblMsg;

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iptSenha = (EditText)findViewById(R.id.iptSenha);
        iptLogin = (EditText)findViewById(R.id.iptLogin);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        lblMsg = (TextView)findViewById(R.id.lblMessage);

    }

    @Override
    protected void onStart(){
        super.onStart();

        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        btnEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String msgLogin = loginMsg(iptLogin.getText().toString(), iptSenha.getText().toString());

                if(!msgLogin.isEmpty() || msgLogin == null){
                    System.out.println("msg:" + msgLogin);
                    lblMsg.setText(msgLogin);
                }else{
                    lblMsg.setText("");
                    JSONObject newUserJson = new JSONObject();
                    try{
                        newUserJson.put("login", iptLogin.getText().toString());
                        newUserJson.put("senha", iptSenha.getText().toString());
                        newUserJson.put("voto", "");
                    } catch(JSONException e){
                        e.printStackTrace();
                    }

                    CustomJSONObjectRequest jsonRequest =
                            new CustomJSONObjectRequest(Request.Method.POST,
                            Links.BASE_URL+Links.USER+Links.SAVE, newUserJson,
                                Cadastro.this, Cadastro.this);

                    mQueue.add(jsonRequest);
                }
            }
        });

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
        error.printStackTrace();
        if(error instanceof ServerError && error != null) {
            lblMsg.setTextColor(Color.RED);
            lblMsg.setText(ErrorHandle.parseVolleyError(error));
        }
    }

    @Override
    public void onResponse(Object response) {
        Toast.makeText(Cadastro.this, "Usuário cadastrado!\nVoltando a tela de login", Toast.LENGTH_LONG).show();
        finish();
    }

    public String loginMsg(String login, String senha){
        StringBuilder sb = new StringBuilder();

        if((login.isEmpty() || login == null) && (senha.isEmpty() || senha == null)){
            sb.append("Campos vazios!");
        } else if(login.isEmpty() || login == null){
            sb.append("Login vazio!");
        } else if(senha.isEmpty() || senha == null){
            sb.append("Senha não informada!");
        }

        return sb.toString();

    }
}
