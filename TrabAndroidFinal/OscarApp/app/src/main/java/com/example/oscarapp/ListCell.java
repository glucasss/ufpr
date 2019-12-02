package com.example.oscarapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oscarapp.FilmeVO.FilmeVO;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ListCell extends ArrayAdapter<FilmeVO> {

    private final Activity context;
    private final FilmeVO[] filmeVO;

    public ListCell(Activity context, FilmeVO[] filmeVO){
        super(context, R.layout.list_cell, filmeVO);
        this.context = context;
        this.filmeVO = filmeVO;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);
        TextView lblFilme = (TextView) rowView.findViewById(R.id.lblFilme);
        TextView lblGenero = (TextView) rowView.findViewById(R.id.lblGenero);
        ImageView imgLista = (ImageView) rowView.findViewById(R.id.imgLista);
        lblFilme.setText(filmeVO[position].getNome());
        lblGenero.setText(filmeVO[position].getGenero());
        try {
            imgLista.setImageDrawable(getResource(filmeVO[position].getFoto()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowView;
    }

    public Drawable getResource(String url) throws MalformedURLException, IOException {
        return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
    }

}
