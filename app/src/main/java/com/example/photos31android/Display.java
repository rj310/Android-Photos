package com.example.photos31android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class Display extends AppCompatActivity {
    ImageView imagefield;
    TextView captionfield;
    ListView taglist;
    RadioButton locationselect,personselect;
    EditText newtagfield;
    public static int photoindex;
    public static int albumindex;
    Button addtag,removetag,backbutton,movebutton,slidebutton;
    String selectedtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        imagefield = findViewById(R.id.shownimage);
        captionfield = findViewById(R.id.captionfield);
        taglist = findViewById(R.id.taglist);
        locationselect = findViewById(R.id.personbutton);
        personselect = findViewById(R.id.locationbutton);
        newtagfield = findViewById(R.id.newtagfield);
        addtag = findViewById(R.id.addtagbutton);
        removetag = findViewById(R.id.removetagbutton);
        backbutton = findViewById(R.id.backbutton);
        movebutton = findViewById(R.id.movebutton);
        slidebutton = findViewById(R.id.slidebutton);

        Bundle bundle = getIntent().getExtras();
        photoindex = bundle.getInt("Index");
        albumindex = bundle.getInt("AlbumIndex");
        Photo temp = MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex);
        imagefield.setImageBitmap(temp.getImage());
        captionfield.setText(MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex).getCaption());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.album, temp.getTags());
        taglist.setAdapter(arrayAdapter);
        taglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedtag = MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex).getTags().get(position);
            }
        });


        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!personselect.isChecked() && !locationselect.isChecked()){
                    Toast.makeText(getApplicationContext(), "No Tag Type Selected!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String value = newtagfield.getText().toString();
                if(value.length()<=0){
                    Toast.makeText(getApplicationContext(), "No Tag Value Entered!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String tagtoadd="";
                if(personselect.isChecked()){
                    tagtoadd = "Location:"+value;
                }
                if(locationselect.isChecked()){
                    tagtoadd = "Person:"+value;
                }
                MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex).addTag(tagtoadd);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.album, MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex).getTags());
                taglist.setAdapter(arrayAdapter);
                newtagfield.setText(null);
                MainActivity.p.save(getApplicationContext());
            }
        });
        removetag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex).removeTag(selectedtag);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.album, MainActivity.p.albums.get(albumindex).getPhotolist().get(photoindex).getTags());
                taglist.setAdapter(arrayAdapter);
                MainActivity.p.save(getApplicationContext());
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.p.save(getApplicationContext());
                finish();
            }
        });

        movebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MovePhoto.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("AlbumIndex",albumindex);
//                bundle.putInt("Photoindex",photoindex);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        slidebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SlideShow.class);
                Bundle bundle = new Bundle();
                bundle.putInt("AlbumIndex",albumindex);
                bundle.putInt("Photoindex",photoindex);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }
}
