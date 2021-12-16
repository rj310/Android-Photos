package com.example.photos31android;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.content.Context;
import java.util.ArrayList;
import android.view.*;

public class ImageAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList data;

    public ImageAdapter(Context context, ArrayList data){
        super(context, R.layout.image_layout, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){

        if (currentView == null){
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = (View) i.inflate(R.layout.image_layout, parent, false);
        }

        ImageView imview = (ImageView) currentView.findViewById(R.id.shownimage);
        Photo temp = (Photo) data.get(position);
        imview.setImageBitmap(temp.getImage());
        return currentView;

    }
}