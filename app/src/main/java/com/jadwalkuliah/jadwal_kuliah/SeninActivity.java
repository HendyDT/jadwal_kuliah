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
import android.widget.Button;
import android.widget.ListView;

public class SeninActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    private void JadwalSenin() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jadwal where hari = 'Senin'", null);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(SeninActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {

                        }
                    }
                });
                builder.create().show();
            }
        }));
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();

    }
}
