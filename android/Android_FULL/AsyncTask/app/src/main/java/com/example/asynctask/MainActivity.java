package com.example.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tex;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tex = findViewById(R.id.tex);
        bt = findViewById(R.id.bt);
    }

    public void processamento(View view){
        NumTask num = new NumTask(tex, bt);
        num.execute(10);
    }
}

class NumTask extends AsyncTask<Integer, Integer, Void>{
    private TextView text;
    private Button bt;

    public NumTask(TextView text, Button bt){
        this.text = text;
        this.bt = bt;
    }

    @Override
    protected Void doInBackground(Integer... params){
        int n = params[0];
        int i= 0;
        while(i<n){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            publishProgress(i);
            i++;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        text.setText(String.valueOf(values[0]));
    }
}
