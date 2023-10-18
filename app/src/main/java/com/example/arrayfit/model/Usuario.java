package com.example.arrayfit.model;

public class Usuario {
    private String nome;
    private String email;
    private String senha;

    private int treinos;


    private String genero;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTreinos() {
        return treinos;
    }

    public void setTreinos(int treinos) {
        this.treinos = treinos;
    }

}
