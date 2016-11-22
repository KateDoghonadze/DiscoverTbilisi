package com.example.qeto.discovertbilisi.api.models;

/**
 * Created by QETO on 11/22/2016.
 */

public class PictureModel {
    public int ID;
    public String Url;
    public String title;

    public PictureModel(int ID,String url, String title) {
        Url = url;
        this.title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
