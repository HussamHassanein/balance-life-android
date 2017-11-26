package com.example.hussamhassanein.balancelifeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File f = getDatabasePath("Mydata.xml");

        if (f != null)
            Log.i("TAG", f.getAbsolutePath());
    }


    public void goToMyPlan(View view){
        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);
    }

    public void goToReview(View view){
        Intent intent = new Intent(this, Review.class);
        startActivity(intent);
    }
    public void goToFeedback(View view){
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);
    }

}
