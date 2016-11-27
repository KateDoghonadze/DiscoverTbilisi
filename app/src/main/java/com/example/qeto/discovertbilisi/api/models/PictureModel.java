package com.example.qeto.discovertbilisi.api.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QETO on 11/22/2016.
 */

public class PictureModel implements Parcelable {
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

    protected PictureModel(Parcel in) {
        ID = in.readInt();
        Url = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Url);
        dest.writeString(title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PictureModel> CREATOR = new Parcelable.Creator<PictureModel>() {
        @Override
        public PictureModel createFromParcel(Parcel in) {
            return new PictureModel(in);
        }

        @Override
        public PictureModel[] newArray(int size) {
            return new PictureModel[size];
        }
    };
}
