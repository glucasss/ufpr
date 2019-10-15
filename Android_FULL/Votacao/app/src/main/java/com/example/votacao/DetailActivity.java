package com.example.votacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailActivity extends Activity {

    String[] candidatos = new String[]{"Albert Einstein", "Nikola Tesla", "Alan Turing"};
    String[] partidos = new String[]{"Partido dos Físicos", "Partido Energético", "Partido da Computação"};
    Integer[] imageId = {R.drawable.img1, R.drawable.img2, R.drawable.img3};

    int candidatoId = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        EditText candidatosNome = (EditText) findViewById(R.id.candidatoName);
        EditText details = (EditText) findViewById(R.id.details);
        ImageView img = findViewById(R.id.imageView);
        Button votar = findViewById(R.id.votar);
        Intent it = getIntent();

        if(it.getExtras().getBoolean("voto")){
            votar.setEnabled(false);
        }

        if(it!=null)
            candidatoId = it.getIntExtra("candidatoId", 3);

        if(candidatoId == 3){
            candidatosNome.setText("");
            details.setText("");
            Toast.makeText(this, "Candidato não encontrado", Toast.LENGTH_SHORT).show();
        }else{
            candidatosNome.setText(candidatos[candidatoId]);
            details.setText(partidos[candidatoId]);
            img.setImageResource(imageId[candidatoId]);
        }
    }

    public void votar(View view){
        Button votar = findViewById(R.id.votar);
        Toast.makeText(this, "Votou em: " + candidatos[candidatoId], Toast.LENGTH_LONG).show();
        votar.setEnabled(false);
    }
}
