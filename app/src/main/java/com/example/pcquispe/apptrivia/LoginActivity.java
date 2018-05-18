package com.example.pcquispe.apptrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText usermane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usermane=(EditText) findViewById(R.id.txt_username);
        //capturar datos
        Bundle datos = this.getIntent().getExtras();
        final String info = datos.getString("infoKey");
        usermane.setText(info);

        btn_login=(Button) findViewById(R.id.btnlogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnlogin:
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });

    }
}
