package com.example.taskg7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    //private Context context;
    private SQLiteDatabase database;
    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }
    public void open() throws SQLException {database =
            dbHelper.getWritableDatabase(); }
    public void close() {
        dbHelper.close();
    }
    public Cursor fetch() {
        String[] columns = new String[] { "_id", "name" };
        Cursor cursor = database.query("tasks", columns, null, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_one(long id) {
        String _id;
        Cursor cursor = database.rawQuery("SELECT * FROM tasks WHERE _id = " + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void insert(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        database.insert("tasks", null, contentValues);
    }
    public int update(long id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        int i = database.update("tasks", contentValues, "_id=" + id,
                null);
        return i;
    }
    public void delete(long id) {
        database.delete("tasks", "_id=" + id, null);
    }
}
