package com.example.photos31android;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Photos implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<Album> albums;

    public static Photos load(Context context){
        Photos pa = null;
        try {
            FileInputStream fis = context.openFileInput("photos.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            pa = (Photos) ois.readObject();

            if (pa.albums == null) {
                pa.albums = new ArrayList<Album>();
            }
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return pa;
    }
    public void save(Context context){
        ObjectOutputStream oos;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("photos.dat", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
