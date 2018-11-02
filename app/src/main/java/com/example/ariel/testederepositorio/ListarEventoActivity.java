package com.example.ariel.testederepositorio;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ariel.testederepositorio.adapter.LinhaConsultaAdapter;
import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.example.ariel.testederepositorio.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarEventoActivity extends ListActivity {

    private ArrayList<Evento> listEventos = new ArrayList<>();
    ListView lista = null;
    ArrayAdapter<Evento> listAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listEventos = new ArrayList<>();
        lista = getListView();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listEventos);

        DatabaseReference reference = ConfiguraFirebase.getNo("eventos");
        listEventos = new ArrayList<>();
        //associar os eventos ao nó produtos para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Evento evento = ds.getValue(Evento.class);
                    evento.setId_evento(ds.getKey());
                    listEventos.add(evento);
                }
                lista.setAdapter(new LinhaConsultaAdapter(ListarEventoActivity.this, listEventos));
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
