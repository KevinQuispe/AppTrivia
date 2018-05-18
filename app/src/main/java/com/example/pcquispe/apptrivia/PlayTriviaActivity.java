package com.example.pcquispe.apptrivia;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayTriviaActivity extends AppCompatActivity  implements View.OnClickListener {

    Button btn_empezar, btn_salir;

    TextView txt_timpo;
    Button btn_start;
    private CountDownTimer timercounter;
    private long timelefmillisecons = 60000;
    private boolean timerunnnig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_trivia);

        getSupportActionBar().setTitle("Play Trivia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_empezar = (Button) findViewById(R.id.btn_empezar);
        btn_salir = (Button) findViewById(R.id.btn_salir);
        btn_empezar.setOnClickListener(this);
        btn_salir.setOnClickListener(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK);
        return false;
    }
    //metodo
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_empezar:
                Intent intent = new Intent(PlayTriviaActivity.this, EmpezarTriviaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_salir:
                //Intent myIntent2 = new Intent(getApplicationContext(), PlayTriviaActivity.class);
                //PlayTriviaActivity.this.finish();
                Intent intent1 = new Intent(getApplicationContext(), ReaderActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
        }
    }
}