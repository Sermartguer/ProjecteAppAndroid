package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
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
    private TextView name;
   // private TextView date2;
   // private TextView budget2;
   // private TextView revenue2;
   // private TextView production2;
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
    private String idfilm;
    private Comida comida;
    private Comida comidaw;

    private LinearLayout bgElement;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_test);
        ivBookCover = (ImageView) findViewById(R.id.detail_imag2);
        name = (TextView) findViewById(R.id.detail_namedet);
        //date2 = (TextView) findViewById(R.id.date2);
        //budget2 = (TextView) findViewById(R.id.budget2);
        //revenue2 = (TextView) findViewById(R.id.revenue2);
       // production2 = (TextView) findViewById(R.id.production3);
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
         Toast alert = Toast.makeText(this,"Valor Position"+position1,Toast.LENGTH_LONG);
        alert.show();
         alert = Toast.makeText(this,"Valor Position"+itemss.get(position1).toString(),Toast.LENGTH_LONG);
        alert.show();
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
        Picasso.with(this).load(Uri.parse(items.get(position1).getImg())).error(R.drawable.ic_nocover).into(ivBookCover);

        //Comida detailCourse = Comidas.getCourseByPosition(items,position);
        Toast.makeText(this, ""+itemss.get(position1).toString(), Toast.LENGTH_SHORT).show();

        comidaw.getExtraPersons(itemss.get(position1).toString(),new JsonHttpResponseHandler(){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {

                        // display comma separated list of publishers
                        final JSONArray publisher = response.getJSONArray("cast");
                        final int numPublishers = publisher.length();
                        final String[] publishers = new String[numPublishers];
                        final String[] characters=new String[numPublishers];
                        final String[] imgs=new String[numPublishers];
                        for (int i = 0; i < 3; ++i) {

                            JSONObject pro= publisher.getJSONObject(i);
                            String name=pro.getString("name");
                            String character=pro.getString("character");
                            String img=pro.getString("profile_path");
                            publishers[i] = name;
                            characters[i]=character;
                            imgs[i]=img;

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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

        });





        comida.getExtraBookDetails(itemss.get(position1).toString(),tipo1, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(tipo1==1) {
                    try {
                        name.setText(response.getString("original_title"));
                        //date2.setText(response.getString("release_date"));
                        //budget2.setText(Integer.toString(response.getInt("budget")) + " $");
                        //revenue2.setText(Integer.toString(response.getInt("revenue")) + " $");
                        detail_descriptio2.setText(response.getString("overview"));
                        detail_star2.setText(response.getString("vote_average"));
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if (tipo1==2){
                    try {
                        name.setText(response.getString("original_name"));
                        //date2.setText(response.getString("last_air_date"));
                        //budget2.setText(response.getString("original_language"));
                       // revenue2.setText(response.getString("name"));
                        detail_descriptio2.setText(response.getString("overview"));
                        detail_star2.setText(response.getString("vote_average"));
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

}
