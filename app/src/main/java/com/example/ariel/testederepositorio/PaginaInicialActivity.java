package com.example.ariel.testederepositorio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ariel.testederepositorio.adapter.LinhaConsultaAdapter;
import com.example.ariel.testederepositorio.dao.ConfiguraFirebase;
import com.example.ariel.testederepositorio.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PaginaInicialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener {

    //    listando dados do banco
//    private ArrayList<Evento> listEventos = new ArrayList<>();
//    ListView lista = null;
//    ArrayAdapter<Evento> listAdapter = null;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



//**************************
//**************************
//LISTANDO DADOS FIREBASE
//        listEventos = new ArrayList<>();
//        lista = findViewById(R.id.listaEventosPesquisaPaginaInicial);
//        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listEventos);
//
//        //pega o valor que veio pela intenção
//        Intent intent = getIntent();
//        String parametroPesquisa = intent.getStringExtra("pesquisa");
////        Log.d("MSG", "s = " + parametroPesquisa);
//
//        DatabaseReference reference = ConfiguraFirebase.getNo("eventos");
//        listEventos = new ArrayList<>();
//
//        //ordenar os resultados pelo nome e mostrar somente os registros que possuem o nome passado como parâmetro na janela de pesquisa
//        Query pesquisa = reference.orderByChild("titulo_evento").equalTo(parametroPesquisa);
//
//        pesquisa.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("MSG", "Dados do evento => " + dataSnapshot.getValue().toString());
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    //para buscar todos os nós filhos de produtos
//                    Evento evento = ds.getValue(Evento.class);
//                    evento.setId_evento(ds.getKey());
//                    listEventos.add(evento);
//                }
//                lista.setAdapter(new LinhaConsultaAdapter(PaginaInicialActivity.this, listEventos));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//FIM LISTAGEM DE DADOS
//*****************************
//*****************************

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pagina_inicial, menu);

        MenuItem searchItem = menu.findItem(R.id.action_pesquisa);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        PaginaLoginActivity paginaLoginActivity = new PaginaLoginActivity();
//        if (paginaLoginActivity.usuarioLogado()) {
//
//        }
//        noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(getApplicationContext(), PaginaLoginActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_sair) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        if (id == R.id.action_pesquisa) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pagina_inicial) {

        } else if (id == R.id.nav_cadastro) {
            Intent intent = new Intent(getApplicationContext(), PaginaCadastroEventoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_list) {
            Intent intent = new Intent(getApplicationContext(), ListarEventoActivity.class);
            startActivity(intent);
        }
//        else if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d("MSG", "query s => " + s);
        Intent intent = new Intent(PaginaInicialActivity.this, SearchActivity.class);
        intent.putExtra("pesquisa", s);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d("MSG", "change s => " + s);
        return false;
    }
}
