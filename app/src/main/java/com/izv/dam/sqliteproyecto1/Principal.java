package com.izv.dam.sqliteproyecto1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.izv.dam.sqliteproyecto1.actividades.Alta;
import com.izv.dam.sqliteproyecto1.adaptador.Adaptador;
import com.izv.dam.sqliteproyecto1.pelicula.GestorPelicula;
import com.izv.dam.sqliteproyecto1.pelicula.Pelicula;
import com.izv.dam.sqliteproyecto1.util.Dialogo;
import com.izv.dam.sqliteproyecto1.util.OnDialogoListener;

public class Principal extends AppCompatActivity {

    /***************************************************/

    private GestorPelicula gp;
    private Adaptador adp;
    private ListView lv;
    private Cursor c;
    private final static int ALTA=1;

    /***************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        gp = new GestorPelicula(this);
        lv = (ListView)findViewById(R.id.lv);
        init();
    }

    @Override
    protected void onResume() {
        gp.open();
        Log.v("APLICACION", "Resume Principal Open");
        c = gp.getCursor();
        adp = new Adaptador(this, c);
        lv.setAdapter(adp);
        super.onResume();
    }

    @Override
    protected void onPause() {
        gp.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("APLICACION", "Result Principal");
        if(resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case ALTA:
                    //Actualizar cursor
                    //c=gp.getCursor();
                    //adp.changeCursor(c);
                    //tostada("Insertado correctamente");
                    break;
            }
        }
    }

    /***************************************************/

    private void tostada(int i){
        tostada(i + "");
    }

    private void tostada(String i){
        Toast.makeText(this, i, Toast.LENGTH_LONG).show();
    }

    public void insertar(View v){
        Intent i=new Intent(this,Alta.class);
        startActivityForResult(i, ALTA);
    }

    private void init(){

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Pelicula p = new Pelicula();
                p.set(c);//
                final OnDialogoListener odl=new OnDialogoListener() {
                    @Override
                    public void onPreShow(View v) {
                        Button b=(Button) v.findViewById(R.id.btAdd);
                        b.setVisibility(View.GONE);
                        EditText etTitulo = (EditText)v.findViewById(R.id.etTitulo);
                        etTitulo.setText(p.getTitulo());
                        EditText etDirector=(EditText) v.findViewById(R.id.etDirector);
                        etDirector.setText(p.getDirector());
                        EditText etGenero=(EditText) v.findViewById(R.id.etGenero);
                        etGenero.setText(p.getGenero());
                        EditText etEstreno=(EditText) v.findViewById(R.id.etEstreno);
                        etEstreno.setText(p.getEstreno()+"");

                    }

                    @Override
                    public void onOkSelecter(View v) {
                        tostada("Pelicula insertada");
                        EditText etTitulo = (EditText)v.findViewById(R.id.etTitulo);
                        EditText etDirector=(EditText) v.findViewById(R.id.etDirector);
                        EditText etGenero=(EditText) v.findViewById(R.id.etGenero);
                        EditText etEstreno=(EditText) v.findViewById(R.id.etEstreno);
                        p.setTitulo(etTitulo.getText().toString());
                        p.setDirector(etDirector.getText().toString());
                        p.setGenero(etGenero.getText().toString());
                        p.setEstreno(etEstreno.getText().toString());
                        int r=gp.update(p);
                        tostada("Resultado es "+r);
                        //Actualizar cursor
                        c=gp.getCursor();
                        adp.changeCursor(c);

                    }

                    @Override
                    public void onCancelSelecter(View v) {
                        tostada("Cancelado");
                    }
                };
                tostada(p.toString());
                Dialogo d=new Dialogo(Principal.this,R.layout.alta,odl);
                d.show();
            }
        });
    }
}