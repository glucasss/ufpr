package com.example.customlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCell extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] refri;
    private final Integer[] imageId;

    public ListCell(MainActivity context, String[] refri, Integer[] imageId) {
        super(context, R.layout.list_cell, refri);
        this.context = context;
        this.refri = refri;
        this.imageId = imageId;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);
        TextView txtTitle = rowView.findViewById(R.id.txt);
        ImageView imageView = rowView.findViewById(R.id.img);
        txtTitle.setText(refri[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
