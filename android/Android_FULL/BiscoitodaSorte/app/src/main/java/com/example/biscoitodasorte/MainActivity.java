package com.example.biscoitodasorte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String frases[]= {
            "A vida trará coisas boas se tiveres paciência.",
            "Demonstre amor e alegria em todas as oportunidades e verás que a paz nasce dentro de você.",
            "Não compense na ira o que lhe falta na razão.",
            "Defeitos e virtudes são apenas dois lados da mesma moeda.",
            "A maior de todas as torres começa no solo.",
            "Não há que ser forte. Há que ser flexível.",
            "Gente todo dia arruma os cabelos, por que não o coração?",
            "Há três coisas que jamais voltam; a flecha lançada, a palavra dita e a oportunidade perdida.",
            "A juventude não é uma época da vida, é um estado de espírito.",
            "Podemos escolher o que semear, mas somos obrigados a colher o que plantamos."
    };
    Random r;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();
    }

    public void pedir(View view){
        TextView output = (TextView)findViewById(R.id.frase);
        num = r.nextInt(10);
        String tip;
        tip = frases[num];
        output.setText(tip);
    }
}
