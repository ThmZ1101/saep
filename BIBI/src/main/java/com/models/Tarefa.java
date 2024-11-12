package com.models;

import java.util.Date;

public class Tarefa {
    private int id;
    private String descricao;
    private int usuarioId;
    private String setor;
    private String prioridade;
    private Date dataCadastro;
    private String status;

    public Tarefa(int id, String descricao, int usuarioId, String setor, String prioridade, Date dataCadastro, String status) {
        this.id = id;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.setor = setor;
        this.prioridade = prioridade;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
