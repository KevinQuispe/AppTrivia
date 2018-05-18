package com.example.pcquispe.apptrivia.fragmentos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcquispe.apptrivia.R;
import com.example.pcquispe.apptrivia.adaptadores.PreguntaAdapter;
import com.example.pcquispe.apptrivia.entidades.Pregunta;
import com.example.pcquispe.apptrivia.entidades.Respuesta;
import com.example.pcquispe.apptrivia.http.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Properties;

public class ListaPreguntaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    //DECLARE VARIABLES
    ListView listaelementos;
    ProgressDialog progressDoalog, progreso;
    ArrayList<Pregunta> lista = new ArrayList();

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDoalog.incrementProgressBy(1);
        }
    };

    public ListaPreguntaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_pregunta, container, false);
        listaelementos = (ListView) view.findViewById(R.id.text_listado);
        metodoGet();
        return view;
    }

    //metodo register
    public void metodoGet() {
        process();
        Api.get(getActivity(), "pregunta/getdata", new TextHttpResponseHandler() {
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

                    listaelementos.setOnItemClickListener(ListaPreguntaFragment.this);
                    listaelementos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            onItemLongCLick(i);
                            return false;
                        }
                    });
                    listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getActivity(), "item" + i, Toast.LENGTH_SHORT).show();
                            respustaCorrecta(lista.get(i));
                        }

                    });
                    progreso.hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void respustaCorrecta(final Pregunta pregunta) {
        //final int post = Position;
        Fragment fragment = null;
        fragment = new ListaPreguntaCorrectaFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putBoolean("Pregunta", true);
        bundle.putSerializable("Pregunta", pregunta);
        bundle.putString("nombreKey", pregunta.getNombre().toString());
        bundle.putString("respuestaKey", pregunta.getNombre().toString());
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.contenedorFragment, fragment).addToBackStack(null).commit();
    }

    //metodo onItemLongCLick
    private void onItemLongCLick(int position) {
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿Que desea hacer?").setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editar(lista.get(pos));
            }
        })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminar(lista.get(pos));
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    //metodo editarDetalle
    public void editar(final Pregunta pregunta) {
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        Fragment fragment = null;
        fragment = new EditPreguntaFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putBoolean("editar", true);
        bundle.putSerializable("Pregunta", pregunta);
        bundle.putString("nombreKey", pregunta.getNombre().toString());
        bundle.putString("respuestaKey", pregunta.getNombre().toString());
        //bundle.putSerializable("res_1",pregunta.getRespuestas());
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.contenedorFragment, fragment).addToBackStack(null).commit();

    }

    //metodo eliminar
    public void eliminar(final Pregunta pregunta) {
        System.out.println("Se eliminara " + pregunta.getId());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿Esta seguro que desea eliminar?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Eliminando...");
                        progressDialog.show();

                        Api.post(getContext(), "pregunta/delete/" + pregunta.getId(), pregunta, new TextHttpResponseHandler() {
                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                progressDialog.dismiss();
                                System.out.println(responseString);
                            }

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                progressDialog.dismiss();
                                Properties response = new Gson().fromJson(responseString, Properties.class);
                                response.remove(pregunta.getId());
                                String mensaje = response.getProperty("mensaje", "Se eliminó.");
                                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                                metodoGet();
                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //metodo loading
    public void loading() {
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(50);
        progressDoalog.setMessage("Procesando...");
        //progressDoalog.setTitle("ProgressDialog");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDoalog.getProgress() <= progressDoalog.getMax()) {
                        Thread.sleep(50);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDoalog.getProgress() == progressDoalog.getMax()) {
                            progressDoalog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //metodo de carga
    public void process() {
        progreso = new ProgressDialog(getActivity());
        progreso.setMessage("Procesando...");
        progreso.show();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
