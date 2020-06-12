package com.example.Karyna_Labs.Lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Karyna_Labs.R;

public class Lab4Activity extends AppCompatActivity {

    String year[] = {"Glamur", "Pleasure","Arcturus","Andria F1","Escalibur F1","Lumina F1","Megaton F1","Tronka F1","Perfect F1","Bandit F1","HMX 2390 F1"};
    int yield[] = {17, 18, 11, 140, 240, 270, 133, 130, 230, 18};
    int production[] = {85, 118, 71, 94, 85, 83, 86, 115, 118, 80};
    Button btnAdd, btnRead, btnUpdate, btnDelete, btnFind1, btnFind2;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnFind1 = (Button) findViewById(R.id.btnFind1);
        btnFind2 = (Button) findViewById(R.id.btnFind2);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor c = db.query(DBHelper.TABLE_WHEAT, null,null,null,null,null, null);
        if(c.getCount() == 0){
            ContentValues cv = new ContentValues();
            for(int i = 0; i < year.length; i++){
                cv.put(DBHelper.KEY_YEAR, year[i]);
                cv.put(DBHelper.KEY_YIELD, yield[i]);
                cv.put(DBHelper.KEY_PRODUCTION, production[i]);
                db.insert(DBHelper.TABLE_WHEAT, null, cv);
            }
        }
        c.close();
        dbHelper.close();
    }

    public void onClick(View v){

        switch(v.getId()){

            case R.id.btnAdd:
                Intent intent = new Intent(this, Add.class);
                startActivity(intent);
                break;
            case R.id.btnRead:
                intent = new Intent(this, Load.class);
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, Update.class);
                startActivity(intent);
                break;
            case R.id.btnDelete:
                intent = new Intent(this, Delete.class);
                startActivity(intent);
                break;
            case R.id.btnFind1:
                intent = new Intent(this, Find1.class);
                startActivity(intent);
                break;
            case R.id.btnFind2:
                intent = new Intent(this, Find2.class);
                startActivity(intent);
                break;
        }

    }
}