package com.jadwalkuliah.jadwal_kuliah;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Jadwal extends Activity {
    public static Jadwal ma;
    ListView lv;
    String[] hari = new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};
    String[] daftar;
    Cursor cursor;
    Menu menu;
    DataHelper dbcenter;
    public static Jadwal j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itambah = new Intent(Jadwal.this, TambahJadwal.class);
                startActivity(itambah);
            }
        });

        j = this;
        dbcenter = new DataHelper(this);
        RefreshList();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hari);
        lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item = position;
                String itemText = (String) lv.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), "Jadwal " + itemText, Toast.LENGTH_LONG).show();

            }
        });

    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

    }
}
