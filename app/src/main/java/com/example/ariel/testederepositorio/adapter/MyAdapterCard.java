package com.example.ariel.testederepositorio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ariel.testederepositorio.AtualizarEventoActivity;
import com.example.ariel.testederepositorio.ClickRecycler;
import com.example.ariel.testederepositorio.ListarEventoRecycler;
import com.example.ariel.testederepositorio.R;
import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.example.ariel.testederepositorio.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterCard extends RecyclerView.Adapter<MyAdapterCard.MyViewHolderCard> {

    public static ClickRecycler clickRecycler;
    Context contexto;
    private List<Evento> listaEventos;

//    ListarEventoRecycler listarEventoRecycler;

    public MyAdapterCard(Context ctx, List<Evento> list, ClickRecycler clickRecycler) {
        this.contexto = ctx;
        this.listaEventos = list;
        this.clickRecycler = clickRecycler;
    }

    @Override
    public MyViewHolderCard onCreateViewHolder(ViewGroup viewGroup, int i) {
        System.out.println("Contexto=" + viewGroup.getContext());
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list, viewGroup, false);
        return new MyViewHolderCard(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCard viewHolder, final int position) {
        Evento user = listaEventos.get(position);
        viewHolder.textViewTitulo.setText(user.getTitulo_evento());
        viewHolder.textViewDescricao.setText(user.getDescricao_evento());
        viewHolder.textViewLocal.setText(user.getLocal_evento());
        viewHolder.textViewHorario.setText(user.getHorario_evento());
        viewHolder.textViewData.setText(user.getData_evento());

        viewHolder.buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Registro excluído com sucesso!";
                //usa o objeto produto para fazer a exclusão
                final Evento produto = listaEventos.get(position);
                final DatabaseReference reference = ConfiguraFirebase.getNo("eventos");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!listaEventos.isEmpty()) {
                            listaEventos.remove(position);
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

        viewHolder.buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EXERCÍCIO FAZER A TAREFA DE EDIÇÃO USANDO A LÓGICA DA EXCLUSÃO

                final Evento evento = listaEventos.get(position);

                Intent intent = new Intent(contexto, AtualizarEventoActivity.class);
                intent.putExtra("id_evento", evento.getId_evento());
                intent.putExtra("titulo_evento", evento.getTitulo_evento());
                intent.putExtra("descricao_evento", evento.getDescricao_evento());
                intent.putExtra("local_evento", evento.getLocal_evento());
                intent.putExtra("horario_evento", evento.getHorario_evento());
                intent.putExtra("data_evento", evento.getData_evento());
//                    Log.d("msg", "2");
                contexto.startActivity(intent);

            }
        });


    }

    private void removeItem(int position) {
        listaEventos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaEventos.size());
    }

    @Override
    public int getItemCount() {
        return listaEventos != null ? listaEventos.size() : 0;

    }

    private void notifyList() {
        this.notifyDataSetChanged();
    }

    protected class MyViewHolderCard extends RecyclerView.ViewHolder {
        private TextView textViewTitulo;
        private TextView textViewDescricao;
        private TextView textViewLocal;
        private TextView textViewHorario;
        private TextView textViewData;

        Button buttonExcluir; //= itemView.findViewById(R.id.btnExcluirCard);
        Button buttonEditar; // = itemView.findViewById(R.id.btnEditarCard);

        private MyViewHolderCard(final View itemView) {
            super(itemView);
            textViewTitulo = (TextView) itemView.findViewById(R.id.textViewTituloEvento);
            textViewDescricao = (TextView) itemView.findViewById(R.id.textViewDescricaoEvento);
            textViewLocal = (TextView) itemView.findViewById(R.id.textViewLocalEvento);
            textViewHorario = (TextView) itemView.findViewById(R.id.textViewHorarioEvento);
            textViewData = (TextView) itemView.findViewById(R.id.textViewDataEvento);

            buttonExcluir = (Button) itemView.findViewById(R.id.btnExcluirCard);
            buttonEditar = (Button) itemView.findViewById(R.id.btnEditarCard);

        }
    }


}
