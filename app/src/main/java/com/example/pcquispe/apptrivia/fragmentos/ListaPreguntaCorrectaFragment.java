package com.example.pcquispe.apptrivia.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pcquispe.apptrivia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPreguntaCorrectaFragment extends Fragment {

    //declare variables
    TextView txt_nombre,txt_respuesta1,txt_respuesta2,txt_respuesta3,txt_respuesta4;
    Button btn_editar;

    public ListaPreguntaCorrectaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_lista_pregunta_correcta, container, false);
        txt_nombre=(TextView) view.findViewById(R.id.tex_pregunta_correcta);
        txt_respuesta1=(TextView) view.findViewById(R.id.txt_respuesta_correcta);

        txt_nombre.setText(getArguments().getString("nombreKey"));
        txt_respuesta1.setText(getArguments().getString("respuestaKey"));
        return  view;

    }

}
