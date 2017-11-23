package com.example.hussamhassanein.balancelifeandroid.planCategories;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hussamhassanein.balancelifeandroid.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Sleep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleep);
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



}
