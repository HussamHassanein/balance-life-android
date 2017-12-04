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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * Created by HussamHassanein on 2017-11-23.
 */

public class StudiesAndWork extends AppCompatActivity {
    private ArrayList<String> socialtask;
    private ArrayAdapter<String > adaptertask;
    ListView l;
    TextView t;
    public EditText listAdd;
    public Boolean edit = false;
    public   int oldposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efficiency);

        SharedPreferences share = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String result1 = share.getString("StudiesAndWork", null);
        l = (ListView) findViewById(R.id.liststask);
        t = (TextView) findViewById(R.id.showresult);//check

            listAdd = (EditText) findViewById(R.id.edittask);

            String[] mylist = new String[0];
            if(!result1.equals("")) {
              mylist = result1.split(","); // split the plan tasks to  string[]
            }

            List<String> ls = Arrays.<String>asList(mylist); // change from string[] to Arraylist<String>
            socialtask = new ArrayList<String>(ls);// add it to lists of plan
            //adaptertask = new mycustomAdaptar(this, socialtask);

            adaptertask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, socialtask);

            l.setAdapter(adaptertask);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItems = ((TextView) view).getText().toString();// get the task
                listAdd.setText(selectedItems);// write it on the edittext
                edit = true;
                oldposition = position;

            }
        });








       /* TextView showText =(TextView) findViewById(R.id.textt);
        SharedPreferences share=getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        String result1=share.getString("StudiesAndWork",null);
        if (result1!=null) {
            showText.append(result1 + "\n");
        }*/

    }
    public void addTasks(View view) {


        listAdd = (EditText) findViewById(R.id.edittask);
        String toDo = listAdd.getText().toString().trim();
        if (edit && !toDo.isEmpty()&& oldposition!=1000) {
            edit = false;
            socialtask.set(oldposition, toDo);
            adaptertask.notifyDataSetChanged();
            listAdd.setText("");

        } else if (toDo.isEmpty()) {
            return;
        } else {
            adaptertask.add(toDo);
            listAdd.setText("");
        }
    }

    public void saveList(View view) {


        StringBuilder listtask = new StringBuilder();
        for (String s : socialtask) {
            listtask.append(s);
            listtask.append(",");
        }
        SharedPreferences prefs = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("StudiesAndWork", listtask.toString());
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










}/* public void printText(View view){

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

        editor.putInt("StLength",splitArray.length);
        editor.putString("StudiesAndWork",showText.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, MyPlan.class);
        startActivity(intent);
    }

*/
