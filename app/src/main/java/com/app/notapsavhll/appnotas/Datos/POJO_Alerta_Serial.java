package com.app.notapsavhll.appnotas.Datos;

import java.io.Serializable;

public class POJO_Alerta_Serial implements Serializable{

    private int id_alerta;
    private int id_tarea;
    private String tituloAlerta;
    private String descripcionAlerta;
    private String fechaAlerta;
    private String horaAlerta;

    public POJO_Alerta_Serial(){};

    public POJO_Alerta_Serial(int id_alerta, int id_tarea, String tituloAlerta, String descripcionAlerta, String fechaAlerta, String horaAlerta) {

        this.id_alerta = id_alerta;
        this.id_tarea = id_tarea;
        this.tituloAlerta = tituloAlerta;
        this.descripcionAlerta = descripcionAlerta;
        this.fechaAlerta = fechaAlerta;
        this.horaAlerta = horaAlerta;

    }

    public int getId_alerta() {
        return id_alerta;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public String getTituloAlerta() {
        return tituloAlerta;
    }

    public String getDescripcionAlerta() {
        return descripcionAlerta;
    }

    public String getFechaAlerta() {
        return fechaAlerta;
    }

    public String getHoraAlerta() {
        return horaAlerta;
    }



    public void setId_alerta(int id_alerta) {
        this.id_alerta = id_alerta;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public void setTituloAlerta(String tituloAlerta) {
        this.tituloAlerta = tituloAlerta;
    }

    public void setDescripcionAlerta(String descripcionAlerta) {
        this.descripcionAlerta = descripcionAlerta;
    }

    public void setFechaAlerta(String fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public void setHoraAlerta(String horaAlerta) {
        this.horaAlerta = horaAlerta;
    }



    @Override
    public String toString() {

        return "\nID_Alerta:" + this.id_alerta + "\nID_Tarea:" + this.id_tarea + "\nTitulo:" + this.tituloAlerta + "\nDescripcion:" + this.descripcionAlerta + "\nFecha:" + this.fechaAlerta + "\nHora:" + this.horaAlerta + "\n";

    }

}
