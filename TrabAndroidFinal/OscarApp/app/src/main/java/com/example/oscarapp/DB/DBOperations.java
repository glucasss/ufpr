package com.example.oscarapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.oscarapp.FilmeVO.DiretorVO;
import com.example.oscarapp.FilmeVO.FilmeVO;
import com.example.oscarapp.VO.VotedVO;

import java.util.ArrayList;
import java.util.List;


public class DBOperations {

    private SimpleBDWrapper dbHelper;
    private String[] FILME_TABLE_COLUMNS = {"id", "nome", "genero", "foto"};
    private String[] DIRETOR_TABLE_COLUMNS = {"id", "nome"};
    private String[] VOTED_TABLE_COLUMNS = {"id", "voted"};
    private SQLiteDatabase database;

    public DBOperations(Context ctx){
        dbHelper = new SimpleBDWrapper(ctx);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public FilmeVO addFilme(FilmeVO filmeVO){
        ContentValues values = new ContentValues();

        values.put("id", filmeVO.getId());
        values.put("nome", filmeVO.getNome());
        values.put("genero", filmeVO.getGenero());
        values.put("foto", filmeVO.getFoto());

        long filmeId = database.insert("votoFilme", null, values);

        Cursor cursor = database.query("votoFilme", FILME_TABLE_COLUMNS, "id = " + filmeId,
                null, null, null, null);
        cursor.moveToFirst();

        FilmeVO newComment = parseFilme(cursor);
        cursor.close();
        return newComment;

    }

    public DiretorVO addDiretor(DiretorVO diretorVO){
        ContentValues values = new ContentValues();

        values.put("id", diretorVO.getId());
        values.put("nome", diretorVO.getNome());

        long diretorId = database.insert("votoDiretor", null, values);

        Cursor cursor = database.query("votoDiretor", DIRETOR_TABLE_COLUMNS, "id = " + diretorId,
                null, null, null, null);
        cursor.moveToFirst();

        DiretorVO newComment = parseDiretor(cursor);
        cursor.close();
        return newComment;
    }

    public VotedVO addVoted(VotedVO voted){
        ContentValues values = new ContentValues();

        values.put("id", 1);
        values.put("voted", voted.getVoted());
        database.insert("voted", null, values);

        Cursor cursor = database.query("voted", VOTED_TABLE_COLUMNS, "id = " + 1,
                null, null, null, null);
        cursor.moveToFirst();

        VotedVO newComment = parseVoted(cursor);

        cursor.close();
        return newComment;

    }

    public void deleteFilme(FilmeVO comment){
        long id = comment.getId();
        System.out.println("Removido id: " +id);
        database.delete("votoFilme", "id = " + id, null);
    }

    public void deleteDiretor(DiretorVO comment) {
        long id = comment.getId();
        System.out.println("Removido diretor id: " +id);
        database.delete("votoDiretor", "id = " +id, null);
    }

    public void deleteVoted(VotedVO comment){
        long id = 1;
        System.out.println("Removido voto: " + id);
        database.delete("voted", "id = " + id, null);
    }

    public List<FilmeVO> getAllFilmes(){
        List<FilmeVO> filmes = new ArrayList<FilmeVO>();

        Cursor cursor = database.query("votoFilme", FILME_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            FilmeVO filmeVO = parseFilme(cursor);
            filmes.add(filmeVO);
            cursor.moveToNext();
        }

        cursor.close();
        return filmes;

    }

    public List<VotedVO> getAllVoted(){
        List<VotedVO> voteds = new ArrayList<VotedVO>();

        Cursor cursor = database.query("voted", VOTED_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            VotedVO votedVO = parseVoted(cursor);
            voteds.add(votedVO);
            cursor.moveToNext();
        }
        cursor.close();
        return voteds;
    }

    public List<DiretorVO> getAllDiretor(){
        List<DiretorVO> diretores = new ArrayList<DiretorVO>();

        Cursor cursor = database.query("votoDiretor", DIRETOR_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DiretorVO diretorVO = parseDiretor(cursor);
            diretores.add(diretorVO);
            cursor.moveToNext();
        }

        cursor.close();
        return diretores;
    }

    private FilmeVO parseFilme(Cursor cursor){
        FilmeVO filmeVO = new FilmeVO();
        filmeVO.setId(cursor.getInt(0));
        filmeVO.setNome(cursor.getString(1));
        filmeVO.setGenero(cursor.getString(2));
        filmeVO.setFoto(cursor.getString(3));

        return filmeVO;
    }

    private DiretorVO parseDiretor(Cursor cursor){
        DiretorVO diretorVO = new DiretorVO();
        diretorVO.setId(cursor.getInt(0));
        diretorVO.setNome(cursor.getString(1));

        return diretorVO;
    }

    private VotedVO parseVoted(Cursor cursor){
        VotedVO votedVO = new VotedVO();
        votedVO.setId(1);
        votedVO.setVoted(cursor.getString(1));

        return votedVO;
    }

}
