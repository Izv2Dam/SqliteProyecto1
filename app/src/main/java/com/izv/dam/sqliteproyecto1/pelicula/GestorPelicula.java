package com.izv.dam.sqliteproyecto1.pelicula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.izv.dam.sqliteproyecto1.basedatos.Ayudante;
import com.izv.dam.sqliteproyecto1.basedatos.Contrato;
import java.util.ArrayList;
import java.util.List;

public class GestorPelicula {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorPelicula(Context c){
        Log.v("SQLAAD","constructor del gestor de películas");
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }

    public long insert(Pelicula p) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPelicula.TITULO, p.getTitulo());
        valores.put(Contrato.TablaPelicula.DIRECTOR, p.getDirector());
        valores.put(Contrato.TablaPelicula.GENERO, p.getGenero());
        valores.put(Contrato.TablaPelicula.ESTRENO, p.getEstreno());
        long id = bd.insert(Contrato.TablaPelicula.TABLA, null, valores);
        return id;
    }

    public int delete(Pelicula p) {
        return delete(p.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaPelicula._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaPelicula.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Pelicula p){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPelicula.TITULO, p.getTitulo());
        valores.put(Contrato.TablaPelicula.DIRECTOR, p.getDirector());
        valores.put(Contrato.TablaPelicula.GENERO, p.getGenero());
        valores.put(Contrato.TablaPelicula.ESTRENO, p.getEstreno());
        String condicion = Contrato.TablaPelicula._ID + " = ?";
        String[] argumentos = { p.getId() + "" };
        int cuenta = bd.update(Contrato.TablaPelicula.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public Pelicula getRow(Cursor c) {
        Pelicula p = new Pelicula();
        p.setId(c.getLong(c.getColumnIndex(Contrato.TablaPelicula._ID)));
        p.setTitulo(c.getString(c.getColumnIndex(Contrato.TablaPelicula.TITULO)));
        p.setDirector(c.getString(c.getColumnIndex(Contrato.TablaPelicula.DIRECTOR)));
        p.setGenero(c.getString(c.getColumnIndex(Contrato.TablaPelicula.GENERO)));
        p.setEstreno(c.getInt(c.getColumnIndex(Contrato.TablaPelicula.ESTRENO)));
        return p;
    }

    public Pelicula getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaPelicula.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaPelicula.TITULO+", "+Contrato.TablaPelicula.ESTRENO);
        return cursor;
    }

    public List<Pelicula> select(String condicion, String[] parametros) {
        List<Pelicula> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Pelicula p;
        while (cursor.moveToNext()) {
            p = getRow(cursor);
            la.add(p);
        }
        cursor.close();
        return la;
    }

    public List<Pelicula> select() {
        return select(null,null);
    }
}