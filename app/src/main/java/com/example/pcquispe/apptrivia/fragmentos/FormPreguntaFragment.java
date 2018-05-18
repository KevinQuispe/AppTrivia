
package com.example.pcquispe.apptrivia.fragmentos;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcquispe.apptrivia.R;
import com.example.pcquispe.apptrivia.entidades.Pregunta;
import com.example.pcquispe.apptrivia.entidades.Respuesta;
import com.example.pcquispe.apptrivia.http.Api;
import com.example.pcquispe.apptrivia.util.Config;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormPreguntaFragment extends Fragment implements View.OnClickListener {
    //DECLARE VARIABLES

    TextInputEditText txt_pregunta, txt_respuesta1, txt_respuesta2, txt_respuesta3, txt_respuesta4;
    TextInputEditText preguntaedit;
    CheckBox checkrespuesta1, checkrespuesta2, checkrespuesta3, checkrespuesta4;
    Button button;

    public FormPreguntaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_pregunta, container, false);
        preguntaedit = (TextInputEditText) view.findViewById(R.id.txt_pregunta);
        txt_respuesta1 = (TextInputEditText) view.findViewById(R.id.txt_respuesta1);
        txt_respuesta2 = (TextInputEditText) view.findViewById(R.id.txt_respuesta2);
        txt_respuesta3 = (TextInputEditText) view.findViewById(R.id.txt_respuesta3);
        txt_respuesta4 = (TextInputEditText) view.findViewById(R.id.txt_respuesta4);

        checkrespuesta1 = (CheckBox) view.findViewById(R.id.check_respuesta1);
        checkrespuesta2 = (CheckBox) view.findViewById(R.id.check_respuesta2);
        checkrespuesta3 = (CheckBox) view.findViewById(R.id.check_respuesta3);
        checkrespuesta4 = (CheckBox) view.findViewById(R.id.check_respuesta4);
        button = (Button) view.findViewById(R.id.btn_registrar);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_registrar:
                registrarPregunta();
                break;
        }
    }

    public void registrarPregunta() {
        String pregunta = preguntaedit.getText().toString();
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        StringBuffer result = new StringBuffer();
        String respuesta1 = txt_respuesta1.getText().toString();
        String respuesta2 = txt_respuesta2.getText().toString();
        String respuesta3 = txt_respuesta3.getText().toString();
        String respuesta4 = txt_respuesta4.getText().toString();

        if (TextUtils.isEmpty(pregunta)) {
            preguntaedit.setError("Ingrese Pregunta");
        }
        if (TextUtils.isEmpty(respuesta1)) {
            txt_respuesta1.setError("Ingrese Respuesta");
        }
        if (TextUtils.isEmpty(respuesta2)) {
            txt_respuesta2.setError("Ingrese Respuesta");
        }
        if (TextUtils.isEmpty(respuesta3)) {
            txt_respuesta3.setError("Ingrese Respuesta");
        }
        if (TextUtils.isEmpty(respuesta4)) {
            txt_respuesta4.setError("Ingrese Respuesta");

        } else if (((!checkrespuesta1.isChecked() && !checkrespuesta2.isChecked() && !checkrespuesta3.isChecked() && !checkrespuesta4.isChecked()))) {
            Toast.makeText(getActivity(), "Elija respuesta", Toast.LENGTH_SHORT).show();
        } else {
            result.append(txt_respuesta1.getText().toString()).append(checkrespuesta1.isChecked());
            result.append(txt_respuesta2.getText().toString()).append(checkrespuesta2.isChecked());
            result.append(txt_respuesta3.getText().toString()).append(checkrespuesta3.isChecked());
            result.append(txt_respuesta4.getText().toString()).append(checkrespuesta4.isChecked());

            respuestas.add(new Respuesta(0, respuesta1, checkrespuesta1.isChecked()));
            respuestas.add(new Respuesta(1, respuesta2, checkrespuesta2.isChecked()));
            respuestas.add(new Respuesta(2, respuesta3, checkrespuesta3.isChecked()));
            respuestas.add(new Respuesta(3, respuesta4, checkrespuesta4.isChecked()));

            Pregunta post = new Pregunta(1, pregunta, respuestas);

            Api.post(getActivity(), "pregunta/create", post, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    System.out.println(responseString);
                    Toast.makeText(getActivity(), "error en conexion", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    System.out.println(responseString);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public void fragmentEdit() {

    }

    //elije respuesta correcta correcta
    public void isCorrecta() {
        String pregunta = preguntaedit.getText().toString();
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        StringBuffer result = new StringBuffer();
        String respuesta1 = txt_respuesta1.getText().toString();
        String respuesta2 = txt_respuesta2.getText().toString();
        String respuesta3 = txt_respuesta3.getText().toString();
        String respuesta4 = txt_respuesta4.getText().toString();

        if (((!checkrespuesta1.isChecked() && !checkrespuesta2.isChecked() && !checkrespuesta3.isChecked() && !checkrespuesta4.isChecked()))) {
            Toast.makeText(getActivity(), "Seleccione respuesta", Toast.LENGTH_SHORT).show();
        } else {
            result.append(txt_respuesta1.getText().toString()).append(checkrespuesta1.isChecked());
            result.append(txt_respuesta2.getText().toString()).append(checkrespuesta2.isChecked());
            result.append(txt_respuesta3.getText().toString()).append(checkrespuesta3.isChecked());
            result.append(txt_respuesta4.getText().toString()).append(checkrespuesta4.isChecked());

            respuestas.add(new Respuesta(0, respuesta1, checkrespuesta1.isChecked()));
            respuestas.add(new Respuesta(1, respuesta2, checkrespuesta2.isChecked()));
            respuestas.add(new Respuesta(2, respuesta3, checkrespuesta3.isChecked()));
            respuestas.add(new Respuesta(3, respuesta4, checkrespuesta4.isChecked()));
            Pregunta post = new Pregunta(1, pregunta, respuestas);

            Api.post(getActivity(), "pregunta/create", post, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    System.out.println(responseString);
                    Toast.makeText(getActivity(), "error en conexion", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    System.out.println(responseString);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    //limpiarCampos();
                }
            });
        }
    }

    //limpiar campos
    public void limpiarCampos() {
        txt_pregunta.setText("");
        txt_respuesta1.setText("");
        txt_respuesta2.setText("");
        txt_respuesta3.setText("");
        txt_respuesta4.setText("");
    }
}
