package com.herprogramacion.restaurantericoparico.modelo;

import com.herprogramacion.restaurantericoparico.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/03/17.
 */

public class Comidas {
    public static List<Comida> getComidasPopulares() {
        return COMIDAS_POPULARES;
    }

    public static List<Comida> getBEBIDAS() {
        return BEBIDAS;
    }

    public static List<Comida> getPOSTRES() {
        return POSTRES;
    }

    public static List<Comida> getPLATILLOS() {
        return PLATILLOS;
    }

    public static List<Comida> getMovies() {return  Movies;}
    public static List<Comida> getMorePopular() {return  MorePopular;}
    public static List<Comida> getNowPlaying() {return NowPlaying;}
    public static List<Comida> getTopRatedS() {return TopRatedS;}
    public static List<Comida> getMorePopSer() {return MorePopSer;}
    public static List<Comida> getOnAir(){return OnAir;}

    public static List<Comida> getuno() {
        return uno;
    }
    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();
    public static final List<Comida> BEBIDAS = new ArrayList<>();
    public static final List<Comida> POSTRES = new ArrayList<>();
    public static final List<Comida> PLATILLOS = new ArrayList<>();
    public static final List<Comida> uno = new ArrayList<>();

    public static final List<Comida> Movies = new ArrayList<>();
    public static final List<Comida> MorePopular= new ArrayList<>();
    public static final List<Comida> NowPlaying=new ArrayList<>();
    public static final List<Comida> TopRatedS=new ArrayList<>();
    public static final List<Comida> MorePopSer=new ArrayList<>();
    public static final List<Comida> OnAir=new ArrayList<>();

    public static final List<Comida> MAS_VALORADAS = new ArrayList<>();
    static {
        COMIDAS_POPULARES.add(new Comida("5.4f", "Beauty and the Beast", R.drawable.beea,"Explore the mysterious and dangerous home of the king of the apes as a team of explorers ventures deep inside the treacherous, primordial island.",3f,0,305,"03/17/2017",""));
        COMIDAS_POPULARES.add(new Comida("5.4f", "A Cure for Wellness", R.drawable.cure,"Descripcion",3f,1,778,"03/24/2017",""));
        COMIDAS_POPULARES.add(new Comida("5.4f", "Ghost in the Shell", R.drawable.gost,"Descripcion",3f,2,34,"03/31/2017",""));
        COMIDAS_POPULARES.add(new Comida("5.4f", "Grave", R.drawable.gr,"Descripcion",3f,3,1168,"03/17/2017",""));
        COMIDAS_POPULARES.add(new Comida("5.4f", "Imperium", R.drawable.imp,"Descripcion",3f,4,589,"03/17/2017",""));

        //Movies.add(new Comida(1.1f, "sad", R.drawable.cure,"",3f,1,778,"03/24/2017"));

        PLATILLOS.add(new Comida("5.4f", "Camarones Tismados", R.drawable.camarones,"Descripcion",3f,5,589,"03/17/2017",""));
        PLATILLOS.add(new Comida("5.4f", "Rosca Herbárea", R.drawable.rosca,"Descripcion",3f,6,589,"03/17/2017",""));
        PLATILLOS.add(new Comida("5.4f", "Sushi Extremo", R.drawable.sushi,"Descripcion",3f,7,589,"03/17/2017",""));
        PLATILLOS.add(new Comida("5.4f", "Sandwich Deli", R.drawable.sandwich,"Descripcion",3f,8,589,"03/17/2017",""));
        PLATILLOS.add(new Comida("5.4f", "Lomo De Cerdo Austral", R.drawable.lomo_cerdo,"Descripcion",3f,9,589,"03/17/2017",""));


        uno.add(new Comida("5.4f", "Lomo De Cerdo Austral", R.drawable.lomo_cerdo,"Descripcion",3f,9,589,"03/17/2017",""));

            BEBIDAS.add(new Comida("5.4f", "Taza de Café", R.drawable.cafe,"Descripcion",3f,10,589,"03/17/2017",""));
            BEBIDAS.add(new Comida("5.4f", "Coctel Tronchatoro", R.drawable.coctel,"Descripcion",3f,11,589,"03/17/2017",""));
            BEBIDAS.add(new Comida("5.4f", "Jugo Natural", R.drawable.jugo_natural,"Descripcion",3f,12,589,"03/17/2017",""));
            BEBIDAS.add(new Comida("5.4f", "Coctel Jordano", R.drawable.coctel_jordano,"Descripcion",3f,13,589,"03/17/2017",""));
            BEBIDAS.add(new Comida("5.4f", "Botella Vino Tinto Darius", R.drawable.vino_tinto,"Descripcion",3f,14,589,"03/17/2017",""));

        POSTRES.add(new Comida("5.4f", "Postre De Vainilla", R.drawable.postre_vainilla,"Descripcion",3f,15,589,"03/17/2017",""));
        POSTRES.add(new Comida("5.4f", "Flan Celestial", R.drawable.flan_celestial,"Descripcion",3f,16,589,"03/17/2017",""));
        POSTRES.add(new Comida("5.4f", "Cupcake Festival", R.drawable.cupcakes_festival,"Descripcion",3f,17,589,"03/17/2017",""));
        POSTRES.add(new Comida("5.4f", "Pastel De Fresa", R.drawable.pastel_fresa,"Descripcion",3f,18,589,"03/17/2017",""));
        POSTRES.add(new Comida("5.4f", "Muffin Amoroso", R.drawable.muffin_amoroso,"Descripcion",3f,19,589,"03/17/2017",""));
    }
    public static Comida getCourseByPosition(List<Comida> items,int position) {
        if(items == POSTRES){
            return items.get(position);
        }else if(items == PLATILLOS){
            return items.get(position);
        }else if(items == BEBIDAS){
            return items.get(position);
        }else{
            return COMIDAS_POPULARES.get(position);
        }

    }
}


