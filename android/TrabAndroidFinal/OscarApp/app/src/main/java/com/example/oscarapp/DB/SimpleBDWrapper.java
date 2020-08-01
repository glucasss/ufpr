package com.example.oscarapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleBDWrapper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OscarApp.db";

    private static final String TB_VOTOFILME =
            "CREATE TABLE votoFilme(" +
                    "id integer primary key," +
                    "nome text," +
                    "genero text," +
                    "foto text);";
    private static final String TB_VOTODIRETOR =
            "CREATE TABLE votoDiretor(" +
                    "id integer primary key," +
                    "nome text);";
    private static final String TB_VOTED =
            "CREATE TABLE voted(id integer primary key, text);";

    public SimpleBDWrapper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TB_VOTOFILME);
        sqLiteDatabase.execSQL(TB_VOTODIRETOR);
        sqLiteDatabase.execSQL(TB_VOTED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS votoFilme");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS votoDiretor");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS voted");
        onCreate(sqLiteDatabase);

    }
}
