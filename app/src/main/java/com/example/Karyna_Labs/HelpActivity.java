package com.example.Karyna_Labs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class HelpActivity extends AppCompatActivity {
    Button lab1, lab2, lab3, lab4, lab5, lab6;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        lab1 = (Button) findViewById(R.id.lab1);
        lab2 = (Button) findViewById(R.id.lab2);
        lab3 = (Button) findViewById(R.id.lab3);
        lab4 = (Button) findViewById(R.id.lab4);
        lab5 = (Button) findViewById(R.id.lab5);
        lab6 = (Button) findViewById(R.id.lab6);

        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://drive.google.com/file/d/1sLI_xgLnFZeGRE7BMnYsmyLOmuyjSDmm/view?usp=sharing"));
                startActivity(browserIntent);
            }
        });
        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://drive.google.com/file/d/1AEWMl5w_Qf8U5nRXTe2GC6jtquGyGC09/view?usp=sharing"));
                startActivity(browserIntent);
            }
        });
        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://drive.google.com/file/d/10dqUp8ZvAWubjg_OEyDhJCNIZg1GRlHo/view?usp=sharing"));
                startActivity(browserIntent);
            }
        });
        lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/document/d/1XtfCbJgGeC9kxJO4J7CO68SdJHHg_E1107P4OAAif8M/edit"));
                startActivity(browserIntent);
            }
        });
        lab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/document/d/1gEfl9o1nUtSamtdMFj9LruApcTohRcUSiQP2XOnhY7Y/edit"));
                startActivity(browserIntent);
            }
        });
        lab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/document/d/1BC__NAwNJB-MgMzgHtjlSnci0p_JTqKq15eRKcdqWDc/edit"));
                startActivity(browserIntent);
            }
        });
    }
}
