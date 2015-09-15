package com.example.android.hargabarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bocist-8 on 14/09/15.
 */
public class DatabaseBarang extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "databarang";
    public static final String NAMA_BARANG = "nama";
    public static final String HARGA_BARANG = "harga";

    public DatabaseBarang(Context context){
        super (context,DATABASE_NAME,null,1);

    }

    public void createTable(SQLiteDatabase sqLiteDatabase){
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS daftarHarga");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists daftarHarga (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, harga TEXT);");
    }

    public void generateData(SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        cv.put(NAMA_BARANG,"torrent");
        cv.put(HARGA_BARANG,"500000");
        sqLiteDatabase.insert("daftarHarga",NAMA_BARANG,cv);

    }

    public void addData(SQLiteDatabase sqLiteDatabase, String eng, String ind){
        ContentValues cv = new ContentValues();
        cv.put(NAMA_BARANG,eng);
        cv.put(HARGA_BARANG,ind);
        sqLiteDatabase.insert("daftarHarga", NAMA_BARANG, cv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void tes(){


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
