package com.mackenzie.notasapp.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notas")
public class NoteEntity {

    // Este decorador contendra un valor unico para cada nota autogenerado
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Titulo")
    public String titulo;
    @ColumnInfo(name = "Contenido")
    public String contenido;
    @ColumnInfo(name = "Favoritos")
    public boolean favorita;
    @ColumnInfo(name = "Color")
    public String color;
    @ColumnInfo(name = "Fecha")
    public String fecha;

    public NoteEntity() {
    }

    public NoteEntity(String titulo, String contenido, boolean favorita, String color) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.favorita = favorita;
        this.color = color;
        Date date = new Date();
        fecha = date.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
