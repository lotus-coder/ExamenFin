package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerLibro extends AppCompatActivity {


    private TextView titulo,resto;
    private Button btnSalir,btnEliminar;
    private Libro l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libro);

        btnSalir = findViewById(R.id.btnSalirVer);
        btnEliminar = findViewById(R.id.btnEliminar);
        titulo = findViewById(R.id.tTitulo);
        resto = findViewById(R.id.tResto);
        Bundle extras = getIntent().getExtras();

        l = (Libro) extras.getSerializable("libro");

        titulo.setText(l.getTitulo());
        String s = l.getAutor()+"\n"+l.getIsbn()+"\n"+l.getEditoria()+"\n"+l.getNumPag();
        s+=l.isLeido()? "Leido":"No Leido";
        resto.setText(s);
        eventos();
    }

    private void eventos() {
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BibliotecaSQLHelper usdbh = new BibliotecaSQLHelper(VerLibro.this, "DBUsuarios", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();
                db.execSQL("DELETE FROM biblioteca where id = "+l.getId());
                db.close();
                finish();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}