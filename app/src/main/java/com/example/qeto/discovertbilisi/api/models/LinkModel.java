package com.example.qeto.discovertbilisi.api.models;

/**
 * Created by QETO on 11/20/2016.
 */

public class LinkModel {
    public int ID;
    public String Link;
    public int PlaceID;

    public LinkModel(int ID, String link, int placeID) {
        this.ID = ID;
        Link = link;
        PlaceID = placeID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public int getPlaceID() {
        return PlaceID;
    }

    public void setPlaceID(int placeID) {
        PlaceID = placeID;
    }
}
