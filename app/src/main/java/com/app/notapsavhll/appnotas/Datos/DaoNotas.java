package com.app.notapsavhll.appnotas.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DaoNotas {

    private Context _contexto;
    private SQLiteDatabase _midb;


    //CREACION DE LA DB;
    public DaoNotas(Context contexto){

        this._contexto = contexto;
        this._midb = new NotasBD(contexto).getWritableDatabase();

    }

    //INSERTAR DATOS;
    public long insert(Nota_Serial c){

        ContentValues cv = new ContentValues();

        cv.put(NotasBD.COLUMNS_REGISTROS[1],c.getTipo());
        cv.put(NotasBD.COLUMNS_REGISTROS[2],c.getTitulo());
        cv.put(NotasBD.COLUMNS_REGISTROS[3],c.getDescripcion());
        cv.put(NotasBD.COLUMNS_REGISTROS[4],c.getFecha_creacion());
        cv.put(NotasBD.COLUMNS_REGISTROS[5],c.getFecha_limite());
        cv.put(NotasBD.COLUMNS_REGISTROS[6],c.getHora_limite());
        cv.put(NotasBD.COLUMNS_REGISTROS[7],c.getChecalo());

        return _midb.insert(NotasBD.TABLE_REGISTROS_NAME,null,cv) ;

    }

    //ACTUALIZAR DATOS;
    public long update(Nota_Serial c){

        ContentValues cv = new ContentValues();

        cv.put(NotasBD.COLUMNS_REGISTROS[1],c.getTipo());
        cv.put(NotasBD.COLUMNS_REGISTROS[2],c.getTitulo());
        cv.put(NotasBD.COLUMNS_REGISTROS[3],c.getDescripcion());
        cv.put(NotasBD.COLUMNS_REGISTROS[4],c.getFecha_creacion());
        cv.put(NotasBD.COLUMNS_REGISTROS[5],c.getFecha_limite());
        cv.put(NotasBD.COLUMNS_REGISTROS[6],c.getHora_limite());
        cv.put(NotasBD.COLUMNS_REGISTROS[7],c.getChecalo());

        return _midb.update(NotasBD.TABLE_REGISTROS_NAME, cv, "_id=?", new String[] { String.valueOf( c.getId_nota())});

    }

    //ELIMINAR DATOS;
    public int delete(String id){

        return  _midb.delete("registros","_id='"+id+"'",null);

    }

    //CREACION DE LISATAS;
    public List<Nota_Serial> buscarTodos(int tipo) {

        List<Nota_Serial> notesArrayList = new ArrayList<Nota_Serial>();
        String selectQuery = "SELECT * FROM registros WHERE tipo = '"+tipo+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Nota_Serial notas = new Nota_Serial();
                notas.setId_nota(c.getInt(c.getColumnIndex("_id")));
                notas.setTipo(c.getInt(c.getColumnIndex("tipo")));
                notas.setTitulo(c.getString(c.getColumnIndex("titulo")));
                notas.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                notas.setFecha_creacion(c.getString(c.getColumnIndex("fecha_creacion")));
                notas.setFecha_limite(c.getString(c.getColumnIndex("fecha_limite")));
                notas.setHora_limite(c.getString(c.getColumnIndex("hora_limite")));
                notas.setChecalo(c.getInt(c.getColumnIndex("checalo")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID;
    public Nota_Serial buscarUno(int iden) {

        Nota_Serial notesUno = new Nota_Serial();
        String selectQuery = "SELECT * FROM registros WHERE _id = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Nota_Serial notas = new Nota_Serial();
                notas.setId_nota(c.getInt(c.getColumnIndex("_id")));
                notas.setTipo(c.getInt(c.getColumnIndex("tipo")));
                notas.setTitulo(c.getString(c.getColumnIndex("titulo")));
                notas.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                notas.setFecha_creacion(c.getString(c.getColumnIndex("fecha_creacion")));
                notas.setFecha_limite(c.getString(c.getColumnIndex("fecha_limite")));
                notas.setHora_limite(c.getString(c.getColumnIndex("hora_limite")));
                notas.setChecalo(c.getInt(c.getColumnIndex("checalo")));

                notesUno = notas;

            } while (c.moveToNext());

        }

        return notesUno;

    }



}
