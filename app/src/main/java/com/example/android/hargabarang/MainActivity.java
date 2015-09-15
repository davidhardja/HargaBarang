package com.example.android.hargabarang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    SQLiteDatabase sqLiteDatabase = null;
    Cursor cursor = null;
    DatabaseBarang dataBarang = null;
    EditText namaBarang;
    TextView hargaBarang;
    EditText tambahBarang;
    EditText tambahHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBarang = new DatabaseBarang(this);
        sqLiteDatabase = dataBarang.getWritableDatabase();
        dataBarang.createTable(sqLiteDatabase);
        setContentView(R.layout.activity_main);
        namaBarang = (EditText)findViewById(R.id.nama_barang_edit_view);
        hargaBarang = (TextView)findViewById(R.id.harga_text_view);
        tambahBarang = (EditText)findViewById(R.id.tambah_barang_edit_view);
        tambahHarga = (EditText)findViewById(R.id.tambah_harga_edit_text);
        //dataBarang.generateData(sqLiteDatabase);
    }


    public void tambah(View view){
        String barangBaru = tambahBarang.getText().toString();
        String  hargaBaru = tambahHarga.getText().toString();
        dataBarang.addData(sqLiteDatabase,barangBaru,hargaBaru);
        Context context = getApplicationContext();
        CharSequence text = barangBaru + " dengan harga " + hargaBaru + " telah berhasil ditambahkan";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();

    }

    public void cari(View view){
        String result = "";
        String barangYangDicari = namaBarang.getText().toString();

        cursor = sqLiteDatabase.rawQuery("SELECT ID, nama, harga "
                + "FROM daftarHarga where nama='" + barangYangDicari + "' ORDER BY nama", null);

        if(cursor.moveToFirst()){
            result = "Rp "+cursor.getString(2);

            for (;!cursor.isAfterLast();cursor.moveToNext()){
                result = "Rp "+cursor.getString(2);

            }

        }

        if(result==""){
            result = "Barang tidak Ditemukan";

        }


        hargaBarang.setText(result);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        sqLiteDatabase.close();
        cursor.close();

    }

    public void pindah(View view){
        Intent intent = new Intent(this,ListDaftar.class);
        startActivity(intent);

    }

}


