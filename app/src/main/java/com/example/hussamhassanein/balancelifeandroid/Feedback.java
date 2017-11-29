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

    double total1;
    double total2;
    double total3;

    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        getSupportActionBar().setTitle("Feedback");

        l= (ListView) findViewById(R.id.feedbackshow);

        //checked

        SharedPreferences sharef = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        double de=(double) sharef.getInt("SocialLength",0);
        double dee=(double)sharef.getInt("SoChLength",0);
        double dw=(double) sharef.getInt("ExLength",0);
        double dwe=(double)sharef.getInt("ExChLength",0);
        double dr=(double) sharef.getInt("StLength",0);
        double dre=(double)sharef.getInt("StChLength",0);


        total1 = Math.round((dee/ de * 100) * 10) / 10;
        total2 = Math.round((dwe/ dw * 100) * 10) / 10;
        total3 = Math.round((dre/ dr * 100) * 10) / 10;


        String [] catogery={"Social","Exercise","StudiesAndWork"};
        int[]  percent ={(int)total1, (int)total2, (int)total3};
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