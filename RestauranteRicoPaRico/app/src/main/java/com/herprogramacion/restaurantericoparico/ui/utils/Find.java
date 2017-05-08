package com.herprogramacion.restaurantericoparico.ui.utils;

import com.herprogramacion.restaurantericoparico.ui.init.Singleton;

/**
 * Created by Sergio on 08/05/2017.
 */

public class Find {
    public static int findFavTV(String id) {
        for (int i = 0; i<=(Singleton.getInstance().getArray().size()-1); i++){
            if((Singleton.getInstance().getArray().get(i)).equals(id) )
                return i;
        }
        return -1;
    }

    public static int findFavFilm(String id) {
        for (int i = 0; i<=(Singleton.getInstance().getArrayFilmFav().size()-1); i++){
            if((Singleton.getInstance().getArrayFilmFav().get(i)).equals(id) )
                return i;
        }
        return -1;
    }


    public static int findVisTV(String id) {
        for (int i = 0; i<=(Singleton.getInstance().getArraytvvis().size()-1); i++){
            if((Singleton.getInstance().getArraytvvis().get(i)).equals(id) )
                return i;
        }
        return -1;
    }

    public static int findVisFilm(String id) {
        for (int i = 0; i<=(Singleton.getInstance().getArrayfilmvis().size()-1); i++){
            if((Singleton.getInstance().getArrayfilmvis().get(i)).equals(id) )
                return i;
        }
        return -1;
    }



    public static int findPenTV(String id) {
        for (int i = 0; i<=(Singleton.getInstance().getArraytvpen().size()-1); i++){
            if((Singleton.getInstance().getArraytvpen().get(i)).equals(id) )
                return i;
        }
        return -1;
    }

    public static int findPenFilm(String id) {
        for (int i = 0; i<=(Singleton.getInstance().getArrayfilmpen().size()-1); i++){
            if((Singleton.getInstance().getArrayfilmpen().get(i)).equals(id) )
                return i;
        }
        return -1;
    }
}