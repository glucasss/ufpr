package com.example.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovaEdicaoActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtFone;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_edicao);
        edtNome = findViewById(R.id.edtNome);
        edtFone = findViewById(R.id.edtFone);

        Intent intent = getIntent();
        if (intent != null){
            contato = (Contato)intent.getSerializableExtra("contato");
            if(contato != null){
                edtNome.setText(contato.nome);
                edtFone.setText(contato.fone);
            }
        }
    }

    public void salvar(View v){
        BancoDeDados db = new BancoDeDados(this);
        db.abrir();
        if(contato != null){
            db.atualizaContato(contato.id, edtNome.getText().toString(),
            edtFone.getText().toString());
        } else{
            db.insereContato(edtNome.getText().toString(), edtFone.getText().toString());
        }
        db.fechar();
        setResult(MainActivity.REQUEST_SALVOU);
        finish();
    }
}
