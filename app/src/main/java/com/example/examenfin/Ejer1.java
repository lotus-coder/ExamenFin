package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ejer1 extends AppCompatActivity {

    private Button btnNuevo,btnListado,btnBuscar,btnSalir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer1);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnListado = findViewById(R.id.btnListar);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSalir = findViewById(R.id.btnSalirBD);
        eventos();
    }

    private void eventos() {
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ejer1.this,NuevoLibro.class);
                startActivity(i);
            }
        });
        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ejer1.this,ListarLibros.class);
                startActivity(i);
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ejer1.this,Buscar.class);
                startActivity(i);
            }
        });
    }
}