package com.herprogramacion.restaurantericoparico.ui.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 08/05/2017.
 */

public class LawyersDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lawyers.db";

    public LawyersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Lawyers.LawyerEntry.TABLE_NAME + " ("
                + Lawyers.LawyerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Lawyers.LawyerEntry.ID + " TEXT,"
                + Lawyers.LawyerEntry.NAME + " TEXT,"
                + Lawyers.LawyerEntry.RATING + " TEXT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }


    public void saveSite(String id, String name, String rating) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("INSERT INTO Lawyers VALUES (" + "'" + id + "'," + "'" + id + "'," + "'" + name + "'," + "'" + rating + "',)");
        database.close();
    }

    public void deleteSite(String id) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM Lawyers WHERE " + "ID = '" + id + "'");
        database.close();
    }

    public List<String> mySiteList (){
        List<String> myList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + "Lawyers", null);
        while (cursor.moveToNext()){
            myList.add(cursor.getString(0));
        }
        cursor.close();
        database.close();
        return myList;
    }

    public List<String> mySiteListID (){
        List<String> myList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT ID FROM " + "Lawyers", null);
        while (cursor.moveToNext()){
            myList.add(cursor.getString(0));
        }
        cursor.close();
        database.close();
        return myList;
    }
}