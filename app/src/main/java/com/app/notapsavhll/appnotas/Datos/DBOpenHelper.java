    package com.app.notapsavhll.appnotas.Datos;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class DBOpenHelper extends SQLiteOpenHelper{

        //CONSTRUCCION DE LA BD;
        private static final String DB_NAME = "db_notes";
        private static final int DB_VERSION = 2;



        public static final String[]COLUMNS_REGISTROS = {"_id","tipo","titulo","descripcion","fecha_creacion","fecha_limite","hora_limite","checalo"};
        public static final String TABLE_REGISTROS_NAME="registros";

        private  final String TABLE_REGISTROS = "create table registros ("+
                COLUMNS_REGISTROS[0]+" integer primary key autoincrement, "+
                COLUMNS_REGISTROS[1]+" integer not null," +
                COLUMNS_REGISTROS[2]+" varchar(100) not null,"+
                COLUMNS_REGISTROS[3]+" text not null,"+
                COLUMNS_REGISTROS[4]+" varchar(12) not null,"+
                COLUMNS_REGISTROS[5]+" varchar(12) null,"+
                COLUMNS_REGISTROS[6]+" varchar(12) null,"+
                COLUMNS_REGISTROS[7]+" integer null);";



        public static final String[]COLUMNS_ALERTAS = {"_id","id_Tarea","titulo","descripcion","fechaAlerta","horaAlerta"};
        public static final String TABLE_ALERTAS_NAME="alertas";

        private  final String TABLE_ALERTAS = "create table alertas ("+
                COLUMNS_ALERTAS[0]+" integer primary key autoincrement, "+
                COLUMNS_ALERTAS[1]+" integer not null," +
                COLUMNS_ALERTAS[2]+" varchar(100) not null,"+
                COLUMNS_ALERTAS[3]+" text not null,"+
                COLUMNS_ALERTAS[4]+" varchar(12) null,"+
                COLUMNS_ALERTAS[5]+" varchar(12) null);";



        public static final String[]COLUMNS_MEDIA = {"_id","id_Tarea","dirUri","descripcion"};
        public static final String TABLE_MEDIA_NAME="media";

        private  final String TABLE_MEDIA = "create table media ("+
                COLUMNS_MEDIA[0]+" integer primary key autoincrement, "+
                COLUMNS_MEDIA[1]+" integer not null," +
                COLUMNS_MEDIA[2]+" varchar not null,"+
                COLUMNS_MEDIA[3]+" varchar(100) null);";











        public DBOpenHelper(Context contexto) {

            super(contexto, DB_NAME, null, DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {

                sqLiteDatabase.execSQL(TABLE_REGISTROS);
                sqLiteDatabase.execSQL(TABLE_ALERTAS);
                sqLiteDatabase.execSQL(TABLE_MEDIA);

            }catch (Exception err){}

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            try{

                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS registros");
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS alertas");
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS media");
                onCreate(sqLiteDatabase);

            }catch (Exception err){}

        }







    }
