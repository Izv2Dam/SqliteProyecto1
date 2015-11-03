package com.izv.dam.sqliteproyecto1.pelicula;

import android.database.Cursor;

import com.izv.dam.sqliteproyecto1.basedatos.Contrato;

public class Pelicula {

    private long id;
    private String titulo;
    private String director;
    private String genero;
    private int estreno;

    public Pelicula(long id, String titulo,String director, String genero, int estreno) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.estreno = estreno;
    }

    public Pelicula() {
        this(0,"","","",0);
    }

    public void set(Cursor c){
        setId(c.getLong(c.getColumnIndex(Contrato.TablaPelicula._ID)));
        setTitulo(c.getString(c.getColumnIndex(Contrato.TablaPelicula.TITULO)));
        setDirector(c.getString(c.getColumnIndex(Contrato.TablaPelicula.DIRECTOR)));
        setGenero(c.getString(c.getColumnIndex(Contrato.TablaPelicula.GENERO)));
        setEstreno(c.getInt(c.getColumnIndex(Contrato.TablaPelicula.ESTRENO)));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    public void setEstreno(String estreno){

        int año = 0;
        try{
            año = Integer.parseInt(estreno);
        }catch (NumberFormatException e) {
        }
        setEstreno(año);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return id == pelicula.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", genero='" + genero + '\'' +
                ", estreno=" + estreno +
                '}';
    }
}