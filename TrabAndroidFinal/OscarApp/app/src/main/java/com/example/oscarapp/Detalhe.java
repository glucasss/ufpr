package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Detalhe extends AppCompatActivity {

    ImageView imgFilmeDetalhe;
    TextView lblNomeFilmeDetalhe;
    TextView lblGeneroFilmeDetalhe;
    Button btnVotar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent it = getIntent();
        Bundle params = it.getExtras();

        imgFilmeDetalhe = (ImageView) findViewById(R.id.imgFilmeDetalhe);
        lblNomeFilmeDetalhe = (TextView) findViewById(R.id.lblNomeFilmeDetalhe);
        lblGeneroFilmeDetalhe = (TextView) findViewById(R.id.lblGeneroFilmeDetalhe);
        btnVotar = (Button) findViewById(R.id.btnVotar);

        lblNomeFilmeDetalhe.setText(params.getString("nome"));
        lblGeneroFilmeDetalhe.setText(params.getString("genero"));
        try {
            imgFilmeDetalhe.setImageDrawable(getResource(params.getString("foto")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public Drawable getResource(String url) throws MalformedURLException, IOException {
        return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
    }
}
