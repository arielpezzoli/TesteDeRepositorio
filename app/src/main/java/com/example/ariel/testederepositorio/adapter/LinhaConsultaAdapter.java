package com.example.ariel.testederepositorio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ariel.testederepositorio.AtualizarEventoActivity;
import com.example.ariel.testederepositorio.ListarEventoActivity;
import com.example.ariel.testederepositorio.R;
import com.example.ariel.testederepositorio.SearchActivity;
import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.example.ariel.testederepositorio.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LinhaConsultaAdapter extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;

    ArrayList<Evento> eventos = new ArrayList<>();


    //Cria objeto do tipo que lista as tarefas
    ListarEventoActivity listarEventos;

    private SearchActivity pesquisarEventos;

    //Construtor que recebe a ativida como parametro e a lista de tarefas que vai retornar do BD
    public LinhaConsultaAdapter(ListarEventoActivity listarEventos, ArrayList<Evento> eventos) {
        this.eventos = eventos;
        this.listarEventos = listarEventos;
        this.layoutInflater = (LayoutInflater) this.listarEventos.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public LinhaConsultaAdapter(SearchActivity pesquisarEventos, ArrayList<Evento> eventos) {
        this.eventos = eventos;
        this.pesquisarEventos = pesquisarEventos;
        this.layoutInflater = (LayoutInflater) this.pesquisarEventos.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //Retorna a quantidade de objetos que sta na lista
    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Método converte os valoes de um item  da lista de Tarefas para uma linha do ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Cria um objeto para acessar o layout activity_linha.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha, null);

        //vincula os campos do arquivo de layout aos objetos cadastrados
        final TextView textViewTituloEvento = viewLinhaLista.findViewById(R.id.textViewTituloEvento);
        final TextView textViewDescricaoEvento = viewLinhaLista.findViewById(R.id.textViewDescricaoEvento);
        final TextView textViewLocalEvento = viewLinhaLista.findViewById(R.id.textViewLocalEvento);
        final TextView textViewHorarioEvento = viewLinhaLista.findViewById(R.id.textViewHorarioEvento);
        final TextView textViewDataEvento = viewLinhaLista.findViewById(R.id.textViewDataEvento);

        textViewTituloEvento.setText(eventos.get(position).getTitulo_evento());
        textViewDescricaoEvento.setText(eventos.get(position).getDescricao_evento());
        textViewLocalEvento.setText(String.valueOf(eventos.get(position).getLocal_evento()));
        textViewHorarioEvento.setText(String.valueOf(eventos.get(position).getHorario_evento()));
        textViewDataEvento.setText(String.valueOf(eventos.get(position).getData_evento()));

        if (pesquisarEventos != null) {
            Button buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
            buttonExcluir.setVisibility(View.INVISIBLE);
            Button buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);
            buttonEditar.setVisibility(View.INVISIBLE);
        }

        if (listarEventos != null) {
            Button buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
            Button buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);


            //Criando evento para excluir um registro do BD
            buttonExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mensagem = "Registro excluído com sucesso!";
                    //usa o objeto produto para fazer a exclusão
                    final Evento produto = eventos.get(position);
                    final DatabaseReference reference = ConfiguraFirebase.getNo("produtos");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!eventos.isEmpty()) {
                                eventos.remove(position);
                                reference.child(produto.getId_evento()).removeValue();
                                notifyList();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });


            //Criando evento para editar um registro do BD
            buttonEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //EXERCÍCIO FAZER A TAREFA DE EDIÇÃO USANDO A LÓGICA DA EXCLUSÃO

                    final Evento evento = eventos.get(position);
                    Log.d("msg", "1");

                    Intent intent = new Intent(listarEventos, AtualizarEventoActivity.class);
                    intent.putExtra("id_evento",evento.getId_evento());
                    intent.putExtra("titulo_evento",textViewTituloEvento.getText());
                    intent.putExtra("descricao_evento",textViewDescricaoEvento.getText());
                    intent.putExtra("local_evento", textViewLocalEvento.getText());
                    intent.putExtra("horario_evento", textViewHorarioEvento.getText());
                    intent.putExtra("data_evento", textViewDataEvento.getText());
//                    Log.d("msg", "2");
                    listarEventos.startActivity(intent);

//
//
//
//                    HashMap<String, Object> map = new HashMap<>();
//
//                    if (produto.getNome() != null)
//                        map.put("nome", produto.getNome());
//                    if (produto.getDescricao() != null)
//                        map.put("nome", produto.getDescricao());
//                    if (produto.getValorUnitario() > 0)
//                        map.put("valor", produto.getValorUnitario());
//
//                    reference.child(produto.getId()).updateChildren(map);
                }
            });
            //ajustar com método


        }
        return viewLinhaLista;
    }

    private void notifyList() {
        this.notifyDataSetChanged();
    }

}
