package com.izv.dam.sqliteproyecto1.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "filmoteca.sqlite";
    public static final int DATABASE_VERSION = 2;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        Log.v("SQLAAD", "constructor del ayudante");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        Log.v("SQLAAD","on upgrade");
        String sql="drop table if exists "
                + Contrato.TablaPelicula.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)
        Log.v("SQLAAD","on create");
        String sql;
        sql="create table "+Contrato.TablaPelicula.TABLA+
                " ("+
                  Contrato.TablaPelicula._ID + " integer primary key autoincrement, "+
                  Contrato.TablaPelicula.TITULO+" text, "+
                  Contrato.TablaPelicula.DIRECTOR+" text, "+
                  Contrato.TablaPelicula.GENERO +" text," +
                  Contrato.TablaPelicula.ESTRENO+" integer "+
                ")";
        Log.v("SQLAAD", sql);
        db.execSQL(sql);
    }
}