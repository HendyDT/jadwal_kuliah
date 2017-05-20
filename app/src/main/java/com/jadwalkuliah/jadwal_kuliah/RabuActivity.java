package com.jadwalkuliah.jadwal_kuliah;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RabuActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    private void JadwalRabu() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jadwal where hari = 'Rabu'", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Jadwal","Update Jadwal", "Hapus Jadwal"};
                AlertDialog.Builder builder = new AlertDialog.Builder(RabuActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatJadwal.class);
                                i.putExtra("nama_makul", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), EditJadwal.class);
                                in.putExtra("nama_makul", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from jadwal where nama_makul = '" + selection + "'");
                                JadwalRabu();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        }));
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}
