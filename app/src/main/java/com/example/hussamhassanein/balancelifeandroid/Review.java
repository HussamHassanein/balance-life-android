package com.example.hussamhassanein.balancelifeandroid;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Review extends AppCompatActivity {
     public static final String DEFAULT ="N/A";
     EditText hhh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");
    hhh= (EditText) findViewById(R.id.viewhour);
  //      SharedPreferences share= getSharedPreferences("Mydata", Context.MODE_PRIVATE);
    //    String hou=share.getString("hours"," ");
      //  hhh.setText(hou);
   rev();
    }

 public void rev() {
        SharedPreferences share= getSharedPreferences("Mydata", Context.MODE_PRIVATE);
       String hou=share.getString("hours", "");
           hhh.setText(hou);

    }


   /* rrr=(TextView)findViewById(R.id.textView);
    SharedPreferences share=getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
    String result=share.getString("Social","");
        rrr.setText(result);
        */
}