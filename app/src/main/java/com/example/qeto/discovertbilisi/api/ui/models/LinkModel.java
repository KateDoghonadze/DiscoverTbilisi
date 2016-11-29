package com.example.qeto.discovertbilisi.api.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QETO on 11/20/2016.
 */

public class LinkModel implements Parcelable {
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

    protected LinkModel(Parcel in) {
        ID = in.readInt();
        Link = in.readString();
        PlaceID = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Link);
        dest.writeInt(PlaceID);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LinkModel> CREATOR = new Parcelable.Creator<LinkModel>() {
        @Override
        public LinkModel createFromParcel(Parcel in) {
            return new LinkModel(in);
        }

        @Override
        public LinkModel[] newArray(int size) {
            return new LinkModel[size];
        }
    };
}