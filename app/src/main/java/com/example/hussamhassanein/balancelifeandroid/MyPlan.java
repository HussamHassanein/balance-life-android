package com.example.hussamhassanein.balancelifeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hussamhassanein.balancelifeandroid.planCategories.Exercise;
import com.example.hussamhassanein.balancelifeandroid.planCategories.Sleep;
import com.example.hussamhassanein.balancelifeandroid.planCategories.Social;
import com.example.hussamhassanein.balancelifeandroid.planCategories.StudiesAndWork;

/**
 * Created by HussamHassanein on 2017-11-22.
 */

public class MyPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myplan);
        getSupportActionBar().setTitle("My Plan");

    }
    public void goToSocial(View view){
        Intent intent = new Intent(this, Social.class);
        startActivity(intent);
    }

    public void goToSleep(View view){
        Intent intent = new Intent(this, Sleep.class);
        startActivity(intent);
    }
    public void goToWork(View view){
        Intent intent = new Intent(this, StudiesAndWork.class);
        startActivity(intent);
    }
    public void goToExercise(View view){
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);
    }
}

