package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Buscar extends AppCompatActivity {

    private Button btnBusqueda;
    private RadioButton rbTitulo,rbAutor,rbEditorial;
    private SQLiteDatabase db;
    private RadioGroup rG;
    private EditText etCriterio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        etCriterio = findViewById(R.id.etBusqueda);
        btnBusqueda = findViewById(R.id.btnBusqueda);
        rbAutor = findViewById(R.id.rbAutor);
        rbEditorial = findViewById(R.id.rbEditorial);
        rbTitulo = findViewById(R.id.rbTitulo);
        rG = findViewById(R.id.radioGroup);
        btnBusqueda.setVisibility(View.INVISIBLE);
        etCriterio.setVisibility(View.INVISIBLE);
        BibliotecaSQLHelper usdbh = new BibliotecaSQLHelper(Buscar.this, "DBUsuarios", null, 1);
        db = usdbh.getWritableDatabase();
        eventos();

    }


    private void eventos(){
        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etCriterio.getText().toString().equals("")){
                    if(rG.getCheckedRadioButtonId() == rbTitulo.getId()){
                        Cursor c =db.rawQuery("SELECT id, titulo, autor ,isbn,editorial,numPag ,leido FROM biblioteca where titulo = '"+
                                etCriterio.getText().toString() +"'", null);
                        Libro [] contactos = new Libro[c.getCount()];
                        int j = 0;
                        if (c.moveToFirst()){
                            do {
                                int codigo = c.getInt(0);
                                String titulo =c.getString(1);
                                String autor = c.getString(2);
                                int isbn = c.getInt(3);
                                String editorial = c.getString(4);
                                int numpag =c.getInt(5);
                                boolean leido = (1==c.getInt(6))? true :false ;
                                contactos[j] = new Libro(codigo,titulo,autor,isbn,editorial,numpag,leido);
                                j++;
                            }while (c.moveToNext());
                        }
                        if(contactos.length > 0){
                            Intent intent = new Intent(Buscar.this, VerLibro.class);
                            intent.putExtra("libro", contactos[0]);
                            startActivity(intent);
                        }else{
/*
                            Toast.makeText(this,"",1)
*/
                        }
                    }else if(rG.getCheckedRadioButtonId() == rbEditorial.getId()){
                        Cursor c =db.rawQuery("SELECT id, titulo, autor ,isbn,editorial,numPag ,leido FROM biblioteca where editorial = '"+
                                etCriterio.getText().toString() +"'", null);
                        Libro [] contactos = new Libro[c.getCount()];
                        int j = 0;
                        if (c.moveToFirst()){
                            do {
                                int codigo = c.getInt(0);
                                String titulo =c.getString(1);
                                String autor = c.getString(2);
                                int isbn = c.getInt(3);
                                String editorial = c.getString(4);
                                int numpag =c.getInt(5);
                                boolean leido = (1==c.getInt(6))? true :false ;
                                contactos[j] = new Libro(codigo,titulo,autor,isbn,editorial,numpag,leido);
                                j++;
                            }while (c.moveToNext());
                        }
                        Intent intent = new Intent(Buscar.this, VerLibros.class);
                        intent.putExtra("libro", contactos);
                        startActivity(intent);
                    }else{
                        Cursor c =db.rawQuery("SELECT id, titulo, autor ,isbn,editorial,numPag ,leido FROM biblioteca where autor = '"+
                                etCriterio.getText().toString() +"'", null);
                        Libro [] contactos = new Libro[c.getCount()];
                        int j = 0;
                        if (c.moveToFirst()){
                            do {
                                int codigo = c.getInt(0);
                                String titulo =c.getString(1);
                                String autor = c.getString(2);
                                int isbn = c.getInt(3);
                                String editorial = c.getString(4);
                                int numpag =c.getInt(5);
                                boolean leido = (1==c.getInt(6))? true :false ;
                                contactos[j] = new Libro(codigo,titulo,autor,isbn,editorial,numpag,leido);
                                j++;
                            }while (c.moveToNext());
                        }
                        Intent intent = new Intent(Buscar.this, VerLibros.class);
                        intent.putExtra("libro", contactos);
                        startActivity(intent);
                    }
                }
            }
        });
        rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnBusqueda.setVisibility(View.VISIBLE);
                etCriterio.setVisibility(View.VISIBLE);
            }
        });
    }

}