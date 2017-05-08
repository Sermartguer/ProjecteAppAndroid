package com.herprogramacion.restaurantericoparico.ui.activities;

/**
 * Created by Sergio on 08/05/2017.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Comida;
import com.herprogramacion.restaurantericoparico.models.Comidas;
import com.herprogramacion.restaurantericoparico.models.Book;
import com.herprogramacion.restaurantericoparico.net.BookClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Segui on 27/04/2017.
 */

public class DetailTV extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvName;
    private BookClient client;
    private TextView RDate;
    private TextView Seasons;
    private TextView Episodes;
    private TextView Stars;
    private TextView desc;
    private TextView fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.detail_imag1);
        tvName = (TextView) findViewById(R.id.detail_nam1);
        RDate = (TextView) findViewById(R.id.date11);
        Seasons = (TextView) findViewById(R.id.seasons);
        Episodes = (TextView) findViewById(R.id.episodes);
        Stars = (TextView) findViewById(R.id.detail_star1);
        desc = (TextView) findViewById(R.id.detail_descriptio1);
        fav = (TextView) findViewById(R.id.actividad_botones1);


        // Use the book to populate the data into our views
        Book book = (Book) getIntent().getSerializableExtra(SearchListTV.BOOK_DETAIL_KEY);
        loadBook(book);
    }

    // Populate data for the book
    private void loadBook(Book book) {
        //change activity title
        this.setTitle(book.getname());
        // Populate data
        Picasso.with(this).load(Uri.parse(book.getImg())).error(R.drawable.ic_nocover).into(ivBookCover);
        tvName.setText(book.getname());
        Stars.setText(book.getStars()+"/10");
        RDate.setText(book.getFRdate());

        // fetch extra book data from books API
        client = new BookClient();
        client.getExtraBookDetails1(book.getOpenLibraryId(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Episodes.setText(Integer.toString(response.getInt("number_of_episodes"))+" $");
                    Seasons.setText(Integer.toString(response.getInt("number_of_seasons"))+" $");
                    desc.setText(response.getString("overview"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_share) {
            setShareIntent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent() {
        ImageView ivImage = (ImageView) findViewById(R.id.ivBookCover);
        final TextView tvName = (TextView)findViewById(R.id.tvName);
        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(ivImage);
        // Construct a ShareIntent with link to image
        Intent shareIntent = new Intent();
        // Construct a ShareIntent with link to image
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, (String)tvName.getText());
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        // Launch share menu
        startActivity(Intent.createChooser(shareIntent, "Share Image"));

    }

    // Returns the URI path to the Bitmap displayed in cover imageview
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;


    }
    public void addfav(View v){
        Book book = (Book) getIntent().getSerializableExtra(BookListActivity.BOOK_DETAIL_KEY);
        Toast.makeText(this, "Se ha añadido con exito", Toast.LENGTH_SHORT).show();
        //int num=Functions.findPeli(book);
        //if(num!=-1) {
        Comidas.SeriesFav.add(new Comida(book.getOpenLibraryId(), book.getStars(), book.getname(), book.getImg(), null, 3f, 1, 778, "03/24/2017", book.getImg()));
        //}else{
        //    Toast.makeText(this, "Ya esta en favoritos", Toast.LENGTH_SHORT).show();
        //}


    }
}
