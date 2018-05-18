package com.example.pcquispe.apptrivia.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pcquispe.apptrivia.R;
import com.example.pcquispe.apptrivia.entidades.Pregunta;

import java.util.ArrayList;

/**
 * Created by PCQUISPE on 2/23/2018.
 */

public class PreguntaAdapter extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Pregunta> lista;

    public PreguntaAdapter(Activity activity, ArrayList<Pregunta> lista) {
        this.activity = activity;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1= view;
        if (view1==null){
            LayoutInflater layoutInflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1=layoutInflater.inflate(R.layout.fragment_lista_item,null);
        }
        Pregunta pregunta=lista.get(i);
        TextView nombre=(TextView) view1.findViewById(R.id.tex_nombre_item);
        nombre.setText(pregunta.getNombre());
        return view1;
    }
}
