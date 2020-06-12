package com.example.Karyna_Labs.Lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Karyna_Labs.R;

public class Add extends AppCompatActivity {

    Button add, btnBack;
    EditText etId, etYear, etYield, etProduction;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_add);

        add = (Button) findViewById(R.id.add);
        btnBack = (Button) findViewById(R.id.btnBack);

        etYear = (EditText) findViewById(R.id.etYear);
        etYield = (EditText) findViewById(R.id.etYield);
        etProduction = (EditText) findViewById(R.id.etProduction);

        dbHelper = new DBHelper(this);
    }

    public void Add(View v){
        String year = etYear.getText().toString();
        String yield = etYield.getText().toString();
        String production = etProduction.getText().toString();

        etYear.setText("");
        etYield.setText("");
        etProduction.setText("");

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_YEAR, year);
        contentValues.put(DBHelper.KEY_YIELD, yield);
        contentValues.put(DBHelper.KEY_PRODUCTION, production);

        database.insert(DBHelper.TABLE_WHEAT, null, contentValues);
    }

    public void Back(View v){
        Intent intent = new Intent(this, Lab4Activity.class);
        startActivity(intent);
    }
}