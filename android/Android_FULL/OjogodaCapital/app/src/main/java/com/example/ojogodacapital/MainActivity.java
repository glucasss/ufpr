package com.example.ojogodacapital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String matriz[][] =
            {
                    {"Parana", "Santa Catarina", "Rio Grande do Sul", "Bahia", "Sergipe"},
                    {"Curitiba", "Florianopolis", "Porto Alegre", "Salvador", "Aracaju"}
            };
    Random r;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();
        num = r.nextInt(5);
        System.out.print(num);
        TextView output = (TextView)findViewById(R.id.estado);
        output.setText(matriz[0][num]);
    }

    public void validar(View view){
        EditText input = (EditText)findViewById(R.id.capital);
        TextView output = (TextView)findViewById(R.id.resposta);

        if(input.length() == 0){
            Toast.makeText(this, "Forneça uma resposta", Toast.LENGTH_LONG).show();
        }
        else{
            String cidade = matriz[1][num];
            String capital = input.getText().toString();
            capital = capital.toLowerCase();
            if(cidade.toLowerCase().equals(capital))
                output.setText("Você acertou.");
            else{
                Toast.makeText(this, "Você errou, tente novamente", Toast.LENGTH_SHORT).show();
                output.setText("");
            }
        }
    }
}
