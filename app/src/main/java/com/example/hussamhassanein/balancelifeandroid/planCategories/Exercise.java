package com.example.hussamhassanein.balancelifeandroid.planCategories;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hussamhassanein.balancelifeandroid.MainActivity;
import com.example.hussamhassanein.balancelifeandroid.MyPlan;
import com.example.hussamhassanein.balancelifeandroid.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Exercise extends AppCompatActivity {
    private ArrayList<String> socialtask;
    private ArrayAdapter<String > adaptertask;
    private ArrayList<String> socialkey=new ArrayList<>();
    private ListView l;
    private EditText listAdd;
    private Boolean edit = false;
    private int oldposition=1000;;
    private FirebaseApp app;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private DatabaseReference databaseRef;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efficiency);


        // Get the Firebase app and all primitives we'll use
        app = FirebaseApp.getInstance();
        database = FirebaseDatabase.getInstance(app);
        auth = FirebaseAuth.getInstance(app);
        storage = FirebaseStorage.getInstance(app);
        // Get a reference to our chat "room" in the database
        databaseRef = database.getReference("category/exercise");
        mDatabase = FirebaseDatabase.getInstance().getReference("category/exercise");
        l = (ListView) findViewById(R.id.liststask);
        socialtask = new ArrayList<String>();// add it to lists of plan
        //adaptertask = new mycustomAdaptar(this, socialtask);

        adaptertask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, socialtask);

        l.setAdapter(adaptertask);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String task=dataSnapshot.getValue(String.class);
                String key=dataSnapshot.getKey();
                socialkey.add(key);
                socialtask.add(task);
                adaptertask.notifyDataSetChanged();

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
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItems = ((TextView) view).getText().toString();// get the task
                listAdd.setText(selectedItems);// write it on the edittext
                edit = true;
                oldposition = position;

            }
        });


    }

    public void addTasks(View view) {

        listAdd = (EditText) findViewById(R.id.edittask);
        String toDo = listAdd.getText().toString().trim();
        if (edit && !toDo.isEmpty()&& oldposition!=1000) {
            edit = false;
            String oldtask=socialtask.get(oldposition);
            int index=socialtask.indexOf(oldtask);
            String k =socialkey.get(index);
            socialtask.set(oldposition, toDo);
            databaseRef.child(k).setValue(toDo);

            //Log.d("myTag",k);
            //databaseRef.child(k).setValue(toDo);
            listAdd.setText("");
            oldposition=1000;
            adaptertask.notifyDataSetChanged();

        }
        else if (toDo.isEmpty()) {
            return;
        }
        else {
            //adaptertask.add(toDo);
            listAdd.setText("");
            databaseRef.push().setValue(toDo);
        }
    }

    public void saveList(View view) {

        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);

    }

    public void delettask(View view) {
        String toDo = listAdd.getText().toString().trim();
        if (toDo.isEmpty()) {
            return;
        }
        // get the index of key and remove its value from database and adapter
        String oldtask=socialtask.get(oldposition);
        int index=socialtask.indexOf(oldtask);
        String k =socialkey.get(index);
        databaseRef.child(k).removeValue();
        socialkey.remove(index);
        socialtask.remove(oldposition);
        adaptertask.notifyDataSetChanged();
        listAdd.setText("");
        oldposition=1000;
        edit=false;
    }



}