package com.example.hussamhassanein.balancelifeandroid.planCategories;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hussamhassanein.balancelifeandroid.MainActivity;
import com.example.hussamhassanein.balancelifeandroid.MyPlan;
import com.example.hussamhassanein.balancelifeandroid.R;
import com.example.hussamhassanein.balancelifeandroid.TaskObject;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.PatternSyntaxException;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Social extends AppCompatActivity {
    private ArrayList<String> socialtask;
    private ArrayAdapter<String > adaptertask;
    ListView l;
    TextView t;
    public EditText listAdd;
    Boolean edit = false;
    int oldposition=10000;


    private FirebaseApp app;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private DatabaseReference databaseRef;
    private StorageReference storageRef;
    String userId;
    String name;
    ArrayList<String> names;
    HashSet<String> tasks= new HashSet<>();
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


       mDatabase = FirebaseDatabase.getInstance().getReference("category/social");




// Get a reference to our chat "room" in the database
        databaseRef = database.getReference("category/social");
       // databaseRef.getKey();

       /* databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                     //names= new ArrayList<>();
                    int i = 0;
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        //names.add(d.getValue().toString());
                        tasks.add(d.getValue().toString());
                       //  d.getKey();
                           i++;
                        //name[i] = dataSnapshot.getValue().toString();


                    }
                    TextView t = (TextView) findViewById(R.id.textView32);
                 t.setText(gooo.toString());
                }
            }//onDataChange
                //    name[i] = d.getKey();

                    //  name[i] = dataSnapshot.getValue().toString();
                 //   TextView t = (TextView) findViewById(R.id.textView32);
                  //  t.setText(name[i]);

                    //   mshowdata(dataSnapshot);


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });*/
       // addListenerForSingleValueEvent()
   /* databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
       // ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if (dataSnapshot.exists()) {
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        tasks.add(d.getValue().toString());
                    }
                }
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
             //   Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        //databaseRef.addValueEventListener(postListener);

        /*TextView  showText =(TextView) findViewById(R.id.textt);
        SharedPreferences share=getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String result1=share.getString("Social",null);
        if (result1!=null) {
            showText.append(result1 + "\n");
        }*/



        /*SharedPreferences share = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String result1 = share.getString("Social", null);






        t = (TextView) findViewById(R.id.showresult);//check
        String[] mylist = new String[0];


            if(!result1.equals("")) {
              mylist = result1.split(","); // split the plan tasks to  string[]
            }

            List<String> ls = Arrays.<String>asList(mylist); // change from string[] to Arraylist<String>
        //tasks.add("hello");

*/
        listAdd = (EditText) findViewById(R.id.edittask);
        l = (ListView) findViewById(R.id.liststask);
            socialtask = new ArrayList<String>();// add it to lists of plan
            //adaptertask = new mycustomAdaptar(this, socialtask);


    adaptertask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, socialtask);
        l.setAdapter(adaptertask);
        mDatabase.addChildEventListener(new ChildEventListener() {
       @Override
       public void onChildAdded(DataSnapshot dataSnapshot, String s) {

           String task=dataSnapshot.getValue(String.class);
           socialtask.add(task);
           adaptertask.notifyDataSetChanged();

       }

       @Override
       public void onChildChanged(DataSnapshot dataSnapshot, String s) {
           String task=dataSnapshot.getValue(String.class);
           edit = false;
           socialtask.set(oldposition, task);

           listAdd.setText("");
           oldposition=1000;
           adaptertask.notifyDataSetChanged();

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
          //  l.setAdapter(adaptertask);
    
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


       // listAdd = (EditText) findViewById(R.id.edittask);
        String toDo = listAdd.getText().toString().trim();
        if (edit && !toDo.isEmpty()&& oldposition!=1000) {

            edit = false;
            socialtask.set(oldposition, toDo);

            listAdd.setText("");
            oldposition=1000;
            adaptertask.notifyDataSetChanged();

        } else if (toDo.isEmpty()) {
            return;
        } else {
           // adaptertask.add(toDo);
            listAdd.setText("");




        }
        // Push the chat message to the database
        databaseRef.push().setValue(toDo);
    }

    public void saveList(View view) {


        StringBuilder listtask = new StringBuilder();
        for (String s : socialtask) {
            listtask.append(s);
            listtask.append(",");
        }
        SharedPreferences prefs = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Social", listtask.toString());
        editor.commit();




        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);

    }

    public void delettask(View view) {

        String toDo = listAdd.getText().toString().trim();
        if (toDo.isEmpty()) {
            return;
        }
        socialtask.remove(oldposition);
        adaptertask.notifyDataSetChanged();
        listAdd.setText("");
        oldposition=1000;
        edit=false;
    }



}

// FirebaseUser user =auth.getCurrentUser();
//   userId=user.getUid();
/*

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
         //   mshowdata(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
/*
    private void mshowdata(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            TaskObject Tobject =new TaskObject();
            Tobject.setTask(ds.child(userId).getValue(TaskObject.class).getTask()); //set task
            Tobject.setId(ds.child(userId).getValue(TaskObject.class).getId()); //set task
            //display all the information
           // Log.d("", "showData: id: " + Tobject.getId());
           // Log.d("", "showData: task: " + Tobject.getTask());
        }

    }

*/
/*public void printText(View view){


        TextView  showText =(TextView) findViewById(R.id.textt);
        EditText editText = (EditText) findViewById(R.id.taskSocial);

        if(editText.getText().toString().equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(Social.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Please write a task");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{
            showText.append(editText.getText().toString() + "\n");


        }
        editText.setText("");

    }

    public void confirmSocial(View view){
        SharedPreferences prefs = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =prefs.edit();
        TextView  showText =(TextView) findViewById(R.id.textt);
        String[] splitArray = null;
        try {
            splitArray = showText.getText().toString().split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }

        List list =  new ArrayList<String>();
        Collections.addAll(list, splitArray);
        for(int i = 0 ;i < splitArray.length;i++){
            if(list.get(i).equals("")){
                list.remove( new Integer(i));
            }

        }
        splitArray = (String[]) list.toArray(new String[list.size()]);

        editor.putInt("SocialLength",splitArray.length);
        editor.putString("Social",showText.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);
    }

*/
