package com.example.hussamhassanein.balancelifeandroid;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    Spinner sleepHours;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        getSupportActionBar().setTitle("Review");

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
        String[] splitArray = null;
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

        }
        SharedPreferences.Editor editor= share.edit();
        int length=splitArray.length;
        String  str=category+"len";
        editor.putInt(str, length);
        editor.commit();

        splitArray = (String[]) list.toArray(new String[list.size()]);


        if(category.equals("Social")) {
            l = (ListView) findViewById(R.id.checkview);
            l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,splitArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, splitArray);
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
        editor.putInt("SoChLength",checkedelement.size());
        editor.putInt("ExChLength",checkedExercise.size());
        editor.putInt("StChLength",checkedStudies.size());

        editor.putString("SocialChecked", String.valueOf(checkedelement));
        editor.putString("ExerciseChecked", String.valueOf(checkedExercise));
        editor.putString("StudiesChecked", String.valueOf(checkedStudies));

        int r=(int)sleepHours.getSelectedItem();
        editor.putInt("hoursDone",r );

        editor.commit();
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);


    }


}