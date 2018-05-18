package com.example.pcquispe.apptrivia.fragmentos;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcquispe.apptrivia.R;
import com.example.pcquispe.apptrivia.adaptadores.PreguntaAdapter;
import com.example.pcquispe.apptrivia.entidades.Pregunta;
import com.example.pcquispe.apptrivia.http.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListRankigFragment extends Fragment {

    ListView listaelementos;
    ProgressDialog progressDoalog, progreso;
    ArrayList<Pregunta> lista = new ArrayList();
    public ListRankigFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_rankig, container, false);
        listaelementos = (ListView) view.findViewById(R.id.listado_ranking);
        metodoGet();
        return view;
    }
    public void metodoGet() {
        process();
        Api.get(getActivity(), "ranking/getdata", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getActivity(), "error de Conexion", Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                try {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    lista = new Gson().fromJson(responseString, new TypeToken<ArrayList<Pregunta>>() {}.getType());
                    listaelementos.setAdapter(new PreguntaAdapter(getActivity(), lista));

                    //listaelementos.setOnItemClickListener(ListRankigFragment.this);
                    listaelementos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                            return false;
                        }
                    });
                    listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getActivity(), "item" + i, Toast.LENGTH_SHORT).show();

                        }

                    });
                    progreso.hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    //metodo de carga
    public void process() {
        progreso = new ProgressDialog(getActivity());
        progreso.setMessage("Procesando...");
        progreso.show();
    }
}
