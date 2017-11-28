package com.example.hussamhassanein.balancelifeandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
   TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        getSupportActionBar().setTitle("Feedback");



        t= (TextView) findViewById(R.id.textView3);

        SharedPreferences share = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String plan = share.getString("Social", null);
        String[] splitArray = null;
        try {
            splitArray = plan.split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }


   List list =  new ArrayList<String>();
        Collections.addAll(list, splitArray);


        for(int i = 0 ;i < splitArray.length;i++){
            if(list.get(i).equals("")){
                AlertDialog alertDialog = new AlertDialog.Builder(Feedback.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please ");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
               // list.remove(i);
                list.remove( new Integer(i));
            }

        }
        splitArray = (String[]) list.toArray(new String[list.size()]);
        String item = "[";
         for (int i =0; i< list.size();i++){
            item += "," + list.get(i);
         }
        item += "]";
         t.setText(item);
    }
}