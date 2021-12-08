package com.app.notapsavhll.appnotas.Datos;

import java.io.Serializable;


public class Media implements Serializable {

    private int id_media;
    private int id_TareaNota;
    private String dir_uri;
    private String descripMedia;

    public Media(){};

    public Media(int id_media, int id_TareaNota, String dir_uri, String descripMedia) {

        this.id_media = id_media;
        this.id_TareaNota = id_TareaNota;
        this.dir_uri = dir_uri;
        this.descripMedia = descripMedia;

    }

    public int getId_media() {
        return id_media;
    }

    public int getId_TareaNota() {
        return id_TareaNota;
    }

    public String getDir_uri() {
        return dir_uri;
    }

    public String getDescripMedia() {
        return descripMedia;
    }



    public void setId_media(int id_media) {
        this.id_media = id_media;
    }

    public void setId_TareaNota(int id_TareaNota) {
        this.id_TareaNota = id_TareaNota;
    }

    public void setDir_uri(String dir_uri) {
        this.dir_uri = dir_uri;
    }

    public void setDescripMedia(String descripMedia) {
        this.descripMedia = descripMedia;
    }



    @Override
    public String toString() {

        return "\nID_Media:" + this.id_media + "\nID_Tarea:" + this.id_TareaNota + "\nDirUri:" + this.dir_uri + "\nDescripcion:" + this.descripMedia + "\n";

    }

}
