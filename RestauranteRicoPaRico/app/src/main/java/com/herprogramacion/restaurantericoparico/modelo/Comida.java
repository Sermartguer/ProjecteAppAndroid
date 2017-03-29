
package com.herprogramacion.restaurantericoparico.modelo;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Comida {
    private String openLibraryId;
    private String Rating;
    private String nombre;
    private int idDrawable;
    private String descripcion;
    private int position;
    private int voteCount;
    private String date;
    private String img;

    public  String getImg(){return "https://image.tmdb.org/t/p/w185_and_h278_bestv2"+gIMG();}

    public Comida(String Rating, String nombre, int idDrawable , String descripcion , float rating , int position, int voteCount, String date, String img) {
        this.Rating = Rating;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.descripcion = descripcion;
        this.position = position;
        this.voteCount=voteCount;
        this.date=date;
        this.img=img;
    }
    public String getRating() {
        return Rating;
    }
    private String gIMG(){
        return img;
    }
    public String getOpenLibraryId() {
        return openLibraryId;
    }
    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public String getDescripcion(){
        return  descripcion;
    }
    public int getVoteCount(){return voteCount;}
    public String getDate(){return date;}

    public int getPosition(){return position;}
}
