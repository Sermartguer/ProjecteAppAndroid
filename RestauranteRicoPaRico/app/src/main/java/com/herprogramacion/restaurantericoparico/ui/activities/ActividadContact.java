package com.herprogramacion.restaurantericoparico.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Contacto;

/**
 * Created by 1DAW on 21/03/17.
 */

public class ActividadContact extends ActionBarActivity implements OnMapReadyCallback {
    private Button supp;
    private GoogleMap mMap;
    private Button faq;
    SharedPreferences sharedpreferences;
    private static final LatLng Onti = new LatLng(38.82273628844499, -0.606729340475447);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_contact);
        faq=(Button)findViewById(R.id.butonfaq);
        faq.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(ActividadContact.this, FAQs.class);
                startActivity(i);
            }
        });
        supp = (Button) findViewById(R.id.supp);
        supp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ActividadContact.this, Contacto.class);
                startActivity(i);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        agregarToolbar();
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    public void showAustralia(View v) {
        // Wait until map is ready
        if (mMap == null) {
            return;
        }
        // Create bounds that include all locations of the map
        LatLngBounds.Builder boundsBuilder = LatLngBounds.builder()
                .include(Onti);
        // Move camera to show all markers and locations
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20));
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkers();

        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    showAustralia(null);
                }
            });
        }
    }

    private void addMarkers() {
        mMap.addMarker(new MarkerOptions()
                .position(Onti)
                .title("Brisbane"));

}}