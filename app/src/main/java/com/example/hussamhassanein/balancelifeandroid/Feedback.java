package com.example.hussamhassanein.balancelifeandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hussamhassanein.balancelifeandroid.planCategories.Social;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Feedback extends AppCompatActivity {
    double hoursExpected;
   double hoursDone;
    double total1;
    double total2;
    double total3;
    private FirebaseApp app;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private DatabaseReference refDatabase;
    private DatabaseReference refsDatabase;
    int[]  percent;
    ListView l;
    String [] catogery;
    double dee;
    double dw;
    double dwe;
    double dr;
    double dre;
    double de;
    ArrayList<Double> length;
    myAdaptar adapter;
    int count;
    int counth;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        getSupportActionBar().setTitle("Feedback");
     context=this;
        app = FirebaseApp.getInstance();
        database = FirebaseDatabase.getInstance(app);
        auth = FirebaseAuth.getInstance(app);
        storage = FirebaseStorage.getInstance(app);

        l= (ListView) findViewById(R.id.feedbackshow);
      /*  final double[] de = {0};
        final double[] dee = new double[1];
        final double[] dw=new double[1];
        final double[] dwe=new double[1];
        final double[] dr=new double[1];
        final double[] dre=new double[1];*/
        //checked
       length=new ArrayList<>();
      /*  total1 = Math.round((dee / de * 100) * 10) / 10;
        total2 = Math.round((dwe/ dw * 100) * 10) / 10;
        total3 = Math.round((dre/ dr * 100) * 10) / 10;*/
        //total1 = Math.round((dee / de * 100) * 10) / 10;
        //total2 = Math.round((dwe[0]/ dw[0] * 100) * 10) / 10;
        //total3 = Math.round((dre[0]/ dr[0] * 100) * 10) / 10;


   catogery= new String[]{"Social", "Exercise", "StudiesAndWork"};
     percent=new int[3];
       adapter=new myAdaptar(this,catogery,percent);
        l.setAdapter(adapter);
        count=0;
        SharedPreferences sharef = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        refDatabase = database.getReference("Length");
        refDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                count++;
               // length.add((Double) dataSnapshot.getValue());



                if(dataSnapshot.getKey().equals("3"))
                  dee =dataSnapshot.getValue(Double.class);
               else if(dataSnapshot.getKey().equals("4"))
                    dwe =  dataSnapshot.getValue(Double.class);
                else if(dataSnapshot.getKey().equals("5"))
                    dre =  dataSnapshot.getValue(Double.class);
               else if(dataSnapshot.getKey().equals("6"))
                    de = dataSnapshot.getValue(Double.class);
               else if(dataSnapshot.getKey().equals("7"))
                    dw =  dataSnapshot.getValue(Double.class);
               else  if(dataSnapshot.getKey().equals("8"))
                    dr = dataSnapshot.getValue(Double.class);


                if (count==6){

                    total1 = Math.round((dee / de * 100) * 10) / 10;
                    total2 = Math.round((dwe/ dw * 100) * 10) / 10;
                    total3 = Math.round((dre/ dr * 100) * 10) / 10;
                    percent = new int[]{(int) total1, (int) total2, (int) total3};
                    adapter=new myAdaptar(context,catogery,percent);
                    l.setAdapter(adapter);

                }





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



   counth=0;
        refsDatabase = database.getReference("Hours");
        refsDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                count++;
                // length.add((Double) dataSnapshot.getValue());


                if(dataSnapshot.getKey().equals("1")) {
                    hoursExpected = dataSnapshot.getValue(Double.class);
                } else if(dataSnapshot.getKey().equals("2"))
                    hoursDone =  dataSnapshot.getValue(Double.class);



                if (count==2){
                    TextView t= (TextView)findViewById(R.id.textView5);

                    if(hoursDone<hoursExpected){
                        t.setText("Stick to your schedule or plan realistically");

                    }
                    else if(hoursDone>10.0){
                        t.setText("Try to sleep a little bit less");

                    }
                    else if(hoursDone<4.0){
                        t.setText("Try to sleep a little bit more");
                    }

                }





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

       /*
            double de=length.get(3);
            double dee=length.get(0);
            double dw=length.get(4);
            double dwe=length.get(1);
            double dr=length.get(5);
            double dre=length.get(2);
            total1 = Math.round((dee / de * 100) * 10) / 10;
            total2 = Math.round((dwe/ dw * 100) * 10) / 10;
            total3 = Math.round((dre/ dr * 100) * 10) / 10;*/

        //double de=(double) sharef.getInt("SocialLength",0);
      //String de =(double)database.getReference("Length").child("6").
       // double de =length.get(3);
        //double dee=length.get(0);
       /* double dee=(double)sharef.getInt("SoChLength",0);

        double dw=(double) sharef.getInt("ExLength",0);

        double dwe=(double)sharef.getInt("ExChLength",0);
        double dr=(double) sharef.getInt("StLength",0);
        double dre=(double)sharef.getInt("StChLength",0);*/

        int hoursExpected= sharef.getInt("hours",0);
        int hoursDone= sharef.getInt("hoursDone",0);
        total1 = Math.round((dee / de * 100) * 10) / 10;
        total2 = Math.round((dwe/ dw * 100) * 10) / 10;
        total3 = Math.round((dre/ dr * 100) * 10) / 10;
        //total1 = Math.round((dee / de * 100) * 10) / 10;
        //total2 = Math.round((dwe[0]/ dw[0] * 100) * 10) / 10;
        //total3 = Math.round((dre[0]/ dr[0] * 100) * 10) / 10;


        /*String [] catogery={"Social","Exercise","StudiesAndWork"};
        int[]  percent ={(int)total1, (int)total2, (int)total3};
       myAdaptar adapter=new myAdaptar(this,catogery,percent);
        l.setAdapter(adapter);*/

       TextView t= (TextView)findViewById(R.id.textView5);

  t.setText(String.valueOf(de));
       if(hoursDone<hoursExpected){
            t.setText("Stick to your schedule or plan realistically");

        }
        if(hoursDone>10){
            t.setText("Try to sleep a little bit less");

        }
        if(hoursDone<4){
            t.setText("Try to sleep a little bit more");
        }

    }


}

class myAdaptar  extends ArrayAdapter<String>
{   Context context;
     int[]   percent;
     String[] catogery;
     myAdaptar(Context c, String[] cate , int[] per)
     {
         super(c ,R.layout.barlayout,R.id.bartext,cate);
         this.context=c;
         this.percent=per;
         this.catogery=cate;
     }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflator.inflate(R.layout.barlayout,parent,false);

        ProgressBar progressBar= (ProgressBar) row.findViewById(R.id.progressBar);
        TextView textbar= (TextView) row.findViewById(R.id.bartext);

        progressBar.setProgress(percent[position]);
        textbar.setText(catogery[position]);
        return row;
    }
}