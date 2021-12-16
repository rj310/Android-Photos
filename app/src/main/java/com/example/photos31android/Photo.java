package com.example.photos31android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class Photo implements Serializable {

    transient Bitmap image;
    String caption;
    private ArrayList<String> tags;

    public Photo() {
        tags = new ArrayList<String>();
    }

    public String getCaption(){
        return caption;
    }
    public void setCaption(String c){
        caption=c;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }


    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int b;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        while((b = ois.read()) != -1)
            byteStream.write(b);
        byte bitmapBytes[] = byteStream.toByteArray();
        image = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        if(image != null){
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
            byte bitmapBytes[] = byteStream.toByteArray();
            oos.write(bitmapBytes, 0, bitmapBytes.length);
        }
    }

    public void addTag(String tag){
        tags.add(tag);
    }
    public void removeTag(String tag){
        tags.remove(tag);
    }
    public ArrayList<String> getTags(){
        return tags;
    }





}

