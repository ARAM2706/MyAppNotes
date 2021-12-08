package com.app.notapsavhll.appnotas.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class DaoNotifica {

    private Context _contexto;
    private SQLiteDatabase _midb;


    //CREACION DE LA DB;
    public DaoNotifica(Context contexto){

        this._contexto = contexto;
        this._midb = new NotasBD(contexto).getWritableDatabase();

    }

    //INSERTAR DATOS;
    public long insert(Alerta c){

        ContentValues cv = new ContentValues();

        cv.put(NotasBD.COLUMNS_ALERTAS[1],c.getId_tarea());
        cv.put(NotasBD.COLUMNS_ALERTAS[2],c.getTituloAlerta());
        cv.put(NotasBD.COLUMNS_ALERTAS[3],c.getDescripcionAlerta());
        cv.put(NotasBD.COLUMNS_ALERTAS[4],c.getFechaAlerta());
        cv.put(NotasBD.COLUMNS_ALERTAS[5],c.getHoraAlerta());

        return _midb.insert(NotasBD.TABLE_ALERTAS_NAME,null,cv) ;

    }

    //ACTUALIZAR DATOS;
    public long update(Alerta c){

        ContentValues cv = new ContentValues();

        cv.put(NotasBD.COLUMNS_ALERTAS[1],c.getId_tarea());
        cv.put(NotasBD.COLUMNS_ALERTAS[2],c.getTituloAlerta());
        cv.put(NotasBD.COLUMNS_ALERTAS[3],c.getDescripcionAlerta());
        cv.put(NotasBD.COLUMNS_ALERTAS[4],c.getFechaAlerta());
        cv.put(NotasBD.COLUMNS_ALERTAS[5],c.getHoraAlerta());

        return _midb.update(NotasBD.TABLE_ALERTAS_NAME, cv, "_id=?", new String[] { String.valueOf( c.getId_alerta())});

    }

    //ELIMINAR DATOS (ID);
    public int delete(String id){

        return  _midb.delete("alertas","_id='"+id+"'",null);

    }

    //ELIMINAR DATOS (ID TAREA);
    public int deleteTODAS(String id){

        return  _midb.delete("alertas","id_Tarea='"+id+"'",null);

    }

    //CREACION DE LISTA;
    public List<Alerta> buscarTodos() {

        List<Alerta> notesArrayList = new ArrayList<Alerta>();
        String selectQuery = "SELECT * FROM alertas";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Alerta notas = new Alerta();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setId_tarea(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setTituloAlerta(c.getString(c.getColumnIndex("titulo")));
                notas.setDescripcionAlerta(c.getString(c.getColumnIndex("descripcion")));
                notas.setFechaAlerta(c.getString(c.getColumnIndex("fechaAlerta")));
                notas.setHoraAlerta(c.getString(c.getColumnIndex("horaAlerta")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID TAREA;
    public List<Alerta> buscarTodosDeTarea(int iden) {

        List<Alerta> notesArrayList = new ArrayList<Alerta>();
        String selectQuery = "SELECT * FROM alertas WHERE id_Tarea = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Alerta notas = new Alerta();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setId_tarea(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setTituloAlerta(c.getString(c.getColumnIndex("titulo")));
                notas.setDescripcionAlerta(c.getString(c.getColumnIndex("descripcion")));
                notas.setFechaAlerta(c.getString(c.getColumnIndex("fechaAlerta")));
                notas.setHoraAlerta(c.getString(c.getColumnIndex("horaAlerta")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR FECHA;
    public List<Alerta> buscarTodosDeFecha(String iden) {

        List<Alerta> notesArrayList = new ArrayList<Alerta>();
        String selectQuery = "SELECT * FROM alertas WHERE fechaAlerta = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Alerta notas = new Alerta();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setId_tarea(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setTituloAlerta(c.getString(c.getColumnIndex("titulo")));
                notas.setDescripcionAlerta(c.getString(c.getColumnIndex("descripcion")));
                notas.setFechaAlerta(c.getString(c.getColumnIndex("fechaAlerta")));
                notas.setHoraAlerta(c.getString(c.getColumnIndex("horaAlerta")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID;
    public Alerta buscarUno(int iden) {

        Alerta notesUno = new Alerta();
        String selectQuery = "SELECT * FROM alertas WHERE _id = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Alerta notas = new Alerta();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setId_tarea(c.getInt(c.getColumnIndex("id_Tarea")));
                notas.setTituloAlerta(c.getString(c.getColumnIndex("titulo")));
                notas.setDescripcionAlerta(c.getString(c.getColumnIndex("descripcion")));
                notas.setFechaAlerta(c.getString(c.getColumnIndex("fechaAlerta")));
                notas.setHoraAlerta(c.getString(c.getColumnIndex("horaAlerta")));

                notesUno = notas;

            } while (c.moveToNext());

        }

        return notesUno;

    }











}
