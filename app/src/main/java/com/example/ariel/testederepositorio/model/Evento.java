package com.example.ariel.testederepositorio.model;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.example.ariel.testederepositorio.ListarEventoActivity;
//import com.example.ariel.testederepositorio.adapter.LinhaConsultaAdapter;
import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Evento {

    private String id_evento;
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

    public Evento(String id_evento, String titulo_evento, String descricao_evento, String local_evento, String horario_evento, String data_evento) {
        this.id_evento = id_evento;
        this.titulo_evento = titulo_evento;
        this.descricao_evento = descricao_evento;
        this.local_evento = local_evento;
        this.horario_evento = horario_evento;
        this.data_evento = data_evento;
    }


    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
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


    public static List<Evento> inicializaLista() {
        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("titulo1", "desc1", "local1", "horario1", "data"));
        eventos.add(new Evento("titulo2", "desc2", "local1", "horario1", "data"));
        eventos.add(new Evento("titulo3", "desc3", "local1", "horario1", "data"));
        eventos.add(new Evento("titulo4", "desc4", "local1", "horario1", "data"));
        eventos.add(new Evento("titulo5", "desc5", "local1", "horario1", "data"));
        return eventos;
    }


}
