package com.izv.dam.sqliteproyecto1.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.izv.dam.sqliteproyecto1.R;
import com.izv.dam.sqliteproyecto1.pelicula.GestorPelicula;
import com.izv.dam.sqliteproyecto1.pelicula.Pelicula;

import java.util.List;

public class Alta extends AppCompatActivity {

    private android.widget.TextView tvTexto;
    private GestorPelicula gestor;
    private android.widget.EditText etTitulo;
    private android.widget.EditText etDirector;
    private android.widget.EditText etGenero;
    private android.widget.EditText etEstreno;
    private android.widget.EditText etId;
    private android.widget.Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);
        init();
    }

    private void init(){
        this.btAdd = (Button) findViewById(R.id.btAdd);
        this.etEstreno = (EditText) findViewById(R.id.etEstreno);
        this.etGenero = (EditText) findViewById(R.id.etGenero);
        this.etDirector = (EditText) findViewById(R.id.etDirector);
        this.etTitulo = (EditText) findViewById(R.id.etTitulo);
        this.tvTexto = (TextView) findViewById(R.id.tvTexto);
        gestor = new GestorPelicula(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gestor.open();
        Log.v("APLICACION", "Resume Alta Open");
    }

    @Override
    protected void onPause() {
        super.onPause();
        gestor.close();
        Log.v("APLICACION", "Resume Alta Close");
    }

    public void add(View v){
        Pelicula p = new Pelicula();
        p.setDirector(etDirector.getText().toString().trim());
        p.setTitulo(etTitulo.getText().toString().trim());
        p.setGenero(etGenero.getText().toString().trim());
        p.setEstreno(etEstreno.getText().toString().trim());
        if(!p.getTitulo().isEmpty()) {
            long r = gestor.insert(p);
            if (r>0) {
                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putLong("id", r);
                i.putExtras(bundle);
                setResult(Activity.RESULT_OK, i);
                finish();
            }else {
                tostada("No se ha podido insertar");
            }
        } else{
            tostada("El titulo es obligatorio");
        }
    }

    private void tostada(int i){
        tostada(i + "");
    }
    private void tostada(String i){
        Toast.makeText(this,i,Toast.LENGTH_LONG).show();
    }


    private void view(){
        List<Pelicula> l = gestor.select();
        tvTexto.setText("");
        for(Pelicula p: l){
            tvTexto.append(p.toString());
        }
    }

    public void borrar(View v){
        int id = Integer.parseInt(etId.getText().toString());
        int r = gestor.delete(id);
        tostada(r);
        view();
    }

    public void editar(View v){
        Pelicula p = new Pelicula();
        p.setDirector(etDirector.getText().toString());
        p.setTitulo(etTitulo.getText().toString());
        p.setGenero(etGenero.getText().toString());
        p.setEstreno(etEstreno.getText().toString());
        int id = Integer.parseInt(etId.getText().toString());
        p.setId(id);
        int r = gestor.update(p);
        tostada(r);
        view();
    }
}