package com.herprogramacion.restaurantericoparico.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.herprogramacion.restaurantericoparico.ui.init.Singleton;

import java.util.ArrayList;

/**
 * Created by Sergio on 08/05/2017.
 */

public class DBAdapter {
    //Campos de la BD
    public static final String KEY_ROWID = "_id";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_RATING = "RATING";
    private static final String DATABASE_TABLE = "todo";
    private Context context;
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public DBAdapter open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        //fill_db();
        return this;
    }

    public void close() {
        //database.execSQL("delete from todo");
        dbHelper.close();
    }

    public void fill_db() {
        ContentValues initialValues = null;
        for (int i=0;i<10;i++) {
            initialValues = createContentValues("category_" + i, "summary_" + i, "description_" + i);
            database.insert(DATABASE_TABLE, null, initialValues);
        }
    }

    /**
     * Crea una nueva tarea, si esto va bien retorna la
     * rowId de la tarea, de lo contrario retorna -1
     */
    public long createTodo(String category, String summary, String description) {
        ContentValues initialValues = createContentValues(category, summary, description);
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    //Actualiza la tarea
    public boolean updateTodo(long rowId, String category, String summary, String description) {
        ContentValues updateValues = createContentValues(category, summary, description);
        return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //Borra la tarea
    public boolean deleteTodo(long rowId) {
        return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //Returna un Cursor que contiene todos los items
    public Cursor fetchAllTodos() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_ID, KEY_NAME, KEY_RATING }, null, null, null,
                null, null);
    }

    //Returna un Cursor que contiene la info del item
    public Cursor fetchTodo(long rowId) throws SQLException {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_ROWID, KEY_ID, KEY_NAME, KEY_RATING },
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    private ContentValues createContentValues(String category, String summary, String description) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, category);
        values.put(KEY_NAME, summary);
        values.put(KEY_RATING, description);
        return values;
    }
    public void idfavtv(){
        ArrayList<String> mArrayList = new ArrayList<String>();
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_ROWID, KEY_ID, KEY_NAME, KEY_RATING },
                KEY_ROWID + "=" + null , null, null, null, null, null);
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast()) {
            Singleton.getInstance().addToArray(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_ID))); //add the item
            mCursor.moveToNext();
        }
    }
}
