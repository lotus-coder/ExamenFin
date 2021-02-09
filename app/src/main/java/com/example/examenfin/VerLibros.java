package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VerLibros extends AppCompatActivity {
    private Libro [] l;
    private ListView lvLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libros);
        Bundle extras = getIntent().getExtras();

        l = (Libro[]) extras.getSerializable("libro");
        lvLibros = findViewById(R.id.lvBusqueda);
        AdaptadorLibros a = new AdaptadorLibros(VerLibros.this,l);
        lvLibros.setAdapter(a);
        eventos();
    }

    private void eventos() {

        lvLibros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int idLibro = l[position].getId();
                BibliotecaSQLHelper usdbh = new BibliotecaSQLHelper(VerLibros.this, "DBUsuarios", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();
                Cursor c =db.rawQuery("SELECT id, titulo, autor ,isbn,editorial,numPag ,leido FROM biblioteca where id = "+idLibro , null);
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
                Intent intent = new Intent(VerLibros.this, VerLibro.class);
                intent.putExtra("libro", contactos[0]);
                startActivity(intent);
                return false;
            }
        });
    }
}