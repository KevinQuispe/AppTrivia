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
import com.example.pcquispe.apptrivia.entidades.Ranking;

import java.util.ArrayList;

/**
 * Created by PCQUISPE on 3/13/2018.
 */

public class RankingAdapter  extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Ranking> listview_ranking;

    public RankingAdapter(Activity activity, int simple_expandable_list_item_1, ArrayList<Ranking> listview_ranking) {
        this.activity = activity;
        this.listview_ranking = listview_ranking;

    }
    @Override
    public int getCount() {
        return listview_ranking.size();
    }

    @Override
    public Object getItem(int i) {
        return listview_ranking.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return listview_ranking.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1= view;
        if (view1==null){
            LayoutInflater layoutInflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1=layoutInflater.inflate(R.layout.fragment_list_rankig,null);
        }
        Ranking ranking=listview_ranking.get(i);
        TextView nombre=(TextView) view1.findViewById(R.id.txt_nombre_ranking);
        nombre.setText(ranking.getNombre());
        return view1;
    }
}
