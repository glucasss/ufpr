package com.example.votacao;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class list extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] candidato;
    private final String[] partido;

    public list(MainActivity context, String[] candidato, String[] partido) {
        super(context, R.layout.activity_list, candidato);
        this.context = context;
        this.candidato = candidato;
        this.partido = partido;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_list, null, true);
        TextView txtTitle = rowView.findViewById(R.id.txt);
        TextView txtDetail = rowView.findViewById(R.id.txt2);
        txtTitle.setText(candidato[position]);
        txtDetail.setText(partido[position]);
        return rowView;
    }
}

