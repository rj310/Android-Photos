package com.example.photos31android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    public static ListView listView;
    Button open,create,edit,search,delete;
    public static Photos p;
    private static int selectedindex;
    public static int getSelectedindex(){
        return selectedindex;
    }
    public static ArrayList<String> getAlbumNames(){
        ArrayList<String> temp = new ArrayList<String>();
        for(Album a: p.albums){
            temp.add(a.toString());
        }
        return temp;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // did this get added to git???
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.albumlistview);
        open = findViewById(R.id.openbutton);
        create = findViewById(R.id.createbutton);
        edit = findViewById(R.id.editbutton);
        search = findViewById(R.id.searchbutton);
        delete = findViewById(R.id.deletebutton);
        p = Photos.load(this);
        if(p == null) {
            p = new Photos();
        }
        if (p.albums == null) {
            p.albums = new ArrayList<Album>();
        }

        ArrayAdapter<Album> arrayAdapter = new ArrayAdapter<Album>(this, R.layout.album, p.albums);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedindex = position;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedindex <0 || selectedindex >= p.albums.size()){
                    Toast.makeText(getApplicationContext(), "No Album Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                p.albums.remove(selectedindex);
                ArrayAdapter<Album> arrayAdapter = new ArrayAdapter<Album>(getApplicationContext(),
                        R.layout.album, p.albums);
                listView.setAdapter(arrayAdapter);
                MainActivity.p.save(getApplicationContext());

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedindex <0 || selectedindex >= p.albums.size()){
                    Toast.makeText(getApplicationContext(), "No Album Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                gotoedit();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAlb.class);
                startActivity(intent);
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedindex <0 || selectedindex >= p.albums.size()){
                    Toast.makeText(getApplicationContext(), "No Album Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),OpenAlb.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Index",selectedindex);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
            }
        });

    }

    private void gotoedit(){
        Intent intent = new Intent(getApplicationContext(), EditAlb.class);
        startActivity(intent);
    }


}