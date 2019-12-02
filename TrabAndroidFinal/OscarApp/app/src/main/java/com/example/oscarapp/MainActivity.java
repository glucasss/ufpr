package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener{

    public static final String REQUEST_TAG = "MainVolleyActivity";

    TextView lblLogin;
    TextView lblSenha;
    TextView lblCadastrar;
    TextView lblMessage;
    EditText iptLogin;
    EditText iptSenha;
    Button btnEnviar;

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblLogin = (TextView)findViewById(R.id.lblLogin);
        lblSenha = (TextView)findViewById(R.id.lblSenha);
        lblCadastrar = (TextView)findViewById(R.id.lblCadastrar);
        lblMessage = (TextView)findViewById(R.id.lblMessage);
        iptLogin = (EditText)findViewById(R.id.iptLogin);
        iptSenha = (EditText)findViewById(R.id.iptSenha);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);

    }

    @Override
    protected void onStart(){
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        btnEnviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String msgLogin = loginMsg(iptLogin.getText().toString(), iptSenha.getText().toString());

                if(!msgLogin.isEmpty() || msgLogin == null){
                    System.out.println("msg:" + msgLogin);
                    lblMessage.setText(msgLogin);
                }else{
                    lblMessage.setText("");
                    JSONObject loginJson = new JSONObject();
                    try {
                        loginJson.put("login", iptLogin.getText().toString());
                        loginJson.put("senha", iptSenha.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    CustomJSONObjectRequest jsonRequest =
                            new CustomJSONObjectRequest(Request.Method.POST,
                            Links.BASE_URL+Links.USER+Links.LOGIN, loginJson,
                        MainActivity.this, MainActivity.this);

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
    public void onErrorResponse(VolleyError error){
        if(error instanceof ServerError)
            lblMessage.setText(ErrorHandle.parseVolleyError(error));
    }

    @Override
    public void onResponse(Object response){
        String login = "";
        String senha = "";
        String voto = "";
        int token = 0;

        Intent it;
        Bundle params = new Bundle();

        try {
            login = ((JSONObject) response).getString("login");
            senha = ((JSONObject) response).getString("senha");
            voto = ((JSONObject) response).getString("voto");
            token = ((JSONObject) response).getInt("token");

            if(voto.equals("ok")){
                it = new Intent(MainActivity.this, ResultadoVoto.class);
                startActivity(it);
                finish();
            }else{
                params.putInt("token", token);
                it = new Intent(MainActivity.this, Home.class);
                it.putExtras(params);
                startActivity(it);
                finish();
            }

        } catch(JSONException e){
            e.printStackTrace();
        }
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

    public void cadastra(View view){
        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);
    }

}