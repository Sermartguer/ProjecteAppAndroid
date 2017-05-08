package com.herprogramacion.restaurantericoparico.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.fragments.FragmentFavorite;
import com.herprogramacion.restaurantericoparico.fragments.FragmentPendientes;
import com.herprogramacion.restaurantericoparico.fragments.FragmentVisto;
import com.herprogramacion.restaurantericoparico.fragments.FragmentoInicio;
import com.herprogramacion.restaurantericoparico.fragments.FragmentoPeliculas;
import com.herprogramacion.restaurantericoparico.fragments.FragmentoSeries;
import com.herprogramacion.restaurantericoparico.models.Comidas;
import com.herprogramacion.restaurantericoparico.ui.database.TareasSQLiteActivity;
import com.herprogramacion.restaurantericoparico.ui.init.MorePopMovie;
import com.herprogramacion.restaurantericoparico.ui.init.MorePopSer;
import com.herprogramacion.restaurantericoparico.ui.init.NowPlaying;
import com.herprogramacion.restaurantericoparico.ui.init.OnAir;
import com.herprogramacion.restaurantericoparico.ui.init.TopRatedS;
import com.herprogramacion.restaurantericoparico.ui.init.test;

import java.util.Locale;

import static com.herprogramacion.restaurantericoparico.R.layout.actividad_principal;


public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    public static String language="es";
    private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {

        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(actividad_principal);

        agregarToolbar();



        Comidas.MorePopSer.clear();
        Comidas.Movies.clear();
        Comidas.MorePopular.clear();
        Comidas.NowPlaying.clear();
        Comidas.OnAir.clear();
        Comidas.TopRatedS.clear();
        if (!compruebaConexion(this)) {
            startActivity(new Intent(this,NoConnection.class));
            Toast.makeText(getBaseContext(),"Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
            finish();
        }
        startActivity(new Intent(this, test.class));
        startActivity(new Intent(this, MorePopMovie.class));
        startActivity(new Intent(this, NowPlaying.class));
        startActivity(new Intent(this, TopRatedS.class));
        startActivity(new Intent(this, MorePopSer.class));
        startActivity(new Intent(this, OnAir.class));
        //startActivity(new Intent(this, MorePopMovie.class));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }

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

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Toast alert = Toast.makeText(this,"Valor Position"+sharedpreferences.getString(Name, ""),Toast.LENGTH_LONG);

        //alert.show();
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            /*Inicio*/
            case R.id.item_inicio:
                //startActivity(new Intent(this, test.class));
                fragmentoGenerico = new FragmentoInicio();
                break;
            /*Peliculas*/
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoPeliculas();
                break;
            /*Series*/
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoSeries();
                break;
            /*Search*/
            case R.id.item_search:
                startActivity(new Intent(this, BookListActivity.class));
                break;
             /*SearchTV*/
            case R.id.item_search1:
                startActivity(new Intent(this, SearchListTV.class));
                break;
            /*Contact*/
            case R.id.item_contact:
                startActivity(new Intent(this, ActividadContact.class));
                break;
            case R.id.item_forum:
                startActivity(new Intent(this, ForumActivity.class));
            break;
              /*Favoritos*/
            case R.id.item_favorite:
                fragmentoGenerico = new FragmentFavorite();
                break;
            /*Favoritos*/
            case R.id.item_visto:
                fragmentoGenerico = new FragmentVisto();
                break;

            /*Favoritos*/
            case R.id.item_pendiente:
                fragmentoGenerico = new FragmentPendientes();
                break;

            case R.id.item_sql:
                startActivity(new Intent(this, TareasSQLiteActivity.class));
                break;
            case R.id.en:
                change_lang("en");
                break;
            case R.id.es:
                change_lang("es");
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static String getlang(){
        return language;
    }
    private void change_lang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        this.language=lang;
        //String lang_default = Locale.getDefault().getLanguage();
        //Toast toast1 = Toast.makeText(getApplicationContext(), lang_default, Toast.LENGTH_SHORT);
        //toast1.show();

        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
        //getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        finish();
        Intent refresh = new Intent(ActividadPrincipal.this, ActividadPrincipal.class);
        startActivity(refresh);
    }
    public static boolean compruebaConexion(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

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
}
