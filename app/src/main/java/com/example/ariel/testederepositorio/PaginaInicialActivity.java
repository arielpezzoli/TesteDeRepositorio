package com.example.ariel.testederepositorio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ariel.testederepositorio.pacoteDeTeste.ListaCardFragment;
import com.google.firebase.auth.FirebaseAuth;


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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListaCardFragment fragment = new ListaCardFragment();
        transaction.replace(R.id.fragment_paginaInicial, fragment);
        transaction.commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
//        } else if (id == R.id.nav_list) {
//            Intent intent = new Intent(getApplicationContext(), ListarEventoActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_card_list) {
            Intent intent = new Intent(getApplicationContext(), ListarEventoRecycler.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d("MSG", "query s => " + s);
//        Intent intent = new Intent(PaginaInicialActivity.this, SearchActivity.class);
        Intent intent = new Intent(PaginaInicialActivity.this, SearchCardActivity.class);
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
