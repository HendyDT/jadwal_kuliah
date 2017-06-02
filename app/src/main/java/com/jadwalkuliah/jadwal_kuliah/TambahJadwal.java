package com.jadwalkuliah.jadwal_kuliah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created on 11/05/2017.
 */

public class TambahJadwal extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5, text6;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);
        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(no, nama_makul, ruang, jam, hari, dosen) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        text5.getText().toString() + "','" +
                        text6.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Jadwal.j.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                finish();
            }
        });

    }
}
