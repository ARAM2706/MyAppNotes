package com.app.notapsavhll.appnotas;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.app.notapsavhll.appnotas.Datos.DaoNotas;
import com.app.notapsavhll.appnotas.Datos.DaoNotifica;
import com.app.notapsavhll.appnotas.Datos.Alerta;
import com.app.notapsavhll.appnotas.Datos.Nota_Serial;


public class Servicio extends Service {

    @Override
    public void onCreate(){}

    hilo h;
    @Override
    public int onStartCommand(Intent intent,int flag,int idProcess){

        try{

            if(h==null) {
                h = new hilo();
                h.start();
            }

        }catch (Exception err){}

        return START_STICKY;

    }

    @Override
    public void onDestroy(){

        try{

            if(h.isAlive()) {
                h.stop();
            }

        }catch (Exception err){}

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private ArrayList<Alerta> listarecordatorios = new ArrayList<>() ;
    int aux=0;

    public void btnNoti_click(String Titulo,String Descripcion,int indice) {
        long hora = System.currentTimeMillis();
       /* aux=aux+1;
        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        int icono = R.mipmap.ic_launcher;

        long hora = System.currentTimeMillis();

        Intent i = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
      //  mBuiler = new NotificationCompat.Builder( getApplicationContext(), "channel_id");
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(icono)
                .setContentTitle(Titulo)
                .setContentText(Descripcion)
                //.setWhen(hora)
                .setWhen(System.currentTimeMillis())
                .setVibrate(new long[]{100, 250, 100, 500})
                .setAutoCancel(true)
                .setSound(defaultSound);

        mNotifyMgr.notify(aux, mBuilder.build());*/
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(hora)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hearty365")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(Titulo)
                .setContentText(Descripcion)
                .setContentInfo("Info");

        notificationManager.notify(/*notification id*/1, notificationBuilder.build());





    }


    public class hilo extends Thread{
        @Override
        public  void  run(){
            while (true){

                try {

                    DaoNotifica dao = new DaoNotifica(getApplicationContext());
                    DaoNotas daonotas = new DaoNotas(getApplicationContext());


                    final Calendar c= Calendar.getInstance();
                    String fecha = c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR);
                    String hora = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);


                    List<Alerta> lista ;
                    lista = dao.buscarTodosDeFecha(fecha);


                    Alerta alertas = new Alerta();
                    alertas.setId_alerta(1);
                    alertas.setId_tarea(1);
                    alertas.setTituloAlerta("Titulo");
                    alertas.setDescripcionAlerta("Descripcion");
                    alertas.setFechaAlerta("Fecha");
                    alertas.setHoraAlerta("Hora");

                    lista.add(alertas);


                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getHoraAlerta().equalsIgnoreCase(hora) && ((c.get(Calendar.SECOND)) == 0)) {

                            Alerta obj = new Alerta();
                            obj.setId_alerta(lista.get(i).getId_alerta());
                            obj.setFechaAlerta(lista.get(i).getFechaAlerta());
                            obj.setHoraAlerta(lista.get(i).getHoraAlerta());

                            listarecordatorios.add(obj);

                            Nota_Serial nota = new Nota_Serial();
                            nota = daonotas.buscarUno(lista.get(i).getId_tarea());

                            if(nota.getTitulo().trim().length()>0 && nota.getDescripcion().trim().length()>0) {
                                btnNoti_click(nota.getTitulo(), nota.getDescripcion(), i);
                            }

                        }

                    }

                    Thread.sleep(1000);

                } catch (Exception e) {}

            }

        }

    }

}
