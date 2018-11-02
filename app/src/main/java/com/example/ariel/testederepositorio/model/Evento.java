package com.example.ariel.testederepositorio.model;

public class Evento {

    private Integer id_evento;
    private String titulo_evento;
    private String descricao_evento;
    private String local_evento;
    private String horario_evento;
    private String data_evento;


    public Evento() {
    }

    public Evento(String titulo_evento, String descricao_evento, String local_evento, String horario_evento, String data_evento) {
        this.titulo_evento = titulo_evento;
        this.descricao_evento = descricao_evento;
        this.local_evento = local_evento;
        this.horario_evento = horario_evento;
        this.data_evento = data_evento;
    }

    public Evento(Integer id_evento, String titulo_evento, String descricao_evento, String local_evento, String horario_evento, String data_evento) {
        this.id_evento = id_evento;
        this.titulo_evento = titulo_evento;
        this.descricao_evento = descricao_evento;
        this.local_evento = local_evento;
        this.horario_evento = horario_evento;
        this.data_evento = data_evento;
    }


    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public String getTitulo_evento() {
        return titulo_evento;
    }

    public void setTitulo_evento(String titulo_evento) {
        this.titulo_evento = titulo_evento;
    }

    public String getDescricao_evento() {
        return descricao_evento;
    }

    public void setDescricao_evento(String descricao_evento) {
        this.descricao_evento = descricao_evento;
    }

    public String getLocal_evento() {
        return local_evento;
    }

    public void setLocal_evento(String local_evento) {
        this.local_evento = local_evento;
    }

    public String getHorario_evento() {
        return horario_evento;
    }

    public void setHorario_evento(String horario_evento) {
        this.horario_evento = horario_evento;
    }

    public String getData_evento() {
        return data_evento;
    }

    public void setData_evento(String data_evento) {
        this.data_evento = data_evento;
    }
}
