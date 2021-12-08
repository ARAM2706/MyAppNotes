package com.app.notapsavhll.appnotas.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DaoVideo {

    private Context _contexto;
    private SQLiteDatabase _midb;

    //CREACION DE LA DB;
    public DaoVideo(Context contexto){

        this._contexto = contexto;
        this._midb = new NotasBD(contexto).getWritableDatabase();

    }

    //INSERTAR DATOS;
    public long insert(Media c){

        ContentValues cv = new ContentValues();

        cv.put(NotasBD.COLUMNS_MEDIA[1],c.getId_TareaNota());
        cv.put(NotasBD.COLUMNS_MEDIA[2],c.getDir_uri());
        cv.put(NotasBD.COLUMNS_MEDIA[3],c.getDescripMedia());

        return _midb.insert(NotasBD.TABLE_MEDIA_NAME,null,cv) ;

    }

    //ACTUALIZAR DATOS;
    public long update(Media c){

        ContentValues cv = new ContentValues();

        cv.put(NotasBD.COLUMNS_MEDIA[1],c.getId_TareaNota());
        cv.put(NotasBD.COLUMNS_MEDIA[2],c.getDir_uri());
        cv.put(NotasBD.COLUMNS_MEDIA[3],c.getDescripMedia());

        return _midb.update(NotasBD.TABLE_MEDIA_NAME, cv, "_id=?", new String[] { String.valueOf( c.getId_media())});

    }

    //ELIMINAR DATOS (ID);
    public int delete(String id){

        return  _midb.delete("media","_id='"+id+"'",null);

    }

    //ELIMINAR DATOS (ID TAREA);
    public int deleteTODAS(String id){

        return  _midb.delete("media","id_Tarea='"+id+"'",null);

    }

    //CREACION DE LISTA;
    public List<Media> buscarTodos() {

        List<Media> notesArrayList = new ArrayList<Media>();
        String selectQuery = "SELECT * FROM media";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Media notas = new Media();
                notas.setId_media(c.getInt(c.getColumnIndex("_id")));
                notas.setId_TareaNota(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setDir_uri(c.getString(c.getColumnIndex("dirUri")));
                notas.setDescripMedia(c.getString(c.getColumnIndex("descripcion")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID TAREA;
    public List<Media> buscarTodosDeTarea1(int iden) {

        List<Media> notesArrayList = new ArrayList<Media>();
        String selectQuery = "SELECT * FROM media WHERE id_Tarea = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Media notas = new Media();
                notas.setId_media(c.getInt(c.getColumnIndex("_id")));
                notas.setId_TareaNota(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setDir_uri(c.getString(c.getColumnIndex("dirUri")));
                notas.setDescripMedia(c.getString(c.getColumnIndex("descripcion")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID;
    public Media buscarUno(int iden) {

        Media notesUno = new Media();
        String selectQuery = "SELECT * FROM media WHERE _id = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Media notas = new Media();
                notas.setId_media(c.getInt(c.getColumnIndex("_id")));
                notas.setId_TareaNota(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setDir_uri(c.getString(c.getColumnIndex("dirUri")));
                notas.setDescripMedia(c.getString(c.getColumnIndex("descripcion")));

                notesUno = notas;

            } while (c.moveToNext());

        }

        return notesUno;

    }
}
