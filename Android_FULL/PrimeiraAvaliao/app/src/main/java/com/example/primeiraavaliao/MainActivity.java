package com.example.primeiraavaliao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcula(View view){
        EditText nota1 = findViewById(R.id.nota1);
        EditText nota2 = findViewById(R.id.nota2);
        EditText freq = findViewById(R.id.freq);
        TextView notaFinal = findViewById(R.id.notaFinal);
        TextView situacao = findViewById(R.id.situacao);
        //EditText inserirNome = (EditText)findViewById(R.id.inserirNome);
        String resultado;
        String sit;
        //String nome = inserirNome.getText().toString();
        float num1 = Float.parseFloat(nota1.getText().toString());
        float num2 = Float.parseFloat(nota2.getText().toString());
        int frequencia = Integer.parseInt(freq.getText().toString());

        float media = (num1 + num2) / 2;
        resultado = ""+media;

        if(media >= 7 && frequencia >= 75){
            sit = "Aluno aprovado!";
        }else {
            if (media >= 4 && media < 7 && frequencia >= 75) {
                sit = "Aluno em Exame Final!";
            } else {
                if (media < 4) {
                    sit = "Aluno reprovado por nota!";
                } else {
                    sit = "Aluno reprovado por frequência!";
                }
            }
        }
        notaFinal.setText(resultado);
        situacao.setText(sit);
    }
}
