package com.example.photos31android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MovePhoto extends AppCompatActivity {
    ListView albumlist;
    Button movebutton,cancelbutton;
    int selectedindex;
    int photoindex, albumindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        albumlist = findViewById(R.id.albumlistview);
        movebutton = findViewById(R.id.movebuttn);
        cancelbutton = findViewById(R.id.cancelerbutton);
//        Bundle bundle = getIntent().getExtras();
        photoindex = Display.photoindex;
        albumindex = Display.albumindex;
        ArrayAdapter<Album> arrayAdapter = new ArrayAdapter<Album>(this, R.layout.album, MainActivity.p.albums);
        albumlist.setAdapter(arrayAdapter);
        albumlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedindex = position;
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        movebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo temp = MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex);
                if(selectedindex <0 || selectedindex >= MainActivity.p.albums.size()){
                    Toast.makeText(getApplicationContext(), "No Album Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.p.albums.get(albumindex).getPhotolist().remove(temp);
                MainActivity.p.albums.get(selectedindex).getPhotolist().add(temp);
                MainActivity.p.save(getApplicationContext());
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });



    }


}
