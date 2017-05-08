package com.herprogramacion.restaurantericoparico.ui.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Comida;
import com.herprogramacion.restaurantericoparico.models.Comidas;
import com.herprogramacion.restaurantericoparico.ui.utils.Find;
import com.herprogramacion.restaurantericoparico.ui.init.Singleton;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class DetailActivity  extends AppCompatActivity {
    private static final String EXTRA_POSITION = "com.herprogramacion.cursospoint.extra.POSITION";
    public static int position1;
    private static List<Comida> itemss;
    private static int tipo1;
    int search;
    private TextView name;
    private TextView detail_star2;
    private TextView detail_descriptio2;
    private ImageView ivBookCover;
    private TextView p1;
    private TextView r1;
    private TextView p2;
    private TextView r2;
    private ImageView i1;
    private ImageView i2;
    private ImageView i3;
    private TextView p3;
    private TextView r3;
    private TextView p4;
    private TextView r4;
    private ImageView i4;
    private TextView p5;
    private TextView r5;
    private ImageView i5;
    private TextView p6;
    private TextView r6;
    private ImageView i6;
    private String idfilm;
    private Comida comida;
    private Comida comidaw;
    private Comida videos;
    private LinearLayout bgElement;
    private final String[] video=new String[1];
    private String[] nombre;
    private String[] profile;
    private String[] caracter;
    private String urlvideo;
    private TextView gen;
    private ImageView uri;
    private ImageView uriyt;
    private String urlhomepage;
    private TextView time;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_test);
        ivBookCover = (ImageView) findViewById(R.id.detail_imag2);
        name = (TextView) findViewById(R.id.detail_namedet);
        detail_star2 = (TextView) findViewById(R.id.dt);
        detail_descriptio2=(TextView) findViewById(R.id.detail_descriptio2);
        idfilm=itemss.get(position1).toString();
        bgElement=(LinearLayout) findViewById(R.id.container);
        p1=(TextView)findViewById(R.id.p1);
        r1=(TextView)findViewById(R.id.r1);
        p2=(TextView)findViewById(R.id.p2);
        r2=(TextView)findViewById(R.id.r2);
        i1=(ImageView)findViewById(R.id.i1);
        i2=(ImageView)findViewById(R.id.i2);
        p3=(TextView)findViewById(R.id.p3);
        r3=(TextView)findViewById(R.id.r3);
        i3=(ImageView)findViewById(R.id.i3);
        p4=(TextView)findViewById(R.id.p4);
        r4=(TextView)findViewById(R.id.r4);
        i4=(ImageView)findViewById(R.id.i4);
        p5=(TextView)findViewById(R.id.p5);
        r5=(TextView)findViewById(R.id.r5);
        i5=(ImageView)findViewById(R.id.i5);
        p6=(TextView)findViewById(R.id.p6);
        r6=(TextView)findViewById(R.id.r6);
        i6=(ImageView)findViewById(R.id.i6);
        time=(TextView)findViewById(R.id.time);
        gen=(TextView)findViewById(R.id.g1);
        if (!compruebaConexion(this)) {
            startActivity(new Intent(this,NoConnection.class));
            Toast.makeText(getBaseContext(),"Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
            finish();
        }
        uriyt=(ImageView) findViewById(R.id.imageButton);
        uriyt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(urlhomepage.equals("")) {
                    //startActivity(new Intent(DetailActivity.this, Pop.class));
                    uriyt.setVisibility(View.GONE);
                }else{
                    Uri uri = Uri.parse("https://www.youtube.com/watch?v="+urlvideo);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
        uri=(ImageView)findViewById(R.id.uri);
        uri.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(urlhomepage.equals("")) {
                    startActivity(new Intent(DetailActivity.this, Pop.class));
                }else{
                    Uri uri = Uri.parse(urlhomepage);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });


        setToolbar();
        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);
        setupViews(itemss,position,idfilm);
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)// Habilitar Up Button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }






    private void setupViews(final List<Comida> items, int position, String idfilm) {
        this.position1 = position;
        comida=new Comida();
        comidaw=new Comida();
        videos=new Comida();

        Picasso.with(this).load(Uri.parse(items.get(position1).getImg())).error(R.drawable.ic_nocover).into(ivBookCover);

        //Comida detailCourse = Comidas.getCourseByPosition(items,position);
        Toast.makeText(this, ""+itemss.get(position1).toString(), Toast.LENGTH_SHORT).show();
        videos.getExtraVideos(itemss.get(position1).toString(),new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {

                        // display comma separated list of publishers
                        final JSONArray publisher = response.getJSONArray("results");
                        final int numPublishers = publisher.length();
                        final String[] publishers = new String[numPublishers];
                        final String[] characters = new String[numPublishers];
                        final String[] imgs = new String[numPublishers];
                        for (int i = 0; i < 1; ++i) {

                            JSONObject pro = publisher.getJSONObject(i);
                            String name = pro.getString("key");
                            video[i] = name;
                        }
                        urlvideo = video[0];
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        });
        comidaw.getExtraPersons(itemss.get(position1).toString(),tipo1,new JsonHttpResponseHandler(){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    // display comma separated list of publishers
                    final JSONArray publisher = response.getJSONArray("cast");
                    final int numPublishers = publisher.length();
                   if(numPublishers<1){
                       p1.setVisibility(View.GONE);
                       r1.setVisibility(View.GONE);
                       i1.setVisibility(View.GONE);
                       p2.setVisibility(View.GONE);
                       r2.setVisibility(View.GONE);
                       i2.setVisibility(View.GONE);
                       p3.setVisibility(View.GONE);
                       r3.setVisibility(View.GONE);
                       i3.setVisibility(View.GONE);
                   }else if(numPublishers<2&&numPublishers>0) {
                       final String[] publishers = new String[numPublishers];
                       final String[] characters = new String[numPublishers];
                       final String[] imgs = new String[numPublishers];
                       caracter = new String[numPublishers];
                       nombre = new String[numPublishers];
                       profile = new String[numPublishers];
                       for (int i = 0; i < numPublishers; ++i) {

                           JSONObject pro = publisher.getJSONObject(i);
                           String name = pro.getString("name");
                           String character = pro.getString("character");
                           String img = pro.getString("profile_path");
                           publishers[i] = name;
                           characters[i] = character;
                           imgs[i] = img;
                           caracter[i] = character;
                           nombre[i] = name;
                           profile[i] = img;

                       }
                       Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i1);

                       p1.setText(publishers[0]);
                       r1.setText(characters[0]);
                       p2.setVisibility(View.GONE);
                       r2.setVisibility(View.GONE);
                       i2.setVisibility(View.GONE);
                       p3.setVisibility(View.GONE);
                       r3.setVisibility(View.GONE);
                       i3.setVisibility(View.GONE);
                   }else if(numPublishers>1&&numPublishers<3){
                       final String[] publishers = new String[numPublishers];
                       final String[] characters = new String[numPublishers];
                       final String[] imgs = new String[numPublishers];
                       caracter = new String[numPublishers];
                       nombre = new String[numPublishers];
                       profile = new String[numPublishers];
                       for (int i = 0; i < numPublishers; ++i) {

                           JSONObject pro = publisher.getJSONObject(i);
                           String name = pro.getString("name");
                           String character = pro.getString("character");
                           String img = pro.getString("profile_path");
                           publishers[i] = name;
                           characters[i] = character;
                           imgs[i] = img;
                           caracter[i] = character;
                           nombre[i] = name;
                           profile[i] = img;

                       }
                       Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i1);
                       Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[1]))).error(R.drawable.ic_nocover).into(i1);

                       p1.setText(publishers[0]);
                       r1.setText(characters[0]);
                       p2.setText(characters[1]);
                       r2.setText(characters[1]);
                       p3.setVisibility(View.GONE);
                       r3.setVisibility(View.GONE);
                       i3.setVisibility(View.GONE);
                }else if(numPublishers>3){
                       final String[] publishers = new String[numPublishers];
                       final String[] characters=new String[numPublishers];
                       final String[] imgs=new String[numPublishers];
                       caracter=new String[numPublishers];
                       nombre=new String[numPublishers];
                       profile=new String[numPublishers];
                       for (int i = 0; i < numPublishers; ++i) {

                           JSONObject pro= publisher.getJSONObject(i);
                           String name=pro.getString("name");
                           String character=pro.getString("character");
                           String img=pro.getString("profile_path");
                           publishers[i] = name;
                           characters[i]=character;
                           imgs[i]=img;
                           caracter[i]=character;
                           nombre[i]=name;
                           profile[i]=img;

                       }
                       Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i1);
                       Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[1]))).error(R.drawable.ic_nocover).into(i2);
                       Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[2]))).error(R.drawable.ic_nocover).into(i3);

                       p1.setText(publishers[0]);
                       r1.setText(characters[0]);
                       p2.setText(publishers[1]);
                       r2.setText(characters[1]);
                       p3.setText(publishers[2]);
                       r3.setText(characters[2]);
                   }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }

                if(tipo1==1){
                    try {

                        // display comma separated list of publishers
                        final JSONArray publisher = response.getJSONArray("crew");
                        final int numPublishers = publisher.length();
                        final String[] publishers = new String[numPublishers];
                        final String[] characters=new String[numPublishers];
                        final String[] imgs=new String[numPublishers];
                        caracter=new String[numPublishers];
                        nombre=new String[numPublishers];
                        profile=new String[numPublishers];
                        for (int i = 0; i < numPublishers; ++i) {

                            JSONObject pro= publisher.getJSONObject(i);
                            String name=pro.getString("name");
                            String character=pro.getString("job");
                            String img=pro.getString("profile_path");
                            publishers[i] = name;
                            characters[i]=character;
                            imgs[i]=img;
                            caracter[i]=character;
                            nombre[i]=name;
                            profile[i]=img;

                        }
                        Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i4);
                        Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[1]))).error(R.drawable.ic_nocover).into(i5);
                        Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[2]))).error(R.drawable.ic_nocover).into(i6);

                        p4.setText(publishers[0]);
                        r4.setText(characters[0]);
                        p5.setText(publishers[1]);
                        r5.setText(characters[1]);
                        p6.setText(publishers[2]);
                        r6.setText(characters[2]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(tipo1==2){
                    try {

                        // display comma separated list of publishers
                        final JSONArray publisher = response.getJSONArray("crew");
                        final int numPublishers = publisher.length();
                        if(numPublishers<1){
                            p4.setVisibility(View.GONE);
                            r4.setVisibility(View.GONE);
                            i4.setVisibility(View.GONE);
                            p5.setVisibility(View.GONE);
                            r5.setVisibility(View.GONE);
                            i5.setVisibility(View.GONE);
                            p6.setVisibility(View.GONE);
                            r6.setVisibility(View.GONE);
                            i6.setVisibility(View.GONE);
                        }else if(numPublishers<2&&numPublishers>0){
                            final String[] publishers = new String[numPublishers];
                            final String[] characters=new String[numPublishers];
                            final String[] imgs=new String[numPublishers];
                            caracter=new String[numPublishers];
                            nombre=new String[numPublishers];
                            profile=new String[numPublishers];
                            for (int i = 0; i < numPublishers; ++i) {

                                JSONObject pro= publisher.getJSONObject(i);
                                String name=pro.getString("name");
                                String character=pro.getString("job");
                                String img=pro.getString("profile_path");
                                publishers[i] = name;
                                characters[i]=character;
                                imgs[i]=img;
                                caracter[i]=character;
                                nombre[i]=name;
                                profile[i]=img;

                            }
                            Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i4);
                            p4.setText(publishers[0]);
                            r4.setText(characters[0]);
                            p5.setVisibility(View.GONE);
                            r5.setVisibility(View.GONE);
                            i5.setVisibility(View.GONE);
                            p6.setVisibility(View.GONE);
                            r6.setVisibility(View.GONE);
                            i6.setVisibility(View.GONE);
                        }else if(numPublishers>1&&numPublishers<3){
                            final String[] publishers = new String[numPublishers];
                            final String[] characters=new String[numPublishers];
                            final String[] imgs=new String[numPublishers];
                            caracter=new String[numPublishers];
                            nombre=new String[numPublishers];
                            profile=new String[numPublishers];
                            for (int i = 0; i < numPublishers; ++i) {

                                JSONObject pro= publisher.getJSONObject(i);
                                String name=pro.getString("name");
                                String character=pro.getString("job");
                                String img=pro.getString("profile_path");
                                publishers[i] = name;
                                characters[i]=character;
                                imgs[i]=img;
                                caracter[i]=character;
                                nombre[i]=name;
                                profile[i]=img;

                            }
                            Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i4);
                            Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[1]))).error(R.drawable.ic_nocover).into(i5);

                            p4.setText(publishers[0]);
                            r4.setText(characters[0]);
                            p5.setText(publishers[1]);
                            r5.setText(characters[1]);
                            p6.setVisibility(View.GONE);
                            r6.setVisibility(View.GONE);
                            i6.setVisibility(View.GONE);
                        }else if(numPublishers>3){
                            final String[] publishers = new String[numPublishers];
                            final String[] characters=new String[numPublishers];
                            final String[] imgs=new String[numPublishers];
                            caracter=new String[numPublishers];
                            nombre=new String[numPublishers];
                            profile=new String[numPublishers];
                            for (int i = 0; i < numPublishers; ++i) {

                                JSONObject pro= publisher.getJSONObject(i);
                                String name=pro.getString("name");
                                String character=pro.getString("job");
                                String img=pro.getString("profile_path");
                                publishers[i] = name;
                                characters[i]=character;
                                imgs[i]=img;
                                caracter[i]=character;
                                nombre[i]=name;
                                profile[i]=img;

                            }
                            Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[0]))).error(R.drawable.ic_nocover).into(i4);
                            Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[1]))).error(R.drawable.ic_nocover).into(i5);
                            Picasso.with(getBaseContext()).load(Uri.parse(items.get(position1).getTest(imgs[2]))).error(R.drawable.ic_nocover).into(i6);

                            p4.setText(publishers[0]);
                            r4.setText(characters[0]);
                            p5.setText(publishers[1]);
                            r5.setText(characters[1]);
                            p6.setText(publishers[1]);
                            r6.setText(characters[1]);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }}

        });





        comida.getExtraBookDetails(itemss.get(position1).toString(),tipo1, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(tipo1==1) {
                    try {
                        name.setText(response.getString("title"));
                        //date2.setText(response.getString("release_date"));
                        //budget2.setText(Integer.toString(response.getInt("budget")) + " $");
                        //revenue2.setText(Integer.toString(response.getInt("revenue")) + " $");
                        if (response.has("overview")){
                            if (response.getString("overview").equals("null")){
                                detail_descriptio2.setText("No hay datos que mostrarle");
                            }else {
                                detail_descriptio2.setText(response.getString("overview"));
                            }
                        }else {
                            detail_descriptio2.setText("No hay datos que mostrarle");
                        }

                        detail_star2.setText(response.getString("vote_average"));
                        urlhomepage = (response.getString("homepage"));
                        String runt = response.getString("runtime");
                        if (runt.equals(0)) {
                            time.setVisibility(View.GONE);
                        } else{
                            time.setText(response.getString("runtime") + "min");
                    }
                        if (response.has("production_companies")) {

                            // display comma separated list of publishers
                            final JSONArray publisher = response.getJSONArray("production_companies");
                            final int numPublishers = publisher.length();
                            final String[] publishers = new String[numPublishers];
                            for (int i = 0; i < numPublishers; ++i) {

                                JSONObject pro= publisher.getJSONObject(i);
                                String name=pro.getString("name");
                                publishers[i] = name;
                            }

                            //production2.setText(TextUtils.join(", ", publishers));
                        }
                        if (response.has("genres")){
                            final JSONArray gene = response.getJSONArray("genres");
                            final int ngen = gene.length();
                            final String[] publishers = new String[ngen];
                            for (int i = 0; i < ngen; ++i) {

                                JSONObject pro= gene.getJSONObject(i);
                                String name=pro.getString("name");
                                publishers[i] = name;
                            }
                            gen.setText(TextUtils.join(", ", publishers));

                        }else{
                            gen.setText("No esta");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if (tipo1==2){
                    try {
                        name.setText(response.getString("original_name"));
                        //date2.setText(response.getString("last_air_date"));
                        //budget2.setText(response.getString("original_language"));
                        // revenue2.setText(response.getString("name"));
                        if (response.has("overview")){
                            if (response.getString("overview").equals("null")){
                                detail_descriptio2.setText("No hay datos que mostrarle");
                            }else {
                                detail_descriptio2.setText(response.getString("overview"));
                            }
                        }else {
                            detail_descriptio2.setText("No hay datos que mostrarle");
                        }
                        detail_star2.setText(response.getString("vote_average"));
                        urlhomepage=(response.getString("homepage"));
                        time.setVisibility(View.GONE);
                        /*if (response.has("production_companies")) {

                            // display comma separated list of publishers
                            final JSONArray publisher = response.getJSONArray("production_companies");
                            final int numPublishers = publisher.length();
                            final String[] publishers = new String[numPublishers];
                            for (int i = 0; i < numPublishers; ++i) {

                                JSONObject pro= publisher.getJSONObject(i);
                                String name=pro.getString("name");
                                publishers[i] = name;
                            }

                            //production2.setText(TextUtils.join(", ", publishers));
                        }*/
                        if (response.has("genres")){
                            final JSONArray gene = response.getJSONArray("genres");
                            final int ngen = gene.length();
                            final String[] publishers = new String[ngen];
                            for (int i = 0; i < ngen; ++i) {

                                JSONObject pro= gene.getJSONObject(i);
                                String name=pro.getString("name");
                                publishers[i] = name;
                            }
                            gen.setText(TextUtils.join(", ", publishers));

                        }else{
                            gen.setText("No esta");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                // Obtener intent de la actividad padre
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Comprobar si DetailActivity no se creó desde CourseActivity
                if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot()) {
                    // Construir de nuevo la tarea para ligar ambas actividades
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Terminar con el método correspondiente para Android 5.x
                    this.finishAfterTransition();
                    return true;
                }
                // Dejar que el sistema maneje el comportamiento del up button
                return false;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static void launch(Activity context, int position, View sharedView ,List<Comida> items,int tipo) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        tipo1=tipo;
        position1=position;
        itemss=items;

        context.startActivity(intent);
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

    public void addfav1(View v) {
        //Book book = (Book) getIntent().getSerializableExtra(AdaptadorSeries.BOOK_DETAIL_KEY);
        String title1 = name.getText().toString();
        String idfil = itemss.get(position1).toString();
        String stars2 = detail_star2.getText().toString();
        String image = itemss.get(position1).getImg();
        try {
            if (tipo1 == 2) {
                search = Find.findFavTV(idfil);
                if (search == -1) {
                    Singleton.getInstance().addToArray(idfil);
                    // mDbHelper.createTodo(idfil, title1, stars2);
                    Toast.makeText(this, "Serie Añadida a Favoritos", Toast.LENGTH_SHORT).show();
                    Comidas.SeriesFav.add(new Comida(idfil, stars2, title1, image, null, 3f, 1, 778, "03/24/2017", image));
                } else {
                    Toast.makeText(this, "Se a Eliminado de Favoritos", Toast.LENGTH_SHORT).show();
                    // dbHelper.deleteTodo(search);
                    Comidas.SeriesFav.remove(search);
                    Singleton.getInstance().getArray().remove(search);
                }
            } else {
                search = Find.findFavFilm(idfil);
                if (search == -1) {
                    Singleton.getInstance().addToArrayFilmFav(idfil);
                    Comidas.MoviesFav.add(new Comida(idfil, stars2, title1, image, null, 3f, 1, 778, "03/24/2017", image));
                    Toast.makeText(this, "Serie Añadida a Favoritos", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Se a Eliminado de Favoritos", Toast.LENGTH_SHORT).show();
                    // dbHelper.deleteTodo(search);
                    Comidas.MoviesFav.remove(search);
                    Singleton.getInstance().getArrayFilmFav().remove(search);
                }
            }
        }catch (Exception e) {

        }
    }

    public void addfav2(View v) {
        //Book book = (Book) getIntent().getSerializableExtra(AdaptadorSeries.BOOK_DETAIL_KEY);
        String title1 = name.getText().toString();
        String idfil = itemss.get(position1).toString();
        String stars2 = detail_star2.getText().toString();
        String image = itemss.get(position1).getImg();
        try {
            if (tipo1 == 2) {
                search = Find.findVisTV(idfil);
                if (search == -1) {
                    Singleton.getInstance().addToArraytvvis(idfil);
                    // mDbHelper.createTodo(idfil, title1, stars2);
                    Toast.makeText(this, "Serie Añadida a Visto", Toast.LENGTH_SHORT).show();
                    Comidas.SeriesVis.add(new Comida(idfil, stars2, title1, image, null, 3f, 1, 778, "03/24/2017", image));
                } else {
                    Toast.makeText(this, "Se a Eliminado de Visto", Toast.LENGTH_SHORT).show();
                    // dbHelper.deleteTodo(search);
                    Comidas.SeriesVis.remove(search);
                    Singleton.getInstance().getArraytvvis().remove(search);
                }
            } else {
                search = Find.findVisFilm(idfil);
                if (search == -1) {
                    Singleton.getInstance().addToArrayfilmvis(idfil);
                    Comidas.MoviesVis.add(new Comida(idfil, stars2, title1, image, null, 3f, 1, 778, "03/24/2017", image));
                    Toast.makeText(this, "Serie Añadida a Visto", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Se a Eliminado de Visto", Toast.LENGTH_SHORT).show();
                    // dbHelper.deleteTodo(search);
                    Comidas.MoviesVis.remove(search);
                    Singleton.getInstance().getArrayfilmvis().remove(search);
                }
            }
        } catch (Exception e) {

        }

    }

    public void addfav3(View v) {
        //Book book = (Book) getIntent().getSerializableExtra(AdaptadorSeries.BOOK_DETAIL_KEY);
        String title1 = name.getText().toString();
        String idfil = itemss.get(position1).toString();
        String stars2 = detail_star2.getText().toString();
        String image = itemss.get(position1).getImg();
        try {
            if (tipo1 == 2) {
                search = Find.findPenTV(idfil);
                if (search == -1) {
                    Singleton.getInstance().addToArraytvpen(idfil);
                    // mDbHelper.createTodo(idfil, title1, stars2);
                    Toast.makeText(this, "Serie Añadida a Pendiente", Toast.LENGTH_SHORT).show();
                    Comidas.SeriesPen.add(new Comida(idfil, stars2, title1, image, null, 3f, 1, 778, "03/24/2017", image));
                } else {
                    Toast.makeText(this, "Se a Eliminado de Pendiente", Toast.LENGTH_SHORT).show();
                    // dbHelper.deleteTodo(search);
                    Comidas.SeriesPen.remove(search);
                    Singleton.getInstance().getArraytvpen().remove(search);
                }
            } else {
                search = Find.findPenFilm(idfil);
                if (search == -1) {
                    Singleton.getInstance().addToArrayfilmpen(idfil);
                    Comidas.MoviesPen.add(new Comida(idfil, stars2, title1, image, null, 3f, 1, 778, "03/24/2017", image));
                    Toast.makeText(this, "Serie Añadida a Pendiente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Se a Eliminado de Pendiente", Toast.LENGTH_SHORT).show();
                    // dbHelper.deleteTodo(search);
                    Comidas.MoviesPen.remove(search);
                    Singleton.getInstance().getArrayfilmpen().remove(search);
                }
            }
        }catch (Exception e) {

        }
    }

}
