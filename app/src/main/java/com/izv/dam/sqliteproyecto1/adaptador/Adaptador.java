package com.izv.dam.sqliteproyecto1.adaptador;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.izv.dam.sqliteproyecto1.R;
import com.izv.dam.sqliteproyecto1.pelicula.GestorPelicula;
import com.izv.dam.sqliteproyecto1.pelicula.Pelicula;

public class Adaptador extends CursorAdapter{

    public Adaptador (Context co, Cursor cu) {
        super(co, cu, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1 = (TextView)view.findViewById(R.id.tvParte1);
        TextView tv2 = (TextView)view.findViewById(R.id.tvParte2);
        Pelicula p = new Pelicula();
        p.set(cursor);
        tv1.setText(p.getDirector() + " " + p.getTitulo());
        tv2.setText(p.getGenero() + " " + p.getEstreno());
    }
}
