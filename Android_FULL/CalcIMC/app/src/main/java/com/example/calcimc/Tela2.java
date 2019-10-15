package com.example.calcimc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Tela2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        TextView nome1 = findViewById(R.id.nome);
        TextView imc1 = findViewById(R.id.imc);
        TextView situacao1 = findViewById(R.id.situacao);
        Intent it = getIntent();
        String nome = null;
        float imc = 0;
        if(it != null){
            Bundle params = it.getExtras();
            if(params != null){
                nome = params.getString("nome");
                imc = params.getFloat("imc");
            }
        }
        String situacao = null;
        nome1.setText(nome);
        imc1.setText(Float.toString(imc));
        if(imc < 15)
           situacao = "Extremamente abaixo do peso";
        if(imc >=15 && imc <= 16)
            situacao = "Gravemente abaixo do peso";
        if(imc > 16 && imc <= 18.5)
            situacao = "Abaixo do peso ideal";
        if(imc > 18.5 && imc <= 25)
            situacao = "Faixa de peso ideal";
        if(imc > 25 && imc <= 30)
            situacao = "Sobrepeso";
        if(imc > 30 && imc <= 35)
            situacao = "Obesidade grau I";
        if(imc > 35 && imc <= 40)
            situacao = "Obesidade grau II (grave)";
        if(imc > 40)
            situacao = "Obesidade grau III (mórbida)";
        situacao1.setText(situacao);
    }

}
