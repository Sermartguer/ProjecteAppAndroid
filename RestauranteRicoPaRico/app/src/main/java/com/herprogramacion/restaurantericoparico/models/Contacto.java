package com.herprogramacion.restaurantericoparico.models;

/**
 * Created by Sergio on 25/04/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.herprogramacion.restaurantericoparico.R;

public class Contacto extends Activity implements View.OnClickListener{
private Button btnSendMail;
    private EditText etAsunto;
    private EditText etMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto);
        btnSendMail = (Button) findViewById(R.id.btnEnviarMai);
        btnSendMail.setOnClickListener(this);

    }

    public void onClick(View v){
        //final EditText etDe = (EditText) getView().findViewById(R.id.et_EmailDe);
        etAsunto = (EditText) findViewById(R.id.et_EmailAsunto);
        etMensaje = (EditText) findViewById(R.id.et_EmailMensaje);

        //String to = etDe.getText().toString();
        String subject = etAsunto.getText().toString();
        String message = etMensaje.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sermartguer@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Seleciona un cliente de correo"));
    }
}
