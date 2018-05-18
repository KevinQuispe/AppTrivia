package com.example.pcquispe.apptrivia;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcquispe.apptrivia.entidades.Pregunta;
import com.example.pcquispe.apptrivia.entidades.Preguntas;
import com.example.pcquispe.apptrivia.entidades.Respuesta;
import com.example.pcquispe.apptrivia.http.Api;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class EmpezarTriviaActivity extends AppCompatActivity  implements View.OnClickListener{
    //variables para lista
    ProgressDialog progressDoalog, progreso;
    ArrayList<Pregunta> preguntas = new ArrayList();
    List<Respuesta> respuestas;

    //Declare variablles Empezar Trivia
    TextView nvidas;//para el numero de vidas
    TextView txt_puntaje; //para el numero de puntos
    private int puntaje; // puntaje
    private int aciertos; // número de aciertos
    private int fallos; // número de fallos
    private int id; // id de la pregunta actual
    private int limite; // número de preguntas que se responderán
    private int numero_vidas; // número de vidas
    private int maxVidas; //Las vidas con las que se empieza
    //variables pregunta respuesta
    private Pregunta pregunta; // pregunta actual
    private  TextView txt_pregunta;
    private Button btnrespuesta1; // Botón primera opción
    private Button btnrespuesta2; // Botón segunda opción
    private Button btnrespuesta3; // Botón tercera opción
    private Button btnrespuesta4; // Botón cuarta opción
    private CountDownTimer cronometro; // Cronómetro con la cuenta atrás
    private static final long INTERVALO_CLICK = 1000;
    private long mLastClickTime = 0; // Variable para controlar el tiempo entre pulsaciones

    //variables timer
    TextView txt_tiempo;
    private CountDownTimer timercounter;
    private long timelefmillisecons = 60000;
    private boolean timerunnnig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empezar_trivia);
        getSupportActionBar().setTitle("Empezar Trivia");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //call identificadores
        nvidas = (TextView) findViewById(R.id.txt_numero_vidas);
        txt_tiempo = (TextView) findViewById(R.id.txt_tiempo);
        txt_pregunta = (TextView) findViewById(R.id.txt_pregunta);
        btnrespuesta1 = (Button) findViewById(R.id.btn_respuesta1);
        btnrespuesta2 = (Button) findViewById(R.id.btn_respuesta2);
        btnrespuesta3 = (Button) findViewById(R.id.btn_respuesta3);
        btnrespuesta4 = (Button) findViewById(R.id.btn_respuesta4);

        btnrespuesta1.setOnClickListener(this);
        btnrespuesta2.setOnClickListener(this);
        btnrespuesta3.setOnClickListener(this);
        btnrespuesta4.setOnClickListener(this);
        //init variables y metodo
        iniciarJuego();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK);
        return false;
    }

    //metodo iniciar juego
    public void iniciarJuego() {
        //startStop();
        //updateTimer();
        cuentaAtras();
        //Init variables
        numero_vidas = 5;
        puntaje = 0;
        String vidas = String.valueOf(numero_vidas);
        nvidas.setText("Vidas:" + vidas);
        String puntos = String.valueOf(puntaje);
    }

    //timer metodos
    public void startStop() {
        if (timerunnnig) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        timercounter = new CountDownTimer(timelefmillisecons, 1000) {
            @Override
            public void onTick(long l) {
                timelefmillisecons = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(EmpezarTriviaActivity.this, ResultPlayTriviaActivity.class);
                startActivity(intent);
            }
        }.start();
        timerunnnig = true;
    }

    public void stopTimer() {
        timercounter.cancel();
        timerunnnig = false;
    }
    //set time
    public void updateTimer() {
        int minutes = (int) timelefmillisecons / 60000;
        int seconds = (int) timelefmillisecons % 60000 / 1000;
        String timetext = "";
        timetext = "" + minutes;
        timetext += ":";
        if (seconds <10) timetext += "0";
        timetext += seconds;
        txt_tiempo.setText(timetext);
    }

    //cuentas atras
    private void cuentaAtras() {
        cronometro = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timelefmillisecons = millisUntilFinished;
                updateTimer();
                txt_tiempo.setText(Long.toString(millisUntilFinished / 1000));
                if (millisUntilFinished <10) {
                    txt_tiempo.setTextColor(getResources().getColor(R.color.buttonErrorColor));
                }
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(EmpezarTriviaActivity.this, ResultPlayTriviaActivity.class);
                startActivity(intent);
            }
        }.start();
        timerunnnig = true;
    }
    //metodo para cargar data de preguntas
    public void cargarDataPreguntas() {

        Api.get(getApplicationContext(), "pregunta/getdata", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "error de Conexion", Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_respuesta1:
                break;

        }
    }
}
