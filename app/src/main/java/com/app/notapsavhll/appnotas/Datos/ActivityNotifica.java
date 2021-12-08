package com.app.notapsavhll.appnotas.Datos;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;

import com.app.notapsavhll.appnotas.MainActivity;
import com.app.notapsavhll.appnotas.R;

public class ActivityNotifica extends AppCompatActivity {

    ListView lista;
    int tomaID = 0;


    String opc[] = new String[]{"Editar", "Eliminar"};

    private Alerta recordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifica);

        Bundle datos = this.getIntent().getExtras();
        int recupera_idTarea = datos.getInt("idtarea_integer");
        tomaID = recupera_idTarea;

        //Toast.makeText(ActivityNotifica.this, ""+tomaID, Toast.LENGTH_SHORT).show();


        lista =(ListView)findViewById(R.id.listaAlertas);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                recordatorio = new Alerta();
                recordatorio.setId_alerta(adp.getItem(i).getId_alerta());
                recordatorio.setId_tarea(adp.getItem(i).getId_tarea());
                recordatorio.setTituloAlerta(adp.getItem(i).getTituloAlerta());
                recordatorio.setDescripcionAlerta(adp.getItem(i).getDescripcionAlerta());
                recordatorio.setFechaAlerta(adp.getItem(i).getFechaAlerta());
                recordatorio.setHoraAlerta(adp.getItem(i).getHoraAlerta());
                elige_Lista();

                return false;

            }

        });

        cargardatos(tomaID);

    }

    ArrayAdapter<Alerta> adp;
    public void cargardatos(int ident){

        DaoNotifica dao = new DaoNotifica(ActivityNotifica.this);
        adp = new ArrayAdapter<Alerta>(ActivityNotifica.this, android.R.layout.simple_list_item_1,dao.buscarTodosDeTarea(ident));
        lista.setAdapter(adp);

    }



    public void elige_Lista(){

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.alert_Title)
                .setIcon(R.mipmap.ic_launcher)
                .setItems(opc, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(opc[which].equals("Editar")){

                            Intent intent = new Intent(getApplication(),ActivityDatos.class);
                            intent.putExtra("tipo_integer", 5);

                            intent.putExtra("integer_ID_alerta",recordatorio.getId_alerta());
                            intent.putExtra("integer_ID_tarea",recordatorio.getId_tarea());
                            intent.putExtra("string_titulo_alerta",recordatorio.getTituloAlerta());
                            intent.putExtra("string_descrip_alerta",recordatorio.getDescripcionAlerta());
                            intent.putExtra("string_fecha_alerta",recordatorio.getFechaAlerta());
                            intent.putExtra("string_hora_alerta",recordatorio.getHoraAlerta());

                            startActivityForResult(intent,1005);

                        }else if(opc[which].equals("Eliminar")) {

                            AlertDialog dialog2 = new AlertDialog.Builder(ActivityNotifica.this)
                                    .setTitle(R.string.del_alert_Title)
                                    .setIcon(android.R.drawable.ic_delete)
                                    .setMessage(R.string.del_alert_Message)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            try {

                                                DaoNotifica dao = new DaoNotifica(ActivityNotifica.this);

                                                if (dao.delete(recordatorio.getId_alerta() + "") > 0) {

                                                    Toast.makeText(getBaseContext(), R.string.del_alert_resultGood, Toast.LENGTH_SHORT).show();
                                                    cargardatos(tomaID);

                                                } else {

                                                    Toast.makeText(getBaseContext(), R.string.del_alert_resultBad, Toast.LENGTH_SHORT).show();

                                                }

                                            } catch (Exception err) {

                                                Toast.makeText(getBaseContext(), err.getMessage(), Toast.LENGTH_LONG).show();

                                            }

                                        }

                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            //Toast.makeText(MainActivity.this, "Presiono CANCEL", Toast.LENGTH_SHORT).show();

                                        }

                                    })
                                    .create();

                            dialog2.show();

                        }

                        dialog.dismiss();

                    }

                })
                .create();

        dialog.show();

    }



    public void btnAlert_click(View v){

        Toast.makeText(ActivityNotifica.this,R.string.toast_creaAlerta,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplication(),ActivityDatos.class);
        intent.putExtra("tipo_integer", 4);
        intent.putExtra("integer_ID_tarea", tomaID);

        startActivityForResult(intent,1004);

    }


    int aux=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK && requestCode == 1004) {

            try {

                Alerta objAlert = (Alerta) data.getSerializableExtra("miAlerta");
                DaoNotifica dao = new DaoNotifica(ActivityNotifica.this);

                if(dao.insert(new Alerta(0,objAlert.getId_tarea(),objAlert.getTituloAlerta(),objAlert.getDescripcionAlerta(),objAlert.getFechaAlerta(),objAlert.getHoraAlerta()))>0) {



                    Toast.makeText(getBaseContext(), R.string.toast_alertaCreada, Toast.LENGTH_SHORT).show();
                    cargardatos(tomaID);

                }else{

                    Toast.makeText(getBaseContext(), R.string.toast_alertaCreadaProblem, Toast.LENGTH_SHORT).show();

                }

            }catch (Exception err){

                Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();

            }

        }else if(resultCode==RESULT_OK && requestCode == 1005){

            try {

                Alerta objAlert = (Alerta) data.getSerializableExtra("miAlertaEdit");
                DaoNotifica dao = new DaoNotifica(ActivityNotifica.this);

                if(dao.update(objAlert)>0) {

                    Toast.makeText(getBaseContext(), R.string.toast_alertaEditada, Toast.LENGTH_SHORT).show();
                    cargardatos(tomaID);

                }else{

                    Toast.makeText(getBaseContext(), R.string.toast_alertaEditadaProblem, Toast.LENGTH_SHORT).show();

                }

            }catch (Exception err){

                Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();

            }

        }

    }

}
