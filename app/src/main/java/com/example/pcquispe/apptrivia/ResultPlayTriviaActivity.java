package com.example.pcquispe.apptrivia;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcquispe.apptrivia.adaptadores.RankingAdapter;
import com.example.pcquispe.apptrivia.entidades.Ranking;
import com.example.pcquispe.apptrivia.fragmentos.FormPreguntaFragment;
import com.example.pcquispe.apptrivia.fragmentos.ListRankigFragment;
import com.example.pcquispe.apptrivia.http.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.journeyapps.barcodescanner.Util;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.sql.Time;
import java.util.ArrayList;


public class ResultPlayTriviaActivity extends AppCompatActivity {
    Button btn_result_playquiz;
    EditText txt_nombre_enviar;
    TextView txt_puntos;
    TextView txt_aciertos;
    TextView txt_fallos;
    TextView txt_tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_play_trivia);
        getSupportActionBar().setTitle("Resultado Quiz");
        //call identificadores
        btn_result_playquiz = (Button) findViewById(R.id.btn_result_playquiz);
        txt_nombre_enviar = (EditText) findViewById(R.id.txt_nombre_enviar);
        txt_aciertos=(TextView) findViewById(R.id.txt_aciertos);
        txt_fallos=(TextView) findViewById(R.id.txt_fallos);
        txt_puntos=(TextView) findViewById(R.id.txt_puntos);
        txt_tiempo=(TextView) findViewById(R.id.txt_tiempo);

        btn_result_playquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txt_nombre_enviar.getText().toString();
                if (TextUtils.isEmpty(nombre)) {
                    txt_nombre_enviar.setError("Ingrese su nombre");
                } else {
                    registrarResultTrivia(); //llamar al metodo registrar resultados de la trivia
                    Intent intent = new Intent(ResultPlayTriviaActivity.this, RankingTriviaActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) ;
        //Toast.makeText(getApplicationContext(), "back press enable", Toast.LENGTH_LONG).show();
        return false;
    }

    public void registrarResultTrivia() {
        String campo_nombre = txt_nombre_enviar.getText().toString();
        String campo_puntos = txt_puntos.getText().toString();
        String campo_fallos = txt_fallos.getText().toString();
        String campo_aciertos = txt_aciertos.getText().toString();
        String campo_tiempo = txt_tiempo.getText().toString();
        int puntos=Integer.parseInt(campo_puntos);
        int fallos=Integer.parseInt(campo_fallos);
        int aciertos=Integer.parseInt(campo_aciertos);
        int tiempo=Integer.parseInt(campo_tiempo);
        //crate el objeto rankig de envio de datos
        Ranking ranking= new Ranking(1,campo_nombre,tiempo,fallos,aciertos,puntos);

        Api.post(getApplicationContext(), "ranking/create", ranking,new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "Error en conexion", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "Datos enviados", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void resultadoRankingTrivia(){

        ListRankigFragment form = new ListRankigFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragment, form);
        transaction.addToBackStack(null).commit();
        setTitle("Resultado Trivia");
    }
}
