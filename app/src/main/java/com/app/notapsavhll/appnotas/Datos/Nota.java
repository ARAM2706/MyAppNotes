package com.app.notapsavhll.appnotas.Datos;

import android.os.Parcel;
import android.os.Parcelable;


public class Nota implements Parcelable{

    private int id_nota;
    private int tipo;
    private String titulo;
    private String descripcion;
    private String fecha_creacion;
    private String fecha_limite;
    private String hora_limite;
    private int checalo;


    public Nota(int id_nota, int tipo, String titulo, String descripcion, String fecha_creacion, String fecha_limite, String hora_limite, int checalo) {

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



    // parceleable
    protected Nota(Parcel in) {

        id_nota = in.readInt();
        tipo = in.readInt();
        titulo = in.readString();
        descripcion = in.readString();
        fecha_creacion = in.readString();
        fecha_limite = in.readString();
        hora_limite =in.readString();
        checalo = in.readInt();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id_nota);
        dest.writeInt(tipo);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeString(fecha_creacion);
        dest.writeString(fecha_limite);
        dest.writeString(hora_limite);
        dest.writeInt(checalo);

    }

    public static final Creator<Nota> CREATOR = new Creator<Nota>() {

        @Override
        public Nota createFromParcel(Parcel in) {
            return new Nota(in);
        }

        @Override
        public Nota[] newArray(int size) {
            return new Nota[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

}
