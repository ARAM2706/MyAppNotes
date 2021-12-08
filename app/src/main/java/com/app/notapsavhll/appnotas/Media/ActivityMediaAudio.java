package com.app.notapsavhll.appnotas.Media;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.notapsavhll.appnotas.Datos.DaoMedia;
import com.app.notapsavhll.appnotas.Datos.DaoVideo;
import com.app.notapsavhll.appnotas.Datos.Media;
import com.app.notapsavhll.appnotas.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityMediaAudio extends AppCompatActivity {

    int tomaID = 0;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private MediaRecorder grabacion;
    private String archivoSalida = null; //asignamos un nombre a la pista de audio que vamos a agregar
    private Button btn_recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_audio);
        btn_recorder = (Button)findViewById(R.id.btn_newMedia1);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivityMediaAudio.this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
        Bundle datos = this.getIntent().getExtras();
        int recupera_idRegistro = datos.getInt("idregistro_integer");
        tomaID = recupera_idRegistro;

        recycler = (RecyclerView) findViewById(R.id.lista_Fotos);

        cargar();
    }
    public void cargar(){

        List<Media> items = new ArrayList<>();

        DaoVideo dao = new DaoVideo(ActivityMediaAudio.this);
        items = dao.buscarTodosDeTarea1(tomaID);

        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new VideoAdapter(items);
        recycler.setAdapter(adapter);

    }

    private String mDirAbsoluto = null;
    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final String ALBUM = "GuardaAudio";
    private static final String EXTENSION_MP3 = ".mp3";
    final int MY_PERMISSIONS_REQUEST_READ_ESTORAGE=124;

    public void Recorder(View view){
        Toast.makeText(ActivityMediaAudio.this,"Grabar audio",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file = null;
        if(grabacion == null){
            try {
                // Crea el Nombre del audio
                String fechaHora = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                String nombre = ALBUM + "_" + fechaHora;
                // Crea el Archivo del audio
                file = nombrarArchivo(ActivityMediaAudio.this, ALBUM, nombre, EXTENSION_MP3);
                // Obtiene el Nombre y el Directorio Absoluto y los Muestra
                // Guarda el Directorio Absoluto en una Variable Global
                archivoSalida = file.getAbsolutePath();
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                grabacion = new MediaRecorder();
                grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
                grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                grabacion.setOutputFile(archivoSalida);
            } catch (IOException e) {
                e.printStackTrace();
                file = null;
                archivoSalida = null;
            }

            try{
                grabacion.prepare();
                grabacion.start();
            }catch (IOException e){
            }

            //btn_recorder.setBackgroundResource(R.drawable.rec);
            Toast.makeText(getApplicationContext(), "Grabando...", Toast.LENGTH_SHORT).show();
        }else if(grabacion!= null) {
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            btn_recorder = (Button)findViewById(R.id.btn_newMedia1);
            //btn_recorder.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(getApplicationContext(), "Grabación finalizada...", Toast.LENGTH_SHORT).show();
        }

    }

    public void reproducir(View view){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
            Media objNota = new Media(0, tomaID, String.valueOf(archivoSalida), "AUDIO");
            DaoMedia dao = new DaoMedia(ActivityMediaAudio.this);

            if(dao.insert(new Media(0,objNota.getId_TareaNota(),objNota.getDir_uri(),objNota.getDescripMedia()))>0) {

                Toast.makeText(getBaseContext(), "Audio guardado", Toast.LENGTH_SHORT).show();
                cargar();

            }else{

                Toast.makeText(getBaseContext(), "El audio no pudo ser guardado", Toast.LENGTH_SHORT).show();

            }
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        }catch (IOException err){
            Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();
        }
        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Reproduciendo audio",Toast.LENGTH_SHORT).show();
    }



    private File nombrarArchivo(Context context, String album, String nombre, String extension) throws IOException {

        return new File(obtenerDirectorioPublico(context, album), nombre + extension);

    }

    private File obtenerDirectorioPublico(Context context, String album) {

        File file = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), album);

            if (file != null) {

                if (!file.mkdirs()) {

                    if (!file.exists()) {

                        Toast.makeText(context, "Error al crear el directorio.", Toast.LENGTH_SHORT).show();
                        return null;

                    }

                }

            }

        } else {

            Toast.makeText(context, "Tarjeta SD no disponible.", Toast.LENGTH_SHORT).show();
            file = new File(context.getFilesDir(), album);

        }

        return file;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_VIDEO_CAPTURE:

                if (resultCode == RESULT_OK) {

                    //Bitmap bitmap = escalarBitmap(mDirAbsoluto, SCALE_FACTOR_IMAGE_VIEW);
                    //imageView.setImageBitmap(bitmap);

                    try {

                        Media objNota = new Media(0, tomaID, String.valueOf(mDirAbsoluto), "AUDIO");
                        DaoMedia dao = new DaoMedia(ActivityMediaAudio.this);

                        if(dao.insert(new Media(0,objNota.getId_TareaNota(),objNota.getDir_uri(),objNota.getDescripMedia()))>0) {

                            Toast.makeText(getBaseContext(), "Audio guardado", Toast.LENGTH_SHORT).show();
                            cargar();

                        }else{

                            Toast.makeText(getBaseContext(), "El audio no pudo ser guardado", Toast.LENGTH_SHORT).show();

                        }

                    }catch (Exception err){

                        Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();

                    }

                }

                break;

            default:

                break;

        }

    }
}
