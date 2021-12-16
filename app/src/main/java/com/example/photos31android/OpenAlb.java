package com.example.photos31android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OpenAlb extends AppCompatActivity {
    GridView albumgrid;
    Button addbutton, removebutton, displaybutton;
    private ImageAdapter adapter;
    private int albumindex;
    int selectedphoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        albumgrid = findViewById(R.id.albumView);
        addbutton = findViewById(R.id.addbutton);
        removebutton = findViewById(R.id.removebutton);
        displaybutton = findViewById(R.id.displaybutton);
        Bundle bundle = getIntent().getExtras();
        albumindex = bundle.getInt("Index");
        adapter = new ImageAdapter(this,MainActivity.p.albums.get(albumindex).getPhotolist());
        albumgrid.setAdapter(adapter);
        albumgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedphoto = position;
            }
        });



        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }

        });

        removebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedphoto <0 || selectedphoto >= MainActivity.p.albums.get(albumindex).getPhotolist().size()){
                    Toast.makeText(getApplicationContext(), "No Photo Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                delete(selectedphoto);
                MainActivity.p.save(getApplicationContext());
            }

        });
        displaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedphoto <0 || selectedphoto >= MainActivity.p.albums.get(albumindex).getPhotolist().size()){
                    Toast.makeText(getApplicationContext(), "No Photo Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),Display.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Index",selectedphoto);
                bundle.putInt("AlbumIndex",albumindex);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(requestCode==1){
            Uri newimage = imageReturnedIntent.getData();
            ImageView iv = new ImageView(this);
            iv.setImageURI(newimage);
            BitmapDrawable drawablebitmap = (BitmapDrawable) iv.getDrawable();
            Bitmap selectedbitmap = drawablebitmap.getBitmap();

            Photo addedphoto = new Photo();
            addedphoto.setImage(selectedbitmap);
            MainActivity.p.albums.get(albumindex).addPhoto(addedphoto);
            MainActivity.p.save(getApplicationContext());
            adapter = new ImageAdapter(this,MainActivity.p.albums.get(albumindex).getPhotolist());
            albumgrid.setAdapter(adapter);

        }

    }

    private void delete(int position){
        MainActivity.p.albums.get(albumindex).getPhotolist().remove(position);
        adapter = new ImageAdapter(getApplicationContext(),MainActivity.p.albums.get(albumindex).getPhotolist());
        albumgrid.setAdapter(adapter);

    }
}
