package com.example.szalu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sum (View view ){
        EditText n1, n2;
        TextView out;
        n1 = (EditText)findViewById(R.id.num1);
        n2 = (EditText)findViewById(R.id.num2);
        out = (TextView)findViewById(R.id.output);

        if(n1.length()!=0 && n2.length()!=0) {
            int v1 = Integer.parseInt(n1.getText().toString());
            int v2 = Integer.parseInt(n2.getText().toString());
            if(view.getId() == R.id.sum)
            out.setText(String.valueOf(n1+n2));
        } else{
            Toast.makeText(this, "Erro, preencha", Toast.LENGTH_SHORT).show();
        }
    }
}
