package com.app.notapsavhll.appnotas.Datos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import java.util.Calendar;


import com.app.notapsavhll.appnotas.R;

public class ActivityDatos extends AppCompatActivity implements View.OnClickListener {

    int type_flag = 0;
    int tomaID = 0;
    int tomaID_Alerta = 0;
    int tomaID_Alerta_Tarea = 0;

    Button btnDatePicker, btnTimePicker, btn_Ingresa;
    EditText txt_fechaCreacion, txt_fechaLimit, txt_horaLimit, txt_titulo, txt_descri;
    TextView titulo1, titulo2, titulo3;
    CheckBox checkCheca;

    private int mYear, mMonth, mDay, mHour, mMinute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        titulo1=(TextView)findViewById(R.id.text_titulo1);
        txt_titulo=(EditText)findViewById(R.id.txt_titulo);
        txt_descri=(EditText)findViewById(R.id.txt_descrip);

        titulo2=(TextView)findViewById(R.id.text_titulo2);
        txt_fechaCreacion=(EditText)findViewById(R.id.txt_fechCreacion);

        titulo3=(TextView)findViewById(R.id.text_titulo3);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txt_fechaLimit=(EditText)findViewById(R.id.txt_fecLimite);
        txt_horaLimit=(EditText)findViewById(R.id.txt_horaLimite);
        checkCheca=(CheckBox)findViewById(R.id.check_Checa);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        btn_Ingresa=(Button)findViewById(R.id.btnguardar);

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        txt_fechaCreacion.setText(mDay + "-" + (mMonth + 1) + "-" + mYear);
        txt_fechaCreacion.setEnabled(false);

        txt_fechaLimit.setEnabled(false);
        txt_horaLimit.setEnabled(false);


        Bundle datos = this.getIntent().getExtras();
        int recupera_tipo = datos.getInt("tipo_integer");
        type_flag = recupera_tipo;


        int recu_id = datos.getInt("integer_ID");
        tomaID = recu_id;

        String recu_titulo = datos.getString("string_Titulo");
        String recu_descri = datos.getString("string_Descrip");
        String recu_fechCrea = datos.getString("string_FechCreacion");
        String recu_fechFin = datos.getString("string_FechaLimite");
        String recu_horaFin = datos.getString("string_HoraLimite");
        int recu_checa = datos.getInt("integer_checalo");


        int recu_idAlerta = datos.getInt("integer_ID_alerta");
        tomaID_Alerta = recu_idAlerta;

        int recu_idTareaAlert = datos.getInt("integer_ID_tarea");
        tomaID_Alerta_Tarea = recu_idTareaAlert;

        String recu_tituloAlerta = datos.getString("string_titulo_alerta");
        String recu_descriAlerta = datos.getString("string_descrip_alerta");
        String recu_fechaAlerta = datos.getString("string_fecha_alerta");
        String recu_horaAlerta = datos.getString("string_hora_alerta");


        if(recupera_tipo==0){

            titulo3.setVisibility(View.INVISIBLE);
            btnDatePicker.setVisibility(View.INVISIBLE);
            btnTimePicker.setVisibility(View.INVISIBLE);
            txt_fechaLimit.setVisibility(View.INVISIBLE);
            txt_horaLimit.setVisibility(View.INVISIBLE);
            checkCheca.setVisibility(View.INVISIBLE);

            btn_Ingresa.setText(R.string.btn_ingresa_addNota);

        }else if(recupera_tipo==1){

            btn_Ingresa.setText(R.string.btn_ingresa_addTarea);

        }else if(recupera_tipo==2){

            txt_titulo.setText(recu_titulo);
            txt_descri.setText(recu_descri);
            txt_fechaCreacion.setText(recu_fechCrea);

            titulo3.setVisibility(View.INVISIBLE);
            btnDatePicker.setVisibility(View.INVISIBLE);
            btnTimePicker.setVisibility(View.INVISIBLE);
            txt_fechaLimit.setVisibility(View.INVISIBLE);
            txt_horaLimit.setVisibility(View.INVISIBLE);
            checkCheca.setVisibility(View.INVISIBLE);

            btn_Ingresa.setText(R.string.btn_ingresa_editNota);

        }else if(recupera_tipo==3){

            txt_titulo.setText(recu_titulo);
            txt_descri.setText(recu_descri);
            txt_fechaCreacion.setText(recu_fechCrea);
            txt_fechaLimit.setText(recu_fechFin);
            txt_horaLimit.setText(recu_horaFin);

            if(recu_checa==1){
                checkCheca.setChecked(true);
            }else if(recu_checa==0){
                checkCheca.setChecked(false);
            }

            btn_Ingresa.setText(R.string.btn_ingresa_editTarea);

        }else if(recupera_tipo==4){

            titulo1.setText("Contenido notificacion:");
            titulo2.setVisibility(View.INVISIBLE);
            txt_fechaCreacion.setVisibility(View.INVISIBLE);
            titulo3.setText("Fecha de notificacion");
            checkCheca.setVisibility(View.INVISIBLE);

            btn_Ingresa.setText(R.string.btn_ingresa_addNoti);

        }else if(recupera_tipo==5){

            titulo1.setText("Contenido notificacion:");
            titulo2.setVisibility(View.INVISIBLE);
            txt_fechaCreacion.setVisibility(View.INVISIBLE);
            titulo3.setText("Fecha de notificacion");
            checkCheca.setVisibility(View.INVISIBLE);

            txt_titulo.setText(recu_tituloAlerta);
            txt_descri.setText(recu_descriAlerta);
            txt_fechaLimit.setText(recu_fechaAlerta);
            txt_horaLimit.setText(recu_horaAlerta);

            btn_Ingresa.setText(R.string.btn_ingresa_editNoti);

        }

    }

    public void btnIngresa_click(View v){

        if(type_flag==0){

            Toast.makeText(ActivityDatos.this,"Nota creada con exito",Toast.LENGTH_SHORT).show();

            Intent atras = new Intent();
            Nota_Serial alum = new Nota_Serial();

            alum.setTipo(0);
            alum.setTitulo(txt_titulo.getText().toString());
            alum.setDescripcion(txt_descri.getText().toString());
            alum.setFecha_creacion(txt_fechaCreacion.getText().toString());

            alum.setFecha_limite("");
            alum.setHora_limite("");

            alum.setChecalo(0);

            atras.putExtra("miNota", alum);
            setResult(RESULT_OK, atras);
            finish();

        }else if(type_flag==1){

            Toast.makeText(ActivityDatos.this,"Task was made successful",Toast.LENGTH_SHORT).show();

            Intent atras = new Intent();
            Nota_Serial alum = new Nota_Serial();

            alum.setTipo(1);
            alum.setTitulo(txt_titulo.getText().toString());
            alum.setDescripcion(txt_descri.getText().toString());
            alum.setFecha_creacion(txt_fechaCreacion.getText().toString());

            alum.setFecha_limite(txt_fechaLimit.getText().toString());
            alum.setHora_limite(txt_horaLimit.getText().toString());

            if(checkCheca.isChecked()){
                alum.setChecalo(1);
            }else{
                alum.setChecalo(0);
            }


            atras.putExtra("miTarea", alum);

            setResult(RESULT_OK, atras);
            finish();

        }else if(type_flag==2){

            Toast.makeText(ActivityDatos.this,R.string.confirmToast_editNote,Toast.LENGTH_SHORT).show();

            Intent atras = new Intent();
            Nota_Serial alum = new Nota_Serial();

            alum.setId_nota(tomaID);
            alum.setTipo(0);
            alum.setTitulo(txt_titulo.getText().toString());
            alum.setDescripcion(txt_descri.getText().toString());
            alum.setFecha_creacion(txt_fechaCreacion.getText().toString());

            alum.setFecha_limite("");
            alum.setHora_limite("");
            alum.setChecalo(0);


            atras.putExtra("miNotaEdit", alum);

            setResult(RESULT_OK, atras);
            finish();


        }else if(type_flag==3){

            Toast.makeText(ActivityDatos.this,R.string.confirmToast_editTarea,Toast.LENGTH_SHORT).show();

            Intent atras = new Intent();
            Nota_Serial alum = new Nota_Serial();

            alum.setId_nota(tomaID);
            alum.setTipo(1);
            alum.setTitulo(txt_titulo.getText().toString());
            alum.setDescripcion(txt_descri.getText().toString());
            alum.setFecha_creacion(txt_fechaCreacion.getText().toString());

            alum.setFecha_limite(txt_fechaLimit.getText().toString());
            alum.setHora_limite(txt_horaLimit.getText().toString());

            if(checkCheca.isChecked()){
                alum.setChecalo(1);
            }else{
                alum.setChecalo(0);
            }


            atras.putExtra("miTareaEdit", alum);

            setResult(RESULT_OK, atras);
            finish();

        }else if(type_flag==4){

            Toast.makeText(ActivityDatos.this,R.string.confirmToast_inNoti,Toast.LENGTH_SHORT).show();

            Intent atras = new Intent();
            Alerta alert = new Alerta();

            alert.setId_tarea(tomaID_Alerta_Tarea);
            alert.setTituloAlerta(txt_titulo.getText().toString());
            alert.setDescripcionAlerta(txt_descri.getText().toString());
            alert.setFechaAlerta(txt_fechaLimit.getText().toString());
            alert.setHoraAlerta(txt_horaLimit.getText().toString());


            atras.putExtra("miAlerta", alert);

            setResult(RESULT_OK, atras);
            finish();

        }else if(type_flag==5){

            Toast.makeText(ActivityDatos.this,R.string.confirmToast_editNoti,Toast.LENGTH_SHORT).show();

            Intent atras = new Intent();
            Alerta alert = new Alerta();

            alert.setId_alerta(tomaID_Alerta);
            alert.setId_tarea(tomaID_Alerta_Tarea);
            alert.setTituloAlerta(txt_titulo.getText().toString());
            alert.setDescripcionAlerta(txt_descri.getText().toString());
            alert.setFechaAlerta(txt_fechaLimit.getText().toString());
            alert.setHoraAlerta(txt_horaLimit.getText().toString());

            atras.putExtra("miAlertaEdit", alert);

            setResult(RESULT_OK, atras);
            finish();

        }

    }









    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txt_fechaLimit.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }

            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }

        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txt_horaLimit.setText(hourOfDay + ":" + minute);
                }

            }, mHour, mMinute, false);
            timePickerDialog.show();

        }

    }

}
