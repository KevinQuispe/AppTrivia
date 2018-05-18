package com.example.pcquispe.apptrivia;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pcquispe.apptrivia.adaptadores.RankingAdapter;
import com.example.pcquispe.apptrivia.entidades.Ranking;
import com.example.pcquispe.apptrivia.http.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

import java.util.zip.Inflater;

public class RankingTriviaActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog progressDoalog, progreso;
    ListView listaelementos;
    ArrayList<Ranking> lista_ranking = new ArrayList<>();
    ArrayAdapter<String> adapter;

    // ListView lista;
    String[] ranking = {"Kevin", "Pedro", "Juan", "Javier", "Manuel", "Mario"};
    Inflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_trivia);
        getSupportActionBar().setTitle("Ranking Trivia");

        //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.activity_ranking_trivia, null);
        //inflater.inflate(R.layout.activity_ranking_trivia, container, false);

        //listaelementos = (ListView) findViewById(R.id.listado_ranking);
        //llamar a metodos
        listaelementos=(ListView) findViewById(R.id.listado_ranking_result);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ranking);
        //listaelementos.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(RankingTriviaActivity.this, android.R.layout.simple_list_item_1, ranking);
        listaelementos.setAdapter(adapter);
        //getDataRankig();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    //metodo block back press
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) ;
        return false;
    }

    //metodo cargar data resultado rankig
    public void getDataRankig() {
        Api.get(getApplicationContext(), "ranking/getdata", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "error de Conexion", Toast.LENGTH_SHORT).show();
                progreso.hide();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                try {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    lista_ranking = new Gson().fromJson(responseString, new TypeToken<ArrayList<Ranking>>() {}.getType());
                    //listaelementos.setAdapter(new RankingAdapter(RankingTriviaActivity.this,R.id.listado_ranking,lista_ranking));

                    System.out.println(lista_ranking);
                    //ArrayAdapter<Ranking> adapters=new ArrayAdapter<Ranking>(RankingTriviaActivity.this,R.layout.activity_ranking_trivia,lista_ranking);
                    //listaelementos.setAdapter(adapters);

                    listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getApplicationContext(), "item" + i, Toast.LENGTH_SHORT).show();
                        }
                    });
                    progreso.hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
