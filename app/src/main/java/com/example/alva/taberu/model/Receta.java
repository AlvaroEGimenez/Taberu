package com.example.alva.taberu.model;

import java.io.Serializable;

public class Receta implements Serializable {
    private String titulo;
    private Integer foto;
    private Integer ingredientes;
    private Integer preparacion;
    private String dificultad;
    private Integer tiempo;
    private Integer comensales;


    public Receta(String titulo, Integer foto, Integer ingredientes, Integer preparacion, String dificultad, Integer tiempo, Integer comensales) {
        this.titulo = titulo;
        this.foto = foto;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.dificultad = dificultad;
        this.tiempo = tiempo;
        this.comensales = comensales;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getFoto() {
        return foto;
    }

    public Integer getIngredientes() {
        return ingredientes;
    }

    public Integer getPreparacion() {
        return preparacion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public Integer getComensales() {
        return comensales;
    }


}
