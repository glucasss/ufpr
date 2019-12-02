package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    ImageView imgOscar;
    TextView lblToken;
    TextView lblVotarFilme;
    TextView lblVotarDiretor;
    TextView lblSair;
    TextView lblConfirmarVoto;
    Intent in;
    Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        in = getIntent();
        params = in.getExtras();

        //INICIALIZA COMPONENTES
        lblToken = (TextView) findViewById(R.id.lblToken);
        lblVotarFilme = (TextView) findViewById(R.id.lblVotarFilme);
        lblVotarDiretor = (TextView) findViewById(R.id.lblVotarDiretor);
        lblSair = (TextView) findViewById(R.id.lblSair);
        lblConfirmarVoto = (TextView) findViewById(R.id.lblConfirmarVoto);

        imgOscar = (ImageView)findViewById(R.id.imgOscar);
        imgOscar.setImageResource(R.drawable.oscar);
        lblToken.setText("Token para votação: " + params.getInt("token"));

    }

    public void sair(View view){
        Intent it = new Intent(Home.this, MainActivity.class);
        startActivity(it);
        Toast.makeText(Home.this, "Deslogado com sucesso!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void votarFilme(View view){
        Intent it = new Intent(Home.this, VotarFilme.class);
        startActivity(it);
    }

    public void votarDiretor(View view){
        Intent it = new Intent(Home.this, VotarDiretor.class);
        startActivity(it);
    }

}
