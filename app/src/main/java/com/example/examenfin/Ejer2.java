package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Ejer2 extends AppCompatActivity {

    private Button btnVito,btnDono,btnBilbo;
    private TextView tvResultado,tvCiudad;
    private Tiempo tiempo;
    public String url = "https://api.tutiempo.net/xml/?lang=es&apid=qsTX4X4qq44as6Q&lid=8043";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer2);
        btnBilbo = findViewById(R.id.btnBilbo);
        btnVito = findViewById(R.id.btnVito);
        btnDono = findViewById(R.id.btnDono);
        tvResultado = findViewById(R.id.tvTiempo);
        tvCiudad = findViewById(R.id.tvCiudad);
        CargaXmlTask tarea = new CargaXmlTask();
        tarea.execute(url);
        tvCiudad.setText("Tiempo en: Vitoria");
        eventos();
    }




    private class CargaXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            RssDOMTiempo domParser = new RssDOMTiempo(params[0]);
            tiempo = domParser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            String s="";

            s="Dia: "+tiempo.getFecha()+"\nEstado Tiempo: "+tiempo.getEstadocielo()+"\n"+
                 "Temperatura Maxima: "   +tiempo.getTempmax()+"\n"+
                "Temperatura Minima"+tiempo.getTempmin();

            tvResultado.setText(s);
        }
    }

    public void cargarDatos(View view) {

        Tiempo temporal;

    }
    private void eventos(){
        btnDono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaXmlTask tarea = new CargaXmlTask();
                tarea.execute("https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=4917");
                tvCiudad.setText("Tiempo en: Donostia");

            }
        });
        btnVito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaXmlTask tarea = new CargaXmlTask();
                tarea.execute("https://api.tutiempo.net/xml/?lang=es&apid=qsTX4X4qq44as6Q&lid=8043");
                tvCiudad.setText("Tiempo en: Vitoria");

            }
        });
        btnBilbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaXmlTask tarea = new CargaXmlTask();
                tarea.execute("https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=8050  ");
                tvCiudad.setText("Tiempo en: Bilbao");
            }
        });

    }
}