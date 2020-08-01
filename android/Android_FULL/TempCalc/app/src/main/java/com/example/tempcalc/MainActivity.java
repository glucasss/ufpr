package com.example.tempcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void converter(View view) {
        RadioButton toCelsius = (RadioButton)findViewById(R.id.radioCel);
        EditText input = (EditText)findViewById(R.id.entrada);
        TextView output = (TextView)findViewById(R.id.saida);

        if(input.length()==0){
            Toast.makeText(this, "Forneça uma temperatura!", Toast.LENGTH_SHORT).show();
            return;
        }

        float inputTemp = Float.parseFloat(input.getText().toString());
        if(toCelsius.isChecked())
            output.setText("Resultado: " + String.valueOf(F2C(inputTemp))+ " C");
        else
            output.setText("Resultado: " + String.valueOf(C2F(inputTemp))+ " F");
    }

    private float F2C(float fahr){
        return((fahr - 32) * 5 / 9);
    }

    private float C2F(float cel){
        return((cel * 9 / 5) + 32);
    }
}
