package com.example.examenfin;

import java.io.Serializable;

public class Libro implements Serializable {

    private int numPag,id,isbn;
    private String titulo,autor,editoria;
    boolean leido;

    public Libro( int id, String titulo, String autor, int isbn, String editoria,int numPag,boolean leido) {
        this.numPag = numPag;
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editoria = editoria;
        this.leido = leido;
    }
    public Libro(int numPag, int isbn, String titulo, String autor, String editoria,boolean leido) {
        this.numPag = numPag;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editoria = editoria;
        this.leido = leido;
    }
    public Libro(int id,String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditoria() {
        return editoria;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public void setEditoria(String editoria) {
        this.editoria = editoria;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "numPag=" + numPag +
                ", id=" + id +
                ", isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editoria='" + editoria + '\'' +
                '}';
    }
}
