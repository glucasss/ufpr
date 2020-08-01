package com.example.avaliacao2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView total;
    EditText valor;
    RadioButton bradesco;
    RadioButton petrobras;
    RadioButton compra;
    RadioButton venda;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = findViewById(R.id.total);
        valor = findViewById(R.id.valor);
        bradesco = findViewById(R.id.bradesco);
        petrobras = findViewById(R.id.petrobras);
        compra = findViewById(R.id.compra);
        venda = findViewById(R.id.venda);
        button = findViewById(R.id.button);
    }

    public void enviar(View view){
        int op1;
        int op2;
        if(bradesco.isChecked()){
            op2=0;
        }else{
            op2=1;
        }
        if(compra.isChecked()){
            op1=0;
        }else{
            op1=1;
        }
        NumTask num = new NumTask(total);
        int val = Integer.parseInt(valor.getText().toString());
        num.execute(op1,op2,val);
    }
}

class NumTask extends AsyncTask<Integer, Void, Double> {
    private TextView text;
    private int op1,op2,valor;
    private double result;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private static final String IP = "172.20.10.7";
    private static final int porta = 12345;

    public NumTask(TextView text){
        this.text = text;
    }

    @Override
    protected Double doInBackground(Integer... params){
        op1 = params[0];
        op2 = params[1];
        valor = params[2];
        try {
            socket = new Socket(IP, porta);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            out.writeInt(op2);
            out.writeInt(valor);
            out.flush();
            result = in.readDouble();
            System.out.print(result);
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Double aDouble) {
        super.onPostExecute(aDouble);
        text.setText(String.valueOf(aDouble));
    }

}
