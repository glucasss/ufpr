package com.example.matematicadivertida;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Contagem extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    ImageView imageView;
    int result;
    int respostas[] = {0,0,0,0,0};
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contagem);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        imageView = findViewById(R.id.imageView2);
        Integer numeros[][] = {{1,R.drawable.um},
                {2,R.drawable.dois},
                {3,R.drawable.tres},
                {4,R.drawable.num4},
                {5,R.drawable.num5},
                {6,R.drawable.num6},
                {7,R.drawable.num7},
                {8,R.drawable.num8},
                {9,R.drawable.num9}};
        Random gerador = new Random();
        int  num[] = {100,100,100};
        Integer i;
        for(int x=0; x<3; x++){
            i = gerador.nextInt(9);
            if(i==num[0] || i==num[1]){
                --x;}
            else{
                num[x]=i;}
        }
        respostas = getIntent().getExtras().getIntArray("array");
        cont = getIntent().getExtras().getInt("cont");
        System.out.println("Contador");
        System.out.println(cont);
        result = gerador.nextInt(9);
        for(int w=0;w<5;w++){
            if(respostas[w] == result){
                w=-1;
                result = gerador.nextInt(9);
            }
            if(num[0] != result && num[1] != result && num[2] != result){
                w=-1;
                result = gerador.nextInt(9);
            }

            System.out.println("Comparar");
            System.out.println(result);
            System.out.println(respostas[0]);
            System.out.println(respostas[1]);
            System.out.println(respostas[2]);
            System.out.println(respostas[3]);
        }
        b1.setText(String.valueOf(num[0]+1));
        b2.setText(String.valueOf(num[1]+1));
        b3.setText(String.valueOf(num[2]+1));
        imageView.setImageDrawable(getResources().getDrawable(numeros[result][1]));
    }

    public void resultado(final int resposta){
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(resposta == result+1) {
            builder.setTitle("Resultado");
            builder.setMessage("Você acertou");
            builder.setPositiveButton("Avançar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Bundle bundle = new Bundle();
                    respostas[cont]=result;
                    bundle.putIntArray("array", respostas);
                    cont++;
                    bundle.putInt("cont", cont);
                    Intent it = new Intent(Contagem.this, Contagem.class);
                    it.putExtras(bundle);
                    it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(it);
                    finish();
                }
            });
        }else{
            builder.setTitle("Resultado");
            builder.setMessage("Você errou. Resposta certa: " + result);
            builder.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(Contagem.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();

                }
            });
        }
        alerta = builder.create();
        alerta.show();
    }

    public void res1(View view){
        String buttonText = b1.getText().toString();
        int resposta = Integer.parseInt(buttonText);
        resultado(resposta);
    }

    public void res2(View view){
        String buttonText = b2.getText().toString();
        int resposta = Integer.parseInt(buttonText);
        resultado(resposta);
    }

    public void res3(View view){
        String buttonText = b3.getText().toString();
        int resposta = Integer.parseInt(buttonText);
        resultado(resposta);
    }

}