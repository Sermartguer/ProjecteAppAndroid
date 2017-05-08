package com.herprogramacion.restaurantericoparico.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.herprogramacion.restaurantericoparico.R;

public class NoConnection extends AppCompatActivity {
private Button reconnect;
    private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {

        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        reconnect = (Button) findViewById(R.id.reconect);
        reconnect.setOnClickListener(myhandler);
    }

    View.OnClickListener myhandler=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (compruebaConexion()==false) {
                Toast.makeText(getBaseContext(),"Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
            }else {
                startActivity(new Intent(NoConnection.this, ActividadPrincipal.class));
            }
        }


    };
    public boolean compruebaConexion() {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }
    @Override
    public void onBackPressed() {

        //You may also add condition if (doubleBackToExitPressedOnce || fragmentManager.getBackStackEntryCount() != 0) // in case of Fragment-based add
        if (mRecentlyBackPressed) {
            mExitHandler.removeCallbacks(mExitRunnable);
            mExitHandler = null;
            super.onBackPressed();
        }
        else
        {
            mRecentlyBackPressed = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            mExitHandler.postDelayed(mExitRunnable, delay);
        }
    }
}
