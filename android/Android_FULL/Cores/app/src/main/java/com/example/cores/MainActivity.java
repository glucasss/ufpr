package com.example.cores;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private TextView selectedColor;
    private TextView color;
    private String[] hexColor = {"00","00","00"};

    private class EventSeek implements SeekBar.OnSeekBarChangeListener{
        private byte color;

        public EventSeek(byte color){
            this.color = color;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekbar) {}

        @Override
        public void onStartTrackingTouch(SeekBar seekbar) {}

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
            setHexNumber(progress, color);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redSeekBar = (SeekBar)findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar)findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar)findViewById(R.id.blueSeekBar);

        redSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)0));
        greenSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)1));
        blueSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)2));

        selectedColor = (TextView)findViewById(R.id.selectedColor);
        color = (TextView)findViewById(R.id.color);

        setColor("#" + hexColor[0] + hexColor[1] + hexColor[2]);
    }
    private void setColor(String str){
        selectedColor.setText(str);
        color.setBackgroundColor(Color.parseColor(str));
    }

    private void setHexNumber(int progress, byte color){
        String c = Integer.toHexString(progress);
        hexColor[color] = (c.length() == 2 ? "" : "0") + c;
        setColor("#" + hexColor[0] + hexColor[1] + hexColor[2]);
    }



}
