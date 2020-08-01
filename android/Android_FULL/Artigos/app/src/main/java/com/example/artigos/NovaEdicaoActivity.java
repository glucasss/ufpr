package com.example.artigos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovaEdicaoActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtRevista;
    private Artigo artigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_edicao);
        edtNome = findViewById(R.id.edtNome);
        edtRevista = findViewById(R.id.edtRevista);

        Intent intent = getIntent();
        if(intent != null){
            artigo = (Artigo)intent.getSerializableExtra("artigo");
            if(artigo != null){
                edtNome.setText(artigo.nome);
                edtRevista.setText(artigo.revista);
            }
        }
    }

    public void salvar(View v){
        BancoDeDados db = new BancoDeDados(this);
        db.abrir();
        if (artigo != null){
            db.atualizaArtigo(artigo.id, edtNome.getText().toString(), edtRevista.getText().toString());
        }else{
            db.insereArtigo(edtNome.getText().toString(), edtRevista.getText().toString());
        }
        db.fechar();
        setResult(MainActivity.REQUEST_SALVOU);
        finish();
    }
}
