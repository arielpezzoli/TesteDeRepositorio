package com.example.ariel.testederepositorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class AtualizarEventoActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_evento);

        Intent intencao = getIntent();

        id = intencao.getStringExtra("id_evento");
        String titulo_evento = intencao.getStringExtra("titulo_evento");
        String descricao_evento = intencao.getStringExtra("descricao_evento");
        String local_evento = intencao.getStringExtra("local_evento");
        String horario_evento = intencao.getStringExtra("horario_evento");
        String data_evento = intencao.getStringExtra("data_evento");

        TextView textoRecebidoTitulo = findViewById(R.id.textoRecebidoTitulo);
        TextView textoRecebidoDescricao = findViewById(R.id.textoRecebidoDescricao);
        TextView textoRecebidoLocal = findViewById(R.id.textoRecebidoLocal);
        TextView textoRecebidoHorario = findViewById(R.id.textoRecebidoHorario);
        TextView textoRecebidoData = findViewById(R.id.textoRecebidoData);

        textoRecebidoTitulo.setText(titulo_evento);
        textoRecebidoDescricao.setText(descricao_evento);
        textoRecebidoLocal.setText(local_evento);
        textoRecebidoHorario.setText(horario_evento);
        textoRecebidoData.setText(data_evento);

        Button atualizar = findViewById(R.id.atualizar);

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id = ;

                String titulo = ((TextView) findViewById(R.id.textoRecebidoTitulo)).getText().toString();
                String descricao = ((TextView) findViewById(R.id.textoRecebidoDescricao)).getText().toString();
                String local = ((TextView) findViewById(R.id.textoRecebidoLocal)).getText().toString();
                String horario = ((TextView) findViewById(R.id.textoRecebidoHorario)).getText().toString();
                String data = ((TextView) findViewById(R.id.textoRecebidoData)).getText().toString();


                final DatabaseReference reference = ConfiguraFirebase.getNo("eventos");

                HashMap<String, Object> map = new HashMap<>();

                if (titulo != null)
                    map.put("titulo_evento", titulo);
                if (descricao != null)
                    map.put("descricao_evento", descricao);
                if (local != null)
                    map.put("local_evento", local);
                if (horario != null)
                    map.put("horario_evento", horario);
                if (data != null)
                    map.put("data_evento", data);

                reference.child(id).updateChildren(map);
                Toast.makeText(getApplicationContext(), "atualizado", Toast.LENGTH_LONG);

                finish();


            }
        });

    }


}
