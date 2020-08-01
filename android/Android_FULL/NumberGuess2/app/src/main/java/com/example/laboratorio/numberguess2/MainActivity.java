package com.example.laboratorio.numberguess2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText num1;
    TextView output;
    RadioButton real;
    RadioButton real2;
    RadioButton dolar;
    RadioButton dolar2;
    RadioButton euro;
    RadioButton euro2;
    RadioButton libra;
    RadioButton libra2;
    RadioButton peso;
    RadioButton peso2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText) findViewById(R.id.num1);
        output = (TextView) findViewById(R.id.output);
        real = findViewById(R.id.real);
        real2 = findViewById(R.id.real2);
        dolar = findViewById(R.id.dolar);
        dolar2 = findViewById(R.id.dolar2);
        euro = findViewById(R.id.euro);
        euro2 = findViewById(R.id.euro2);
        libra = findViewById(R.id.libra);
        libra2 = findViewById(R.id.libra2);
        peso = findViewById(R.id.peso);
        peso2 = findViewById(R.id.peso2);
    }

    public void calc(View view){

        float num1Temp= Float.parseFloat(num1.getText().toString());
        Conversor objConversor = new Conversor(num1Temp, handler);
        objConversor.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            if(msg.what == 1){
                float result = msg.getData().getFloat("resultado");
                output.setText(Float.toString(result));
            }
        }
    };

    public class Conversor extends Thread {
        private float num1, result;
        private Socket socket;
        private DataOutputStream out;
        private DataInputStream in;
        private static final String IP = "192.168.15.4";
        private static final int porta = 12345;

        private Handler handler;

        public Conversor(float num1, Handler handler){
            this.num1 = num1;
            this.handler = handler;
        }

        @Override
        public void run(){
            super.run();
            try{
                socket = new Socket(IP, porta);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                out.writeFloat(num1);
                out.flush();
                result = in.readFloat();
                out.close();
                in.close();
                socket.close();
                Bundle b = new Bundle();
                b.putFloat("resultado", result);
                Message msg = new Message();
                msg.what = 1;
                msg.setData(b);
                handler.sendMessage(msg);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
