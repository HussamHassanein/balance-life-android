package com.example.hussamhassanein.balancelifeandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Feedback extends AppCompatActivity {
   TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        getSupportActionBar().setTitle("Feedback");
        t= (TextView) findViewById(R.id.textView3);
        SharedPreferences shared=getSharedPreferences("Mydata", Context.MODE_PRIVATE);
             String get= shared.getString("SocialChecked",null);
     t.setText(get);
    }
}