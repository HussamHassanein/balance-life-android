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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Review extends AppCompatActivity {

   
    ListView l;
    ArrayList<String> checkedelement= new ArrayList<>();
    ArrayList<String> checkedExercise= new ArrayList<>();
    ArrayList<String> checkedStudies= new ArrayList<>();

    ArrayList<String> newd= new ArrayList<>();

    Spinner sleepHours;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");

      //  Check("Social");
        Check("Social");
        Check("Exercise");
        Check("StudiesAndWork");
        sleepHours = (Spinner) findViewById(R.id.hrSpinner);
        Context context;

        time2Go();
    }

    public void Check(String category){

        SharedPreferences share = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String plan = share.getString(category, null);

     ArrayList<String> socialtask;
        String[] splitArray = plan.split(",");
        List<String> ls = Arrays.<String>asList(splitArray);
        socialtask= new ArrayList<String>(ls);


        if(category.equals("Social")) {
            l = (ListView) findViewById(R.id.checkview);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
           // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,  socialtask);
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
        if(category.equals("Exercise")){
            l = (ListView) findViewById(R.id.exerciseList);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, socialtask);
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
        if(category.equals("StudiesAndWork")){
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

    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
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
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
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

        SharedPreferences shared=getSharedPreferences("MY_DATA",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= shared.edit();

        int r=(int)sleepHours.getSelectedItem();

        editor.putInt("SoChLength",checkedelement.size());
        editor.putInt("ExChLength",checkedExercise.size());
        editor.putInt("StChLength",checkedStudies.size());

        editor.putInt("hoursDone",r );


        // social get the length and update the database
        String social = shared.getString("Social", null);
        String[] splitsocial = social.split(",");
        List<String> ls = Arrays.<String>asList(splitsocial);
        ArrayList<String> socialtask= new ArrayList<String>(ls);
        socialtask.removeAll(checkedelement);
        String joined = TextUtils.join(", ",socialtask);
        editor.putString("Social",  joined);
        editor.putInt("SocialLength",splitsocial.length);


        // exercise get the length and update the database
        String exercise = shared.getString("Exercise", null);
        String[] splitexe = exercise.split(",");
        List<String> es = Arrays.<String>asList(splitexe);
        ArrayList<String> exetask= new ArrayList<String>(es);
        exetask.removeAll(checkedExercise);
        String exjoined = TextUtils.join(", ",exetask);
        editor.putString("Exercise",  exjoined);
        editor.putInt("ExLength",splitexe.length);


       //studyandwork length and update the database
        String studynwork = shared.getString("StudiesAndWork", null);
        String[] splitsandw = studynwork.split(",");
        List<String> study = Arrays.<String>asList(splitsandw);
        ArrayList<String> studytask= new ArrayList<String>(study);
        studytask.removeAll(checkedStudies);
        String studyjoined = TextUtils.join(", ",studytask);
        editor.putString("StudiesAndWork",  studyjoined);
        editor.putInt("StLength",splitsandw.length);
       // commit everything
        editor.commit();

        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);


    }



}
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