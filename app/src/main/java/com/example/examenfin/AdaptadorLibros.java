package com.example.examenfin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class AdaptadorLibros extends ArrayAdapter<Libro> {

    private Libro[] datosLibro;

    public AdaptadorLibros(@NonNull Context context, Libro[] datos) {
        super(context, R.layout.libro, datos);
        this.datosLibro = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.libro, null);

        String titulo = datosLibro[position].getTitulo();
        String autor = datosLibro[position].getAutor();

        TextView tvTitulo = item.findViewById(R.id.txtTitulo);
        tvTitulo.setText(titulo);
        TextView tvAutor = item.findViewById(R.id.txtAutor);
        tvAutor.setText("Por: "+autor);
        String categoria = "";



        return (item);
    }
}