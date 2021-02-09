package com.example.examenfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ListarLibros extends AppCompatActivity {

    private ListView lvLibros;
    private Button btnSalir;
    private Libro [] libros;
    private SQLiteDatabase db;
    private RadioButton rbLeido,rbNoleido,rbTodos;
    private RadioGroup rG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);
        lvLibros = findViewById(R.id.lvLibros);
        btnSalir = findViewById(R.id.btnSalirLista);
        rbLeido = findViewById(R.id.rbLeido);
        rbTodos = findViewById(R.id.rbTodos);
        rbNoleido = findViewById(R.id.rbNoLeido);
        rG = findViewById(R.id.myRadioGroup);
        BibliotecaSQLHelper usdbh = new BibliotecaSQLHelper(this, "DBUsuarios", null, 1);
        db = usdbh.getWritableDatabase();//Si hemos abierto correctamente la base de datos
        listarTodos();
        eventos();
    }


    public void listarTodos(){
        Cursor c =db.rawQuery("SELECT id, titulo, autor FROM biblioteca " , null);
        Libro [] contactos = new Libro[c.getCount()];
        int j = 0;
        if (c.moveToFirst()){
            //Recorremos el cursor hasta que no haya más registros.
            do {
                int codigo = c.getInt(0);
                String titulo =c.getString(1);
                String autor = c.getString(2);
                contactos[j] = new Libro(codigo,titulo,autor);
                j++;
            }while (c.moveToNext());
        }
        libros = contactos;
        AdaptadorLibros adaptador = new AdaptadorLibros(ListarLibros.this, contactos);
        lvLibros.setAdapter(adaptador);
    }
    public void listarLeidos(){
        Cursor c =db.rawQuery("SELECT id, titulo, autor FROM biblioteca where leido = 1 " , null);
        Libro [] contactos = new Libro[c.getCount()];
        int j = 0;
        if (c.moveToFirst()){
            //Recorremos el cursor hasta que no haya más registros.
            do {
                int codigo = c.getInt(0);
                String titulo =c.getString(1);
                String autor = c.getString(2);
                contactos[j] = new Libro(codigo,titulo,autor);
                j++;
            }while (c.moveToNext());
        }
        libros = contactos;
        AdaptadorLibros adaptador = new AdaptadorLibros(ListarLibros.this, contactos);
        lvLibros.setAdapter(adaptador);
    }
    public void listarNoLeidos(){
        Cursor c =db.rawQuery("SELECT id, titulo, autor FROM biblioteca where leido = 0 " , null);
        Libro [] contactos = new Libro[c.getCount()];
        int j = 0;
        if (c.moveToFirst()){
            //Recorremos el cursor hasta que no haya más registros.
            do {
                int codigo = c.getInt(0);
                String titulo =c.getString(1);
                String autor = c.getString(2);
                contactos[j] = new Libro(codigo,titulo,autor);
                j++;
            }while (c.moveToNext());
        }
        libros = contactos;
        AdaptadorLibros adaptador = new AdaptadorLibros(ListarLibros.this, contactos);
        lvLibros.setAdapter(adaptador);
    }


    private  void eventos(){
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == rbLeido.getId()){
                    listarLeidos();
                }
                else if(checkedId == rbNoleido.getId()){
                    listarNoLeidos();
                }
                else if(checkedId == rbTodos.getId()){
                    listarTodos();
                }
            }
        });
        lvLibros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int idLibro = libros[position].getId();
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
                Intent intent = new Intent(ListarLibros.this, VerLibro.class);
                intent.putExtra("libro", contactos[0]);
                startActivity(intent);
                return false;
            }
        });

    }


}