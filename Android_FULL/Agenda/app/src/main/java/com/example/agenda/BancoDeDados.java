package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados {
    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    static String KEY_FONE = "fone";

    String NOME_BANCO = "db_Agenda";
    String NOME_TABELA = "contato";
    int VERSAO_BANCO = 1;
    String SQL_CREATE_TABLE = "create table contato (" + KEY_ID + " integer primary key autoincrement, " + KEY_NOME + " text not null, " + KEY_FONE + " text not null);";

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context ctx){
        this.context = ctx;
        openHelper = new MeuOpenHelper(context);
    }

    private class MeuOpenHelper extends SQLiteOpenHelper{
        MeuOpenHelper(Context context){
            super(context, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(SQL_CREATE_TABLE);
                System.out.println("BASE CRIADA");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS contato");
            onCreate(db);
        }
    }

    public BancoDeDados abrir() throws SQLException{
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        openHelper.close();
    }

    public long insereContato(String nome, String fone){
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        campos.put(KEY_FONE, fone);
        return db.insert(NOME_TABELA, null, campos);
    }

    public void insereContato1(){
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, "teste");
        campos.put(KEY_FONE, "123456789");
        db.insert(NOME_TABELA, null, campos);
    }

    public boolean apagaContato(long id){
        return db.delete(NOME_TABELA, KEY_ID + "=" + id, null) > 0;
    }

    public Cursor retornaTodosContatos(){
        return db.query(NOME_TABELA, new String[] {
        KEY_ID, KEY_NOME, KEY_FONE },
                null, null, null, null, null);
    }

    public boolean atualizaContato(long id, String nome, String fone){
        ContentValues args = new ContentValues();
        args.put(KEY_NOME, nome);
        args.put(KEY_FONE, fone);
        return db.update(NOME_TABELA, args, KEY_ID + "=" + id, null) > 0;
    }
}
