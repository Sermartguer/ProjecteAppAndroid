package com.herprogramacion.restaurantericoparico.ui.init;

import java.util.ArrayList;

/**
 * Created by Sergio on 08/05/2017.
 */

public class Singleton  {

    private static Singleton mInstance;
    private ArrayList<String> list = null;
    private ArrayList<String> filmfav = null;

    private ArrayList<String> tvvis = null;
    private ArrayList<String> filmvis = null;

    private ArrayList<String> tvpen = null;
    private ArrayList<String> filmpen = null;

    public static Singleton getInstance() {
        if(mInstance == null)
            mInstance = new Singleton();

        return mInstance;
    }

    private Singleton() {
        list = new ArrayList<String>();
        filmfav = new ArrayList<String>();
        tvvis = new ArrayList<String>();
        filmvis = new ArrayList<String>();
        tvpen = new ArrayList<String>();
        filmpen = new ArrayList<String>();
    }
    // retrieve array from anywhere
    public ArrayList<String> getArray() {
        return this.list;
    }
    //Add element to array
    public void addToArray(String value) {
        list.add(value);
    }

    public ArrayList<String> getArrayFilmFav() {
        return this.filmfav;
    }
    //Add element to array
    public void addToArrayFilmFav(String value) {
        filmfav.add(value);
    }




    // retrieve array from anywhere
    public ArrayList<String> getArraytvvis() {
        return this.tvvis;
    }
    //Add element to array
    public void addToArraytvvis(String value) {
        tvvis.add(value);
    }

    public ArrayList<String> getArrayfilmvis() {
        return this.filmvis;
    }
    //Add element to array
    public void addToArrayfilmvis(String value) {
        filmvis.add(value);
    }


    // retrieve array from anywhere
    public ArrayList<String> getArraytvpen() {
        return this.tvpen;
    }
    //Add element to array
    public void addToArraytvpen(String value) {
        tvpen.add(value);
    }

    public ArrayList<String> getArrayfilmpen() {
        return this.filmpen;
    }
    //Add element to array
    public void addToArrayfilmpen(String value) {
        filmpen.add(value);
    }


}