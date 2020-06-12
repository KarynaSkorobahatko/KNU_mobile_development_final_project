package com.example.Karyna_Labs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Karyna_Labs.Lab4.Lab4Activity;
import com.example.Karyna_Labs.Lab5.Lab5Activity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showInfo(View view){
        Intent about = new Intent(this,AboutActivity.class);
        startActivity(about);
    }

    public void lab1(View view){
        Intent count = new Intent(this,Count1Activity.class);
        startActivity(count);
    }

    public void lab2(View view){
        Intent count2 = new Intent(this,Count2Activity.class);
        startActivity(count2);
    }

    public void lab3(View view){
        Intent count3 = new Intent(this,Count3Activity.class);
        startActivity(count3);
    }

    public void lab4 (View view){
        Intent lab4 = new Intent(this, Lab4Activity.class);
        startActivity(lab4);
    }

    public void lab5 (View view){
        Intent lab5 = new Intent(this, Lab5Activity.class);
        startActivity(lab5);
    }

    public void lab6 (View view){
        Intent lab6 = new Intent(this, Lab6Activity.class);
        startActivity(lab6);
    }

    public void help (View view){
        Intent help = new Intent(this, HelpActivity.class);
        startActivity(help);
    }


}
