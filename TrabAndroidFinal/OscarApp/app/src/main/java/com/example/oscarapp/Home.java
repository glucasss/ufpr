package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarapp.DB.DBOperations;
import com.example.oscarapp.FilmeVO.DiretorVO;
import com.example.oscarapp.FilmeVO.FilmeVO;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ImageView imgOscar;
    TextView lblToken;
    TextView lblVotarFilme;
    TextView lblVotarDiretor;
    TextView lblSair;
    TextView lblConfirmarVoto;
    Intent in;
    Bundle params;

    DBOperations dbOperations;

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

        dbOperations = new DBOperations(this);
        dbOperations.open();

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

    public void confirmarVoto(View view){

        /*
         *  Pega informações da base local e passa pra Activity
         * de confirmar voto.
         */
        Bundle params = new Bundle();
        List<FilmeVO> listVO = new ArrayList<>();
        listVO = dbOperations.getAllFilmes();
        List<DiretorVO> listVOD = new ArrayList<DiretorVO>();
        listVOD = dbOperations.getAllDiretor();

        if(validaVotos(listVO, listVOD)){
            DiretorVO diretor = listVOD.get(0);
            FilmeVO filme = listVO.get(0);
            params.putString("nomeFilme", filme.getNome());
            params.putString("nomeDiretor", diretor.getNome());
            Intent it = new Intent(Home.this, ConfirmarVoto.class);
            it.putExtras(params);
            startActivity(it);
        }

    }

    private boolean validaVotos(List<FilmeVO> filme, List<DiretorVO> diretor){
        if(filme.isEmpty() && diretor.isEmpty()){
            Toast.makeText(Home.this, "Vote no diretor e filme!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(filme.isEmpty()){
            Toast.makeText(Home.this, "Vote em um filme!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(diretor.isEmpty()){
            Toast.makeText(Home.this, "Vote em um diretor!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

}
