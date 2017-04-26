package com.herprogramacion.restaurantericoparico.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.herprogramacion.restaurantericoparico.R;

/**
 * Actividad para la configuración de preferencias
 */

public class ActividadConfiguracion extends AppCompatActivity {
    private EditText etNombre;
    private EditText etApellido;
    private EditText etEmail;
    private EditText etCargo;
    private Button butonTheme;
    private SharedPreferences sharedPref;
    private TextView look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_configuracion);
        look=(TextView) findViewById(R.id.look);
        final SharedPreferences prefs = this.getSharedPreferences("com.example.miaplicacion", Context.MODE_PRIVATE);
        prefs.edit().putString("clave", "valor").apply();
        butonTheme = (Button) findViewById(R.id.button_theme);
            butonTheme.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String s = prefs.getString("clave", "valor_por_defecto");
                    look.setText(s);
                }
            });


        agregarToolbar();
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#00FF00"));
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }



}