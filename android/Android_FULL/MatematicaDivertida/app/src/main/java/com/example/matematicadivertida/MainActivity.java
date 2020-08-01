package com.example.matematicadivertida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button app1;
    Button app2;
    Button app3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app1 = findViewById(R.id.app1);
        app2 = findViewById(R.id.app2);
        app3 = findViewById(R.id.app3);
    }

    public void contagem(View view){
        Bundle bundle = new Bundle();
        int respostas[]={0,0,0,0,0};
        bundle.putIntArray("array", respostas);
        bundle.putInt("cont", 0);
        Intent intent = new Intent(this, Contagem.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
