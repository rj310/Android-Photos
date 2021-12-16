package com.example.photos31android;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAlb extends AppCompatActivity {
    Button submit,cancel;
    EditText newname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        submit = findViewById(R.id.submitbutton);
        cancel = findViewById(R.id.cancelbutton);
        newname = findViewById(R.id.editField);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newname.getText().toString();
                if(name.length()<= 0){
                    Toast.makeText(getApplicationContext(), "No Name Entered!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Album temp = new Album();
                temp.setName(name);
                MainActivity.p.albums.add(temp);
                MainActivity.p.save(getApplicationContext());
                ArrayAdapter<Album> arrayAdapter = new ArrayAdapter<Album>(getApplicationContext(), R.layout.album, MainActivity.p.albums);
                MainActivity.listView.setAdapter(arrayAdapter);
                finish();

            }
        });


    }
}
