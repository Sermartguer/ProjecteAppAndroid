package com.herprogramacion.restaurantericoparico.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

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

    SharedPreferences sharedpreferences;
    TextView name;
    TextView email;
    TextView nombre;
    ToggleButton btnToggle;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_configuracion);
        nombre=(TextView) findViewById(R.id.textView19);
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
            nombre.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }


        agregarToolbar();
    }


    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (sharedpreferences.getString(Name, "").equals("Dark")){
            toolbar.setBackgroundColor(Color.parseColor("#088A08"));

        }else if (sharedpreferences.getString(Name, "").equals("Light")){
            toolbar.setBackgroundColor(Color.parseColor("#1281ff"));
        }
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    public void Save(View view) {

        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.commit();
        if (sharedpreferences.getString(Name, "").equals("es")){

        }
    }

    public void clear(View view) {
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        name.setText("");
        email.setText("");

    }

    public void Get(View view) {
        nombre=(TextView) findViewById(R.id.textView19);
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
            nombre.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}