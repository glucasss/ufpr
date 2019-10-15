package com.example.calcimc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Tela1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcula(View view){
        EditText nome1 = findViewById(R.id.nome);
        EditText peso1 = findViewById(R.id.peso);
        EditText altura1 = findViewById(R.id.altura);
        String nome = nome1.getText().toString();
        float peso = Float.parseFloat(peso1.getText().toString());
        float altura = Float.parseFloat(altura1.getText().toString());
        float imc = peso/(altura*altura);
        Intent it = new Intent(this, Tela2.class);
        Bundle params = new Bundle();
        params.putString("nome", nome);
        params.putFloat("imc", imc);
        it.putExtras(params);
        startActivity(it);
        finish();
    }
}
