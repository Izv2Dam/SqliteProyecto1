package com.izv.dam.sqliteproyecto1.basedatos;

import android.provider.BaseColumns;

public class Contrato{

    private Contrato (){
    }

    public static abstract class TablaPelicula implements BaseColumns{
        public static final String TABLA = "pelicula";
        public static final String TITULO = "titulo";
        public static final String DIRECTOR = "director";
        public static final String GENERO = "genero";
        public static final String ESTRENO= "estreno";
    }
}