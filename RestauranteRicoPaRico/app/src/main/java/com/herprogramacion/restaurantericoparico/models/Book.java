package com.herprogramacion.restaurantericoparico.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 1DAW on 21/03/17.
 */

public class Book implements Serializable {
    private String openLibraryId;
    private String title;
    private String img;
    private String stars;
    private String stars2;
    private String rdate;
    private String votecount;
    private String description;
    private String budget;
    private String revenue;
    private String status;
    private String runtime;
    private String author;
    private String name;
    private String frdate;
    private String episodes;
    private String seasons;

    public String getOpenLibraryId() {
        return openLibraryId;
    }
    public String getStars() {
        return stars;
    }
    public String getname() {
        return name;
    }
    public String getStars2() {
        return stars2;
    }
    public String getTitle() {
        return title;
    }
    public String getRdate() {
        return rdate;
    }
    public String getBudget() {return budget;}
    public String getVotecount() {return votecount;}
    public String getRevenue() {return revenue;}
    public String getStatus() {return status;}
    public String getRuntime() {return runtime;}
    public String getImg(){return "https://image.tmdb.org/t/p/w185_and_h278_bestv2"+img;}
    public String getAuthor() {
        return author;
    }
    public String getFRdate() {
        return frdate;
    }
    public String getSeasons() {
        return seasons;
    }
    public String getEpisodes() {
        return episodes;
    }
    // Get medium sized book cover from covers API


    // Get large sized book cover from covers API


    // Returns a Book given the expected JSON
    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            if (jsonObject.has("id")) {
                book.openLibraryId = jsonObject.getString("id");
            } else if(jsonObject.has("id")) {
                final JSONArray ids = jsonObject.getJSONArray("id");
                book.openLibraryId = ids.getString(0);
            }
            book.title = jsonObject.has("original_title") ? jsonObject.getString("original_title"):"";
            book.img=jsonObject.has("poster_path")? jsonObject.getString("poster_path"):"";
            book.name = jsonObject.has("original_name") ? jsonObject.getString("original_name"):"";
            book.stars=jsonObject.has("vote_average") ? jsonObject.getString("vote_average"):"";
            book.stars2=jsonObject.has("popularity") ? jsonObject.getString("popularity"):"";
            book.rdate=jsonObject.has("release_date") ? jsonObject.getString("release_date"):"";
            book.votecount=jsonObject.has("vote_count") ? jsonObject.getString("vote_count"):"";
            book.description=jsonObject.has("overview") ? jsonObject.getString("overview"):"";
            book.budget=jsonObject.has("budget") ? jsonObject.getString("budget"):"";
            book.revenue=jsonObject.has("revenue") ? jsonObject.getString("revenue"):"";
            book.runtime=jsonObject.has("runtime") ? jsonObject.getString("runtime"):"";
            book.status=jsonObject.has("status") ? jsonObject.getString("status"):"";
            book.frdate=jsonObject.has("first_air_date") ? jsonObject.getString("first_air_date"):"";
            book.episodes=jsonObject.has("number_of_episodes") ? jsonObject.getString("number_of_episodes"):"";
            book.seasons=jsonObject.has("number_of_seasons") ? jsonObject.getString("number_of_seasons"):"";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return book;
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Book> fromJson(JSONArray jsonArray) {
        ArrayList<Book> books = new ArrayList<Book>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJson(bookJson);
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }
}