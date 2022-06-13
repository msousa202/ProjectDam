package com.example.taskg7;

import static java.sql.Types.VARCHAR;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "TASKS.DB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
        db.execSQL("CREATE TABLE tasks (_id INTEGER PRIMARY KEY " + "AUTOINCREMENT, name VARCHAR(128))");
        db.execSQL("INSERT INTO tasks (name) VALUES ('Task1 - Mário Sousa')");
        db.execSQL("INSERT INTO tasks (name) VALUES ('Task2 - David Fortunato')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }
}
