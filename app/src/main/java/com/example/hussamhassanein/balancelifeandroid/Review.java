package com.example.hussamhassanein.balancelifeandroid;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static bolts.Task.delay;
import static java.lang.Thread.sleep;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Review extends AppCompatActivity {

   
    //ListView l;
    ArrayList<String> socialtask;
    ArrayList<String> exetask;
    ArrayList<String> worktask;
    ArrayList<String> checkedelement= new ArrayList<>();
    ArrayList<String> checkedExercise= new ArrayList<>();
    ArrayList<String> checkedStudies= new ArrayList<>();
    ArrayList<String> newd= new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapters;
    ArrayAdapter<String> adapterw;
    Spinner sleepHours;
    int check=0;
    private FirebaseApp app;
   private FirebaseDatabase database;
   private FirebaseAuth auth;
   private FirebaseStorage storage;
   private DatabaseReference databaseRef;
   private DatabaseReference mDatabase;
   private DatabaseReference wDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");
       app = FirebaseApp.getInstance();
       database = FirebaseDatabase.getInstance(app);
        auth = FirebaseAuth.getInstance(app);
       storage = FirebaseStorage.getInstance(app);
        // Get a reference to our chat "room" in the database
      /*  databaseRef = database.getReference("category/social");
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        ArrayList<String> getlists= new ArrayList<>();
       // socialtask=new ArrayList<>();
        socialtask = myFirebase(getlists,databaseRef);
       while (check == 0){}
       delay(10);
       ListView list=(ListView) findViewById(R.id.checkview);
       check(socialtask ,list,checkedelement);
       check=0;

        databaseRef = database.getReference("category/exercise");
        getlists= new ArrayList<>();
        exetask=new ArrayList<>();
        exetask = myFirebase(getlists,databaseRef);
        while (check == 0){}
      list=(ListView) findViewById(R.id.exerciseList);
        check(exetask,list,checkedExercise);
        check=0;
        databaseRef = database.getReference("category/work");

        getlists= new ArrayList<>();
        worktask=new ArrayList<>();
        worktask = myFirebase(getlists,databaseRef);
        while (check == 0){}
        list=(ListView) findViewById(R.id.studiesandworkList);
        check(worktask,list,checkedStudies);
        check=0;*/
       // ListUtils.setDynamicHeight( list);


      // mDatabase = database.getReference("category/exercise");
        // Check("Social",databaseRef);
        // Check("category/social", mDatabase);
       // Check("category/exercise");
       // Check("category/work");
        sleepHours = (Spinner) findViewById(R.id.hrSpinner);
        final ListView l = (ListView) findViewById(R.id.checkview);
        l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Context context;
        socialtask= new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,  socialtask);
        l.setAdapter(adapter);

        databaseRef = database.getReference("category/social");

        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                socialtask.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
               setDynamicHeight(l);

            }
          public  boolean wat=true;
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
                String selectedItems = ((TextView) view).getText().toString();

                if (checkedelement.contains(selectedItems)) {
                    checkedelement.remove(selectedItems); //uncheck item
                } else checkedelement.add(selectedItems);

            }
        });

        // ListUtils.setDynamicHeight(l);





      //  ListUtils.setDynamicHeight(l);
        final ListView lexe = (ListView) findViewById(R.id.exerciseList);
        lexe .setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        exetask= new ArrayList<String>();
        adapters = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,  exetask);
        lexe .setAdapter(adapters);
       // delay(1000);
        mDatabase = database.getReference("category/exercise");

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                exetask.add(dataSnapshot.getValue(String.class));
                adapters.notifyDataSetChanged();
                setDynamicHeight(lexe );


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
        lexe.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItems = ((TextView) view).getText().toString();

                if (checkedExercise.contains(selectedItems)) {
                    checkedExercise.remove(selectedItems); //uncheck item
                } else checkedExercise.add(selectedItems);

            }
        });
       // ListUtils.setDynamicHeight(lexe );

        final ListView lwork = (ListView) findViewById(R.id.studiesandworkList);
        lwork.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        worktask= new ArrayList<String>();
        adapterw = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,  worktask);
        lwork.setAdapter(adapterw);
       // delay(1000);
        wDatabase = database.getReference("category/work");

        wDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                worktask.add(dataSnapshot.getValue(String.class));
                adapterw.notifyDataSetChanged();
                setDynamicHeight(lwork);

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
        lwork.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItems = ((TextView) view).getText().toString();

                if (checkedStudies.contains(selectedItems)) {
                    checkedStudies.remove(selectedItems); //uncheck item
                } else checkedStudies.add(selectedItems);

            }
        });
       // ListUtils.setDynamicHeight(lwork);


        //delay(10000);

        setDynamicHeight(l);
       setDynamicHeight(lexe );
       setDynamicHeight(lwork);
        time2Go();
    }







        public  void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            System.out.print("am insde");
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
           // socialtask.add("here");
            adapter.notifyDataSetChanged();
        }


    public void time2Go(){

        List hour1 = new ArrayList<Integer>();
        for( int i = 1; i <= 24 ; i++) {
            hour1.add(i);
        }

        Spinner spinner = (Spinner) findViewById(R.id.hrSpinner);
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
                android.R.layout.simple_spinner_item,hour1);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    public void ShowChecked(View view) {

        //SharedPreferences shared=getSharedPreferences("MY_DATA",Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor= shared.edit();

       int r=(int)sleepHours.getSelectedItem();

        database.getReference("Hours").child("2").setValue(r);

        //editor.putInt("SoChLength",checkedelement.size());
      database.getReference("Length").child("3").setValue(checkedelement.size());
        //editor.putInt("ExChLength",checkedExercise.size());
     database.getReference("Length").child("4").setValue(checkedExercise.size());
        //editor.putInt("StChLength",checkedStudies.size());
      database.getReference("Length").child("5").setValue(checkedStudies.size());

        database.getReference("Length").child("6").setValue(socialtask.size());
        //editor.putInt("ExChLength",checkedExercise.size());
        database.getReference("Length").child("7").setValue(exetask.size());
        //editor.putInt("StChLength",checkedStudies.size());
        database.getReference("Length").child("8").setValue(worktask.size());



        //editor.putInt("hoursDone",r );
     // database.getReference("hoursDone").push().setValue(r);
/*
        // social get the length and update the database
        //String social = shared.getString("Social", null);
       // String[] splitsocial = social.split(",");
        /*List<String> ls = Arrays.<String>asList(splitsocial);
        ArrayList<String> socialtask= new ArrayList<String>(ls);
        socialtask.removeAll(checkedelement);
        String joined = TextUtils.join(", ",socialtask);
        editor.putString("Social",  joined);
        editor.putInt("SocialLength",splitsocial.length);
    database.getReference("SocialLength").push().setValue(splitsocial.length);


        // exercise get the length and update the database
        String exercise = shared.getString("Exercise", null);
        String[] splitexe = exercise.split(",");
        List<String> es = Arrays.<String>asList(splitexe);
        ArrayList<String> exetask= new ArrayList<String>(es);
        exetask.removeAll(checkedExercise);
        String exjoined = TextUtils.join(", ",exetask);
        editor.putString("Exercise",  exjoined);
        editor.putInt("ExLength",splitexe.length);
      database.getReference("ExLength").push().setValue(splitexe.length);

       //studyandwork length and update the database
        String studynwork = shared.getString("StudiesAndWork", null);
        String[] splitsandw = studynwork.split(",");
        List<String> study = Arrays.<String>asList(splitsandw);
        ArrayList<String> studytask= new ArrayList<String>(study);
        studytask.removeAll(checkedStudies);
        String studyjoined = TextUtils.join(", ",studytask);
        editor.putString("StudiesAndWork",  studyjoined);
        editor.putInt("StLength",splitsandw.length);
       database.getReference("StLength").push().setValue(splitsandw.length);
       // commit everything
        editor.commit();
*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }



}

/* public ArrayList<String> myFirebase(final ArrayList<String> listtasks,DatabaseReference data) {
      //  DatabaseReference mDatabase;


        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                listtasks.add(dataSnapshot.getValue(String.class));
                //adapter.notifyDataSetChanged();


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

        check=1;
        return  listtasks;
    }

    public void check(ArrayList<String> tasks, ListView listview, final ArrayList<String> checked) {
        //tasks.add(" ");
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapters = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, tasks);
        listview.setAdapter(adapters);
        ListUtils.setDynamicHeight(listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItems = ((TextView) view).getText().toString();

                if (checked.contains(selectedItems)) {
                    checked.remove(selectedItems); //uncheck item
                } else checked.add(selectedItems);

            }
        });

    }

  /*  public void Check(String category,DatabaseReference generaldatabase){
        socialtask= new ArrayList<String>();
        generaldatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot s: dataSnapshot.getChildren()){
                    socialtask.add(s.getValue(String.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        socialtask.add("hwllo");


        //SharedPreferences share = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        //String plan = share.getString(category, null);

    // ArrayList<String> socialtask;
        //String[] splitArray = plan.split(",");
        //List<String> ls = Arrays.<String>asList(splitArray);
       // socialtask= new ArrayList<String>();


        if(category.equals("Social")) {
           // databaseRef = database.getReference(category);

            l = (ListView) findViewById(R.id.checkview);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            //socialtask.add("Notjava");
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
           // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);

          adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,  socialtask);
            l.setAdapter(adapter);
            ListUtils.setDynamicHeight(l);
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





        if(category.equals("category/exercise")){
            databaseRef = database.getReference(category);
            mDatabase = FirebaseDatabase.getInstance().getReference(category);
            l = (ListView) findViewById(R.id.exerciseList);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            socialtask= new ArrayList<String>();
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
      adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, socialtask);
           // String keys= database.getReference("category/exercise");
            //socialtask.add(keys);
            l.setAdapter(adapter);


            ListUtils.setDynamicHeight(l);
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

        if(category.equals("category/work")){
            l = (ListView) findViewById(R.id.studiesandworkList);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, socialtask);
            l.setAdapter(adapter);
            ListUtils.setDynamicHeight(l);

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

    }*/
/*  String[] splitArray = null;
        try {
            splitArray = plan.split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }

        List list =  new ArrayList<String>();
        Collections.addAll(list, splitArray);
        for(int i = 0 ;i < splitArray.length;i++){
            if(list.get(i).equals("")){
                list.remove( new Integer(i));
            }

        }*/
/*SharedPreferences.Editor editor= share.edit();
        int length=splitArray.length;
        String  str=category+"len";
        editor.putInt(str, length);
        editor.commit();*/

// splitArray = (String[]) list.toArray(new String[list.size()]);



//editor.putString("SocialChecked", String.valueOf(checkedelement));
//editor.putString("ExerciseChecked", String.valueOf(checkedExercise));
//editor.putString("StudiesChecked", String.valueOf(checkedStudies));
      /*  String[] splitArray = null;
        try {
            splitArray = plan.split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }

        List list =  new ArrayList<String>();
        Collections.addAll(list, splitArray);

        List<String> union = new ArrayList<String>(list);
        union.addAll(checkedelement);
        List<String> intersection = new ArrayList<String>(list);
        intersection.retainAll(checkedelement);
        union.removeAll(intersection);
        String iHateThis="";
        for(int i=0; i<union.size();i++){
            iHateThis=iHateThis+union.get(i)+"\n";

        }

        editor.remove("Social");
        editor.putString("Social", iHateThis);
        ArrayList<String> socialtask;
        String[] splitArray = plan.split(",");
        List<String> ls = Arrays.<String>asList(splitArray);

        socialtask= new ArrayList<String>(ls);

        String plan2 = shared.getString("Exercise", null);
        String[] splitArray2 = null;
        try {
            splitArray2 = plan2.split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }

        List list2 =  new ArrayList<String>();
        Collections.addAll(list2, splitArray2);

        List<String> union2 = new ArrayList<String>(list2);
        union2.addAll(checkedExercise);
        List<String> intersection2 = new ArrayList<String>(list2);
        intersection.retainAll(checkedExercise);
        union2.removeAll(intersection2);
        String iStillHateThis="";
        for(int i=0; i<union2.size();i++){
            iStillHateThis=iStillHateThis+union2.get(i)+"\n";

        }

        editor.remove("Exercise");
        editor.putString("Exercise", iStillHateThis);

        String plan3 = shared.getString("StudiesAndWork", null);
        String[] splitArray3 = null;
        try {
            splitArray3 = plan3.split("[\\r\\n]+");
        } catch (PatternSyntaxException ex) {
            //
        }

        List list3 =  new ArrayList<String>();
        Collections.addAll(list3, splitArray3);

        List<String> union3 = new ArrayList<String>(list3);
        union3.addAll(checkedStudies);
        List<String> intersection3 = new ArrayList<String>(list3);
        intersection3.retainAll(checkedStudies);
        union3.removeAll(intersection3);

        String iHateThisSoMuch="";

            for(int i=0; i<union3.size();i++){
                iHateThisSoMuch=iHateThisSoMuch+union3.get(i)+"\n";

            }



        editor.remove("StudiesAndWork");
        editor.putString("StudiesAndWork", iHateThisSoMuch);

        editor.remove("SocialLength");
        editor.remove("ExLength");
        editor.remove("StLength");

        editor.putInt("SocialLength",union.size());
        editor.putInt("ExLength",union2.size());
        editor.putInt("StLength",union3.size());

*/