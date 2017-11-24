package com.example.hussamhassanein.balancelifeandroid.planCategories;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hussamhassanein.balancelifeandroid.MyPlan;
import com.example.hussamhassanein.balancelifeandroid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class Social extends AppCompatActivity {
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social);

    }

    public void printText(View view){

        //  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        // showtext.setText(list.toString());
        // list.add(editText.getText().toString());
        //adapter.notifyDataSetChanged();

        // newText.setText(editText.getText().toString());

        // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //newText.setLayoutParams(params);
        //params.setMargins(10, 250, 0, 0);

        //RelativeLayout chat = (RelativeLayout) findViewById(R.id.socialLayout);
        //chat.addView(listt);

        EditText editText = (EditText) findViewById(R.id.taskSocial);
        TextView  showtext =(TextView) findViewById(R.id.textt);

        list.add(editText.getText().toString());

        //showtext.setText("Today's tasks");


        StringBuilder builder = new StringBuilder();
        for (String details : list) {
            builder.append("\u2022"+details + "\n");
        }

        showtext.setText(builder.toString());


        editText.setText("");

    }

    public void confirmSocial(View view){
        SharedPreferences prefs = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =prefs.edit();
        editor.putString("Social",list.toString());
        editor.commit();
        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);
    }


}
