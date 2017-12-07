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


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.bumptech.glide.Glide;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private FirebaseApp app;
    private FirebaseDatabase database;
    private FirebaseDatabase database2;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private DatabaseReference databaseRef;
    private DatabaseReference databaseRef2;

    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File f = getDatabasePath("Mydata.xml");

        if (f != null)
            Log.i("TAG", f.getAbsolutePath());

        String g="dd";
        // Get the Firebase app and all primitives we'll use
        app = FirebaseApp.getInstance();
        database = FirebaseDatabase.getInstance(app);
        database2=FirebaseDatabase.getInstance(app);
        auth = FirebaseAuth.getInstance(app);
        storage = FirebaseStorage.getInstance(app);
// Get a reference to our chat "room" in the database


        // Push the chat message to the database


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


   /* public void gotoeffciency(View view) {
        Intent intent = new Intent(this, Efficiency.class);
        startActivity(intent);
    }*/
}
