package com.example.Karyna_Labs.Lab4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Karyna_Labs.R;

public class Find2 extends AppCompatActivity {
    Button back;
    TextView textView;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_find2);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        back = (Button) findViewById(R.id.btnBack);
        textView = (TextView) findViewById(R.id.textView);
        String selection = "MAX(yield)";
        Cursor c = db.rawQuery("select (MAX(yield)) AS yields from wheat", null );
        textView.append("Найбільша врожайність: " );
        if(c!= null){
            if(c.moveToFirst()){
                String str = c.getString(c.getColumnIndex("yields"));

                textView.append(str + "\n");
            }
            c.close();
        }else{
            textView.setText("Збігів не знайдено!");
        }
        Cursor d = db.rawQuery("select (MIN(yield)) AS yields from wheat", null );
        textView.append("Найменша врожайність: " );
        if(d!= null){
            if(d.moveToFirst()){
                String str = d.getString(d.getColumnIndex("yields"));

                textView.append(str + "\n");
            }
            d.close();
        }else{
            textView.setText("Збігів не знайдено!");
        }
    }

    public void Back(View v){
        Intent intent = new Intent(this, Lab4Activity.class);
        startActivity(intent);
    }
}
