package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button btnEjer1,btnEjer2,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEjer1 = findViewById(R.id.btnEjer1);
        btnEjer2 = findViewById(R.id.btnEjer2);
        btnSalir = findViewById(R.id.btnSalirAPP);
        eventos();
    }


    private void eventos(){
        btnEjer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Ejer1.class);
                startActivity(i);
            }
        });
        btnEjer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Ejer2.class);
                startActivity(i);
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