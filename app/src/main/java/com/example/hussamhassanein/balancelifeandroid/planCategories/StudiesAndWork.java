package com.example.hussamhassanein.balancelifeandroid.planCategories;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hussamhassanein.balancelifeandroid.MyPlan;
import com.example.hussamhassanein.balancelifeandroid.R;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class StudiesAndWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studiesandwork);
        TextView showText =(TextView) findViewById(R.id.textt);
        SharedPreferences share=getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String result1=share.getString("StudiesAndWork",null);
        if (result1!=null) {
            showText.append(result1 + "\n");
        }

    }

    public void printText(View view){

        TextView  showText =(TextView) findViewById(R.id.textt);
        EditText editText = (EditText) findViewById(R.id.taskSocial);

        if(editText.getText().toString().equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(StudiesAndWork.this).create();
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

        editor.putString("StudiesAndWork",showText.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);
    }



}
