package com.example.hussamhassanein.balancelifeandroid.planCategories;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hussamhassanein.balancelifeandroid.MyPlan;
import com.example.hussamhassanein.balancelifeandroid.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Sleep extends AppCompatActivity {
    Spinner sleepHours;
    private FirebaseApp app;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private DatabaseReference databaseRef;
    private DatabaseReference mDatabase;
    private String key="NotInitialize";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleep);
        sleepHours = (Spinner) findViewById(R.id.hours);

        app = FirebaseApp.getInstance();
        database = FirebaseDatabase.getInstance(app);
        auth = FirebaseAuth.getInstance(app);
        storage = FirebaseStorage.getInstance(app);
        databaseRef = database.getReference("category/sleep");

        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String oldkey=dataSnapshot.getKey();
                   key=oldkey;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Context context;

        time();
    }

public void time(){

    List hour = new ArrayList<Integer>();
    for( int i = 1; i <= 24 ; i++) {
        hour.add(i);
    }

    Spinner spinner = (Spinner) findViewById(R.id.hours);
    try {
        Field popup = Spinner.class.getDeclaredField("mPopup");
        popup.setAccessible(true);

        // Get private mPopup member variable and try cast to ListPopupWindow
        android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

        // Set popupWindow height to 500px
        popupWindow.setHeight(500);
        popupWindow.setWidth(550);
    }
    catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
        // silently fail...
    }
  //  Spinner spinner = (Spinner) findViewById(R.id.hours);
// Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
             android.R.layout.simple_spinner_item,hour);
// Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
    spinner.setAdapter(adapter);


    }


    public void goToMyPlan(View view) {



        int h= (int) sleepHours.getSelectedItem();
        database.getReference("Hours").child("1").setValue(h);

     /*  if(key.equals("NotInitialize")){
          databaseRef.push().setValue(h);
        }
        else {

       databaseRef.child(key).setValue(h);
           //databaseRef.child("kewser").child("1").setValue(h);


        }*/

        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);
    }


}
