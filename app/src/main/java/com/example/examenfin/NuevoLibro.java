package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NuevoLibro extends AppCompatActivity {

    private EditText etEditorial,etAutor,etTitulo,etNumPag,etIsbn;
    private CheckBox cbLeido;
    private Button btnCancelar,btnInsert,btnSalir;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);
        etEditorial = findViewById(R.id.etEditorial);
        etAutor = findViewById(R.id.etAutor);
        etIsbn = findViewById(R.id.etIsbn);
        etTitulo = findViewById(R.id.etTitulo);
        etNumPag = findViewById(R.id.etNumPag);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnInsert = findViewById(R.id.btnInsert);
        btnSalir = findViewById(R.id.btnSalirNuevo);
        cbLeido = findViewById(R.id.cbLeido);
        BibliotecaSQLHelper usdbh = new BibliotecaSQLHelper(this, "DBUsuarios", null, 1);
        db = usdbh.getWritableDatabase();//Si hemos abierto correctamente la base de datos
        eventos();
    }

    private void eventos() {
/*
        String i = "id  nombre TEXT, autor TEXT, isbn INTEGER,editorial TEXT,numPag INTEGER,leido INTEGER)";
*/
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hayTexto()){
                    int leido = cbLeido.isChecked() ? 1:0;
                    db.execSQL("INSERT INTO biblioteca (titulo, autor,isbn,editorial,numPag,leido)" +
                            " VALUES ('" + etTitulo.getText().toString() + "' ,'" + etAutor.getText().toString() +
                                        "','" + etIsbn.getText().toString() +  "','"+etEditorial.getText().toString()+
                            "','"+etNumPag.getText().toString()+"','"+leido+"')");
                    todoA0();
                    Toast.makeText(NuevoLibro.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(NuevoLibro.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoA0();
            }
        });
    }

    private boolean hayTexto(){
        if (etEditorial.getText().toString().equals(""))
            return false;
        if (etAutor.getText().toString().equals(""))
        return false;
        if (etIsbn.getText().toString().equals(""))
        return false;
        if (etTitulo.getText().toString().equals(""))
        return false;
        if (etNumPag.getText().toString().equals(""))
        return false;

        return true;
    }
    private void todoA0(){
        etEditorial.setText("");
        etAutor.setText("");
        etIsbn.setText("");
        etTitulo.setText("");
        etNumPag.setText("");
        cbLeido.setChecked(false);
    }
}