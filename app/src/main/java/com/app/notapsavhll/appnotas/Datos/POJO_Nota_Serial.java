package com.app.notapsavhll.appnotas.Datos;

import java.io.Serializable;


public class POJO_Nota_Serial implements Serializable{

    private int id_nota;
    private int tipo;
    private String titulo;
    private String descripcion;
    private String fecha_creacion;
    private String fecha_limite;
    private String hora_limite;
    private int checalo;


    public POJO_Nota_Serial() {}

    public POJO_Nota_Serial(int id_nota, int tipo, String titulo, String descripcion, String fecha_creacion, String fecha_limite, String hora_limite, int checalo) {

        this.id_nota = id_nota;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.fecha_limite = fecha_limite;
        this.hora_limite = hora_limite;
        this.checalo = checalo;

    }

    public int getId_nota() {
        return id_nota;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public String getHora_limite() {
        return hora_limite;
    }

    public int getChecalo() {
        return checalo;
    }



    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public void setHora_limite(String hora_limite) {
        this.hora_limite = hora_limite;
    }

    public void setChecalo(int checalo) {
        this.checalo = checalo;
    }



    @Override
    public String toString() {

        return "\nID:" + this.id_nota + "\nTipo:" + this.tipo + "\nTitulo:" + this.titulo + "\nDescripcion:" + this.descripcion + "\nCreacion:" + this.fecha_creacion + "\nLimite:" + this.fecha_limite + "\nHora:" + this.hora_limite + "\nCheck:" + this.checalo + "\n";

    }

}
