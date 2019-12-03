package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarapp.DB.DBOperations;
import com.example.oscarapp.FilmeVO.FilmeVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Detalhe extends AppCompatActivity {

    ImageView imgFilmeDetalhe;
    TextView lblNomeFilmeDetalhe;
    TextView lblGeneroFilmeDetalhe;
    int idFilme;
    Button btnVotar;
    String fotoUrl;

    DBOperations filmeOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent it = getIntent();
        Bundle params = it.getExtras();

        filmeOperations = new DBOperations(this);
        filmeOperations.open();

        fotoUrl = params.getString("foto");

        imgFilmeDetalhe = (ImageView) findViewById(R.id.imgFilmeDetalhe);
        lblNomeFilmeDetalhe = (TextView) findViewById(R.id.lblNomeFilmeDetalhe);
        lblGeneroFilmeDetalhe = (TextView) findViewById(R.id.lblGeneroFilmeDetalhe);
        btnVotar = (Button) findViewById(R.id.btnVotar);

        lblNomeFilmeDetalhe.setText(params.getString("nome"));
        lblGeneroFilmeDetalhe.setText(params.getString("genero"));
        idFilme = params.getInt("id");

        Picasso.with(Detalhe.this)
                .load(fotoUrl)
                .into(imgFilmeDetalhe);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void votar(View view){
        List<FilmeVO> listVO = new ArrayList<>();
        listVO = filmeOperations.getAllFilmes();

        for(FilmeVO item : listVO){
            filmeOperations.deleteFilme(item);
        }

        FilmeVO r = new FilmeVO();
        r.setId(idFilme);
        r.setNome(lblNomeFilmeDetalhe.getText().toString());
        r.setGenero(lblGeneroFilmeDetalhe.getText().toString());
        r.setFoto(fotoUrl);

        FilmeVO res = filmeOperations.addFilme(r);

        Toast.makeText(Detalhe.this, "Você votou no filme " + res.getNome(), Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onResume(){
        filmeOperations.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        filmeOperations.close();
        super.onPause();
    }

}
