package com.example.photos31android;

import java.io.Serializable;
import java.util.ArrayList;


public class Album implements Serializable {

    private String name;
    private ArrayList<Photo> photolist;

    public Album() {
        name = "";
        photolist = new ArrayList<Photo>();
    }

    public Album(String name, ArrayList<Photo> pl) {
        this.name = name;
        photolist = pl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Photo> getPhotolist() {
        return photolist;
    }

    public void setPhotolist(ArrayList<Photo> photolist) {
        this.photolist = photolist;
    }

    public void addPhoto(Photo p) {
        photolist.add(p);
    }

    public void removePhoto(Photo p) {
        photolist.remove(p);
    }

    public String toString(){
        return name;
    }

}
