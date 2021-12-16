package com.example.photos31android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;


public class Search extends AppCompatActivity {
    EditText firstkey,firstvalue,secondkey,secondvalue;
    RadioButton andbutton,orbutton;
    Button submitbutton,backbutton;
    ArrayList<Photo> searchresults = new ArrayList<Photo>();
    ImageAdapter adapter;
    GridView resultlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        firstkey = findViewById(R.id.firstkey);
        firstvalue = findViewById(R.id.firstvalue);
        secondkey = findViewById(R.id.secondkey);
        secondvalue = findViewById(R.id.secondvalue);
        andbutton = findViewById(R.id.andbutton);
        orbutton = findViewById(R.id.orbutton);
        submitbutton = findViewById(R.id.subbutton);
        backbutton = findViewById(R.id.bacbutton);
        resultlist = findViewById(R.id.resultlist);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchresults.clear();
                if(secondvalue.getText().toString().equals("")){
                    String tag = firstkey.getText().toString() + ":" + firstvalue.getText().toString();
                    for (Album a : MainActivity.p.albums) {
                        for (Photo p : a.getPhotolist()) {
                            for (String s : p.getTags()) {
                                String[] splitreturn = s.split(":");
                                String[] splittag = tag.split(":");
                                if (splitreturn[0].toLowerCase().equals(splittag[0].toLowerCase())) {
                                    if(splitreturn[1].toLowerCase().contains(splittag[1].toLowerCase())){
                                        searchresults.add(p);
                                    }
                                }
                            }
                        }
                    }
                    adapter = new ImageAdapter(getApplicationContext(),searchresults);
                    resultlist.setAdapter(adapter);
                    firstvalue.setText(null);
                    firstkey.setText(null);
                }
                else{
                    Boolean tag1found;
                    Boolean tag2found;
                    String tag1 = firstkey.getText().toString() + ":" + firstvalue.getText().toString();
                    String[] tag1split = tag1.split(":");
                    String tag2 = secondkey.getText().toString() + ":" + secondvalue.getText().toString();
                    String[] tag2split = tag2.split(":");
                    for (Album a : MainActivity.p.albums) {
                        for (Photo p : a.getPhotolist()) {
                           tag1found=false;
                           tag2found=false;
                           for(String s: p.getTags()){
                               String[] stringsplit = s.split(":");
                               if (stringsplit[0].toLowerCase().equals(tag1split[0].toLowerCase())) {
                                   if(stringsplit[1].toLowerCase().contains(tag1split[1].toLowerCase())){
                                       tag1found = true;
                                   }
                               }
                               if (stringsplit[0].toLowerCase().equals(tag2split[0].toLowerCase())) {
                                   if(stringsplit[1].toLowerCase().contains(tag2split[1].toLowerCase())){
                                       tag2found = true;
                                   }
                               }
                           }
                           if(andbutton.isChecked()){
                               if(tag1found && tag2found){
                                   searchresults.add(p);
                               }
                           }
                            if(orbutton.isChecked()){
                                if(tag1found || tag2found){
                                    searchresults.add(p);
                                }
                            }
                        }
                    }
                    adapter = new ImageAdapter(getApplicationContext(),searchresults);
                    resultlist.setAdapter(adapter);
                    firstvalue.setText(null);
                    firstkey.setText(null);
                    secondvalue.setText(null);
                    secondkey.setText(null);


                }

            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
