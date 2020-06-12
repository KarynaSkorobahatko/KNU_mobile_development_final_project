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

import java.util.ArrayList;

public class Find1 extends AppCompatActivity {
    Button back;
    TextView textView;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_find1);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        back = (Button) findViewById(R.id.btnBack);
        textView = (TextView) findViewById(R.id.textView);

        String selection = "production <= 250";
        Cursor c = db.query(DBHelper.TABLE_WHEAT, null, selection, null,null,null,null);
        textView.append("Список сортів, що є озимі та з вегетаційним періодом до 250 днів:\n" );
        if(c!= null){
            if(c.moveToFirst()){
                String str;
                ArrayList<String> array_list = new ArrayList<String>();
                do{
                    String cn = c.getColumnName(1);
                    str = c.getString(c.getColumnIndex(cn));
                    array_list.add(str);

                }while(c.moveToNext());
                textView.append(array_list.toString() + "\n");
            }

            c.close();
        }else{
            textView.setText("Збігів не знайдено!");
        }

        dbHelper.close();

    }

    public void Back(View v){
        Intent intent = new Intent(this, Lab4Activity.class);
        startActivity(intent);
    }
}
