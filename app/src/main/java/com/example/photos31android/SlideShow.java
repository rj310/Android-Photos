package com.example.photos31android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SlideShow extends AppCompatActivity {
    ImageView shownimage;
    ImageButton prevbutton, nextbutton;
    Button gobackbutton;
    int albumindex;
    int photoindex;
    ArrayList<Photo> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        shownimage = findViewById(R.id.shownimage);
        prevbutton = findViewById(R.id.prevbutton);
        nextbutton = findViewById(R.id.nextbutton);
        gobackbutton = findViewById(R.id.gobackbutton);

        Bundle bundle = getIntent().getExtras();
        albumindex = bundle.getInt("AlbumIndex");
        photoindex = bundle.getInt("Photoindex");
        temp = MainActivity.p.albums.get(albumindex).getPhotolist();
        if(photoindex >= temp.size()-1){
            nextbutton.setVisibility(View.INVISIBLE);
        }
        if(photoindex <= 0){
            prevbutton.setVisibility(View.INVISIBLE);
        }
        shownimage.setImageBitmap(temp.get(photoindex).getImage());

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevbutton.setVisibility(View.VISIBLE);
                photoindex+=1;
                shownimage.setImageBitmap(temp.get(photoindex).getImage());
                if(photoindex >= temp.size()-1){
                    nextbutton.setVisibility(View.INVISIBLE);
                }
            }
        });
        prevbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextbutton.setVisibility(View.VISIBLE);
                photoindex-=1;
                shownimage.setImageBitmap(temp.get(photoindex).getImage());
                if(photoindex <= 0){
                    prevbutton.setVisibility(View.INVISIBLE);
                }
            }
        });
        gobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
