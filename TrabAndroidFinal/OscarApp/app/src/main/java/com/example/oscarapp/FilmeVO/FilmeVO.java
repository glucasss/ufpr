package com.example.oscarapp.FilmeVO;

public class FilmeVO {

    private int id;
    private String nome;
    private String genero;
    private String foto;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getGenero(){
        return this.genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getFoto(){
        return this.foto;
    }

    public void setFoto(String foto){
        this.foto = foto;
    }

}
