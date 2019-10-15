package com.example.numberguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num, tries;
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();
        num = r.nextInt(100) + 1;
        System.out.println(num);
        tries = 0;
    }

    public void guess (View view){
        EditText input = (EditText)findViewById(R.id.input);
        TextView output = (TextView)findViewById(R.id.hint);

        if(input.length() == 0){
            Toast.makeText(this, "Forneça um número", Toast.LENGTH_LONG).show();
        }
        else {
            String tip;
            int inputNumber = Integer.parseInt(input.getText().toString());
            tries++;
            if (num > inputNumber)
                tip = "Número sorteado é maior";
            else if (num < inputNumber)
                tip = "Número sorteado é menor";
            else
                tip = "Você acertou em " + tries + " tentativas.";
            output.setText(tip);
        }
    }
}
