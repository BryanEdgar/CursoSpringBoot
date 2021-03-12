package com.cursoSpring.Kruger.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Post {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private Date fecha;

    @Getter
    @Setter
    private String uriimg;

    public Post(int id, String titulo, String descripcion, Date fecha, String uriimg) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.uriimg = uriimg;
    }

    public Post() {

    }
}
