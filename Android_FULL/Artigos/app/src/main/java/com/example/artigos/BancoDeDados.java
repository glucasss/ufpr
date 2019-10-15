package com.example.artigos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados {
    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    static String KEY_REVISTA = "revista";

    String NOME_BANCO = "db_Revistas";
    String NOME_TABELA = "artigo";
    int VERSAO_BANCO = 2;
    String SQL_CREATE_TABLE = "create table artigo " +
            "("+KEY_ID+" integer primary key autoincrement, "
            + KEY_NOME+" text not null, "
            + KEY_REVISTA+" text);";

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context ctx){
        this.context = ctx;
        openHelper = new MeuOpenHelper(context);
    }

    private class MeuOpenHelper extends SQLiteOpenHelper {
        MeuOpenHelper(Context context) {
            super(context, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(SQL_CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS artigo");
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

    public long insereArtigo(String nome, String revista){
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        campos.put(KEY_REVISTA, revista);
        return db.insert(NOME_TABELA, null, campos);
    }

    public boolean apagaArtigo(long id){
        return db.delete(NOME_TABELA, KEY_ID+"="+id, null) > 0;
    }

    public Cursor retornarTodosArtigos(){
        return db.query(NOME_TABELA, new String[]{
                KEY_ID, KEY_NOME, KEY_REVISTA},
                null, null, null, null, null);
    }

    public boolean atualizaArtigo(long id, String nome, String revista){
        ContentValues args = new ContentValues();
        args.put(KEY_NOME, nome);
        args.put(KEY_REVISTA, revista);
        return db.update(NOME_TABELA, args, KEY_ID+"="+id, null) > 0;
    }
}
