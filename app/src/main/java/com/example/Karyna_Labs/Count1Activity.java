package com.example.Karyna_Labs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Count1Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
    }

    public void lab1(View view){
        double x = Double.parseDouble(((EditText)findViewById(R.id.argument_inp)).getText().toString());
        double y = 2;
        double a = 1.5;
        double D = (Math.pow(1.0 / Math.tan(Math.pow(a,3)),3) + (Math.pow(1.0 / Math.atan(a),2)))/(Math.pow(Math.pow(y,Math.tan(x)),0.5));
        ((TextView)findViewById(R.id.result_text)).setText(Double.toString(D));
    }

}