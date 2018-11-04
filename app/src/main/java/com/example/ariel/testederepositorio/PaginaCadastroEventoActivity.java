package com.example.ariel.testederepositorio;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.example.ariel.testederepositorio.model.Evento;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class PaginaCadastroEventoActivity extends AppCompatActivity {

    private DatabaseReference reference = ConfiguraFirebase.getNoRaiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_cadastro_evento);
    }

    public void cadProduto(View view) {

        String titulo_evento = ((EditText) findViewById(R.id.titulo_evento)).getText().toString();
        String descricao_evento = ((EditText) findViewById(R.id.descricao_evento)).getText().toString();
        String local_evento = ((EditText) findViewById(R.id.local_evento)).getText().toString();
        String horario_evento = ((EditText) findViewById(R.id.horario_evento)).getText().toString();
        String data_evento = ((EditText) findViewById(R.id.data_evento)).getText().toString();

        Evento evento = new Evento(titulo_evento, descricao_evento, local_evento, horario_evento, data_evento);

        DatabaseReference eventos = reference.child("eventos");
        //gera um identificador único para cada produto
        //salva o produto na base de dados
        eventos.push().setValue(evento).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(PaginaCadastroEventoActivity.this, "Sucesso ao cadastrar o evento!", Toast.LENGTH_SHORT).show();
                limparCampos();
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PaginaCadastroEventoActivity.this, "Erro ao cadastrar o evento!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void limparCampos() {
        ((EditText) findViewById(R.id.titulo_evento)).setText("");
        ((EditText) findViewById(R.id.descricao_evento)).setText("");
        ((EditText) findViewById(R.id.local_evento)).setText("");
        ((EditText) findViewById(R.id.horario_evento)).setText("");
        ((EditText) findViewById(R.id.data_evento)).setText("");
    }
}
