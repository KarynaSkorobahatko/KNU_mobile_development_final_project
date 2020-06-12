package com.example.Karyna_Labs.Lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Karyna_Labs.R;

public class Update extends AppCompatActivity {
    Button btnUpdate, btnBack;
    EditText etId, etYear, etYield, etProduction;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_update);

        btnUpdate = (Button) findViewById(R.id.add);
        btnBack = (Button) findViewById(R.id.btnBack);

        etId = (EditText) findViewById(R.id.etId);
        etYear = (EditText) findViewById(R.id.etYear);
        etYield = (EditText) findViewById(R.id.etYield);
        etProduction = (EditText) findViewById(R.id.etProduction);

        dbHelper = new DBHelper(this);
    }

    public void Update(View v){
        String id = etId.getText().toString();
        String year = etYear.getText().toString();
        String yield = etYield.getText().toString();
        String production = etProduction.getText().toString();

        etId.setText("");
        etYear.setText("");
        etYield.setText("");
        etProduction.setText("");

        if(id.equalsIgnoreCase("")){
            Toast toast = Toast.makeText(Update.this, "Enter ID", Toast.LENGTH_SHORT);
            toast.show();
        }

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_YEAR, year);
        contentValues.put(DBHelper.KEY_YIELD, yield);
        contentValues.put(DBHelper.KEY_PRODUCTION, production);

        int updCount = database.update(DBHelper.TABLE_WHEAT, contentValues, DBHelper.KEY_ID + "= ?", new String[] {id});
        Toast toast = Toast.makeText(Update.this, "Updates rows count = " + updCount, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void Back(View v){
        Intent intent = new Intent(this, Lab4Activity.class);
        startActivity(intent);
    }
}