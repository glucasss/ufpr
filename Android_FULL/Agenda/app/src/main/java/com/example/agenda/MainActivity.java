package com.example.agenda;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private BancoDeDados db;
    private List<Contato> contatos = new ArrayList<>();
    private AgendaAdapter agendaAdapter;
    public static final int REQUEST_EDICAO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new BancoDeDados(this);
        lerDados();
    }

    public void lerDados(){
        db.abrir();
        contatos.clear();
        Cursor cursor = db.retornaTodosContatos();
        if(cursor.moveToFirst())
            do{
                Contato c = new Contato();
                c.id = cursor.getInt(cursor.getColumnIndex(BancoDeDados.KEY_ID));
                c.nome = cursor.getString(cursor.getColumnIndex(BancoDeDados.KEY_NOME));
                c.fone = cursor.getString(cursor.getColumnIndex(BancoDeDados.KEY_FONE));
                contatos.add(c);
        } while (cursor.moveToNext());
        if(contatos.size() > 0){
            if (agendaAdapter == null){
                agendaAdapter = new AgendaAdapter(this, contatos) {
                    @Override
                    public void edita(Contato contato) {
                        Intent intent = new Intent(getApplicationContext(), NovaEdicaoActivity.class);
                        intent.putExtra("contato", contato);
                        startActivityForResult(intent, REQUEST_EDICAO);
                    }

                    @Override
                    public void deleta(Contato contato) {
                        db.abrir();
                        db.apagaContato(contato.id);
                        db.fechar();
                        lerDados();
                    }
                };
                setListAdapter(agendaAdapter);
            } else
                agendaAdapter.novosDados(contatos);
        }
        db.fechar();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent addNewContact = new Intent(this, NovaEdicaoActivity.class);
        startActivity(addNewContact);
        return super.onOptionsItemSelected(item);
    }

    public static final int REQUEST_SALVOU = 1;

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_EDICAO)
            if(resultCode == REQUEST_SALVOU)
                lerDados();
    }
}
