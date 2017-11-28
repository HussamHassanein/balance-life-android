package com.example.hussamhassanein.balancelifeandroid;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
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
import java.util.regex.PatternSyntaxException;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Review extends AppCompatActivity {

   
    ListView l;
    ArrayList<String> checkedelement= new ArrayList<>();
    ArrayList<String> checkedExercise= new ArrayList<>();
    ArrayList<String> checkedStudies= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");

        Check("Social");
        Check("Exercise");
        Check("StudiesAndWork");
    }

    public void Check(String category){

        SharedPreferences share = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String plan = share.getString(category, null);
        String[] splitArray = null;
        try {
            splitArray = plan.split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }

        if(category.equals("Social")) {
            l = (ListView) findViewById(R.id.checkview);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
            l.setAdapter(adapter);
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItems = ((TextView) view).getText().toString();

                    if (checkedelement.contains(selectedItems)) {
                        checkedelement.remove(selectedItems); //uncheck item
                    } else checkedelement.add(selectedItems);

                }
            });
        }
        if(category.equals("Exercise")){
            l = (ListView) findViewById(R.id.exerciseList);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
            l.setAdapter(adapter);
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItems = ((TextView) view).getText().toString();

                    if (checkedExercise.contains(selectedItems)) {
                        checkedExercise.remove(selectedItems); //uncheck item
                    } else checkedExercise.add(selectedItems);

                }
            });

        }
        if(category.equals("StudiesAndWork")){
            l = (ListView) findViewById(R.id.studiesandworkList);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
            l.setAdapter(adapter);
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItems = ((TextView) view).getText().toString();

                    if (checkedStudies.contains(selectedItems)) {
                        checkedStudies.remove(selectedItems); //uncheck item
                    } else checkedStudies.add(selectedItems);

                }
            });
        }

    }





    public void ShowChecked(View view) {
        SharedPreferences shared=getSharedPreferences("Mydata",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= shared.edit();
        editor.putString("SocialChecked", String.valueOf(checkedelement));
        editor.putString("ExerciseChecked", String.valueOf(checkedExercise));
        editor.putString("StudiesChecked", String.valueOf(checkedStudies));

        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}