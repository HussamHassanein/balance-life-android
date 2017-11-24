package com.example.hussamhassanein.balancelifeandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Review extends AppCompatActivity {
   // TextView rrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");

    }


   /* rrr=(TextView)findViewById(R.id.textView);
    SharedPreferences share=getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
    String result=share.getString("Social","");
        rrr.setText(result);
        */
}