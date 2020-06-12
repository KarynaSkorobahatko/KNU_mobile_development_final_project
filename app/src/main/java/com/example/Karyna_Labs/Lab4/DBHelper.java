package com.example.Karyna_Labs.Lab4;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "wheatDb";
    public static final String TABLE_WHEAT = "wheat";
    public static final String KEY_ID = "_id";
    public static final String KEY_YEAR = "year";
    public static final String KEY_YIELD = "yield";
    public static final String KEY_PRODUCTION = "production";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WHEAT + "(" + KEY_ID + " integer primary key," + KEY_YEAR + " integer," +
                KEY_YIELD + " integer," + KEY_PRODUCTION + " integer" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_WHEAT);

        onCreate(db);

    }
}