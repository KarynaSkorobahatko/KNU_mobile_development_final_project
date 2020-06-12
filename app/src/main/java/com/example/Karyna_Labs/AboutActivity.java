package com.example.Karyna_Labs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView text_view_about = findViewById(R.id.text_view_about);
        text_view_about.setText("Скоробагатько Карина Олександрівна\n3-й курс\nгрупа ТТП-3");
    }
}
