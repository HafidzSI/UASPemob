package com.example.isumatsumi.barangapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Isumatsumi on 20/12/2018.
 */

public class DbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "inventory.db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_BARANG = "nama_barang";
    public static final String COLUMN_JENIS_BARANG = "jenis_barang";
    public static final String COLUMN_JUMLAH = "jumlah";
    public static final String COLUMN_KONDISI = "kondisi";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAMA_BARANG + " TEXT NOT NULL, " +
                COLUMN_JENIS_BARANG + " TEXT NOT NULL" +
                COLUMN_JUMLAH + "TEXT NOT NULL" +
                COLUMN_KONDISI + "TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAMA_BARANG, cursor.getString(1));
                map.put(COLUMN_JENIS_BARANG, cursor.getString(2));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nama_barang, String jenis_barang, String jumlah, String kondisi) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (nama_barang,jenis_barang,jumlah,kondisi) " +
                "VALUES ('" + nama_barang + "', '" + jenis_barang + "','"+ jumlah +"','"+ kondisi +"')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }
}
