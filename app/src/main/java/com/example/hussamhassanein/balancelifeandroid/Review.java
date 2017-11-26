package com.example.hussamhassanein.balancelifeandroid;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Review extends AppCompatActivity {
     public static final String DEFAULT ="N/A";
     EditText hhh;
    ArrayList<String> selectecItem= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");
    //hhh= (EditText) findViewById(R.id.viewhour);
        ListView view=(ListView)findViewById(R.id.checkview);
        view.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items={"work","study","maths"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.rowlayout,R.id.txt_lan,items);
          view.setAdapter(adapter);




          view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

              @Override
              public void onItemClick(AdapterView<?> parent,View view , int position , long id) {
                String selectedItems=((TextView)view).getText().toString();
                 if(selectecItem.contains(selectedItems)){
                     selectecItem.remove(selectedItems); //uncheck item
                 }
                 else selectecItem.add(selectedItems);

              }
          });
  //      SharedPreferences share= getSharedPreferences("Mydata", Context.MODE_PRIVATE);
    //    String hou=share.getString("hours"," ");
      //  hhh.setText(hou);
   //rev();

    }

    private void showrev() {





        }



    public void rev() {
        SharedPreferences share= getSharedPreferences("Mydata", Context.MODE_PRIVATE);
       String hou=share.getString("hours", "");
           hhh.setText(hou);

    }

    public void ShowChecked(View view) {

        String items="";

        for(String item:selectecItem){
            items+="-"+item+"\n";
        }
       // Toast.makeText(this,"You have selected \n", Toast.LENGTH_LONG);
   Toast.makeText(this,"You have selected \n" + items, Toast.LENGTH_LONG).show();
    }


   /* rrr=(TextView)findViewById(R.id.textView);
    SharedPreferences share=getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
    String result=share.getString("Social","");
        rrr.setText(result);
        */
}