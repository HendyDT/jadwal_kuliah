package com.jadwalkuliah.jadwal_kuliah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jadwalkuliah.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table jadwal(no integer primary key, nama_makul text null, ruang text null, jam text null, hari text null, dosen text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO jadwal(no, nama_makul, ruang, jam, hari, dosen) VALUES ('1', 'Manajemen Berbasis Pengetahuan', '05.03.06', '10.40','Selasa','Erik Hadi Saputra S.Kom, M.Eng');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}