package com.example.oscarapp.VO;

public class VotoDiretorVO {

    private int id;
    private String nome;
    private String login;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    private String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login){
        this.login = login;
    }

}
