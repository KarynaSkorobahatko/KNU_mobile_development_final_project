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

public class Load extends AppCompatActivity {

    Button btnBack;
    TextView textView;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_load);

        btnBack = (Button) findViewById(R.id.btnBack);

        textView = (TextView) findViewById(R.id.textView);

        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_WHEAT,null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_YEAR);
            int priceIndex = cursor.getColumnIndex(DBHelper.KEY_YIELD);
            int changesIndex = cursor.getColumnIndex(DBHelper.KEY_PRODUCTION);

            do{
                textView.append(" ID = " + cursor.getInt(idIndex) +
                        ", Name = " + cursor.getString(nameIndex) +
                        ", Period = " + cursor.getInt(changesIndex) +
                        ", Prolificness = " + cursor.getString(priceIndex) + "\n");
            }while(cursor.moveToNext());
        }else{
            textView.setText("Database is empty");
        }
        cursor.close();
    }

    public void Back(View v){
        Intent intent = new Intent(this, Lab4Activity.class);
        startActivity(intent);
    }
}