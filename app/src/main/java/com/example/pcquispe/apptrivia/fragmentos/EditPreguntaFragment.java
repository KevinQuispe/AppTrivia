package com.example.pcquispe.apptrivia.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pcquispe.apptrivia.R;

public class EditPreguntaFragment extends Fragment {

    //Declare variables
    EditText txt_nombre,txt_respuesta1,txt_respuesta2,txt_respuesta3,txt_respuesta4;
    Button btn_editar;
    public EditPreguntaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_pregunta, container, false);
        txt_nombre=(EditText) view.findViewById(R.id.txt_preguntaedit);
        txt_respuesta1=(EditText) view.findViewById(R.id.txt_respuestaedit1);
        txt_respuesta2=(EditText) view.findViewById(R.id.txt_respuestaedit2);
        txt_respuesta3=(EditText) view.findViewById(R.id.txt_respuestaedit3);
        txt_respuesta4=(EditText) view.findViewById(R.id.txt_respuestaedit4);

        txt_nombre.setText(getArguments().getString("nombreKey"));
        txt_respuesta1.setText(getArguments().getString("respuestaKey"));

        btn_editar=(Button) view.findViewById(R.id.btn_edit);
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
