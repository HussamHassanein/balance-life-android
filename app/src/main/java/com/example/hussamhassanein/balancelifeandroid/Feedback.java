package com.example.hussamhassanein.balancelifeandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hussamhassanein.balancelifeandroid.planCategories.Social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Feedback extends AppCompatActivity {

   ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        getSupportActionBar().setTitle("Feedback");

        l= (ListView) findViewById(R.id.feedbackshow);

        //checked

        String [] catogery={"Social","Exercise","StudiesAndWork"};
        int[]  percent ={100, 30, 60};
       myAdaptar adapter=new myAdaptar(this,catogery,percent);
        l.setAdapter(adapter);
    }

}

class myAdaptar  extends ArrayAdapter<String>
{   Context context;
     int[]   percent;
     String[] catogery;
     myAdaptar(Context c, String[] cate , int[] per)
     {
         super(c ,R.layout.barlayout,R.id.bartext,cate);
         this.context=c;
         this.percent=per;
         this.catogery=cate;
     }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflator.inflate(R.layout.barlayout,parent,false);

        ProgressBar progressBar= (ProgressBar) row.findViewById(R.id.progressBar);
        TextView textbar= (TextView) row.findViewById(R.id.bartext);

        progressBar.setProgress(percent[position]);
        textbar.setText(catogery[position]);
        return row;
    }
}