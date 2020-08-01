package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public abstract class AgendaAdapter extends BaseAdapter {

    private List<Contato> contatos;
    private LayoutInflater inflater;

    public AgendaAdapter(Context context, List<Contato> contatos) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contatos = contatos;
    }

    public void novosDados(List<Contato> contatos) {
        this.contatos = contatos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        View v = inflater.inflate(R.layout.item_agenda, null);
        ((TextView) (v.findViewById(R.id.txtNome))).setText(contatos.get(position).nome);
        return v;
    }

    public abstract void edita(Contato contato);

    public abstract void deleta(Contato contato);

}