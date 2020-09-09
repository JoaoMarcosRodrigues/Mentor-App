package com.example.mentor.tarefa;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private int nivel;
    private boolean status;
    private String email_mentor;
    private String email_mentorado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail_mentor() {
        return email_mentor;
    }

    public void setEmail_mentor(String email_mentor) {
        this.email_mentor = email_mentor;
    }

    public String getEmail_mentorado() {
        return email_mentorado;
    }

    public void setEmail_mentorado(String email_mentorado) {
        this.email_mentorado = email_mentorado;
    }
}
