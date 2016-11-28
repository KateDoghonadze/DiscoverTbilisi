package com.example.qeto.discovertbilisi.api.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QETO on 11/27/2016.
 */

public class TypeModel implements Parcelable {
    public int ID;
    public String Name;
    public String iconUrl;
    public int languageID;

    public TypeModel(int ID, String name, String iconUrl, int languageID) {
        this.ID = ID;
        Name = name;
        this.iconUrl = iconUrl;
        this.languageID = languageID;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    protected TypeModel(Parcel in) {
        ID = in.readInt();
        Name = in.readString();
        iconUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Name);
        dest.writeString(iconUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TypeModel> CREATOR = new Parcelable.Creator<TypeModel>() {
        @Override
        public TypeModel createFromParcel(Parcel in) {
            return new TypeModel(in);
        }

        @Override
        public TypeModel[] newArray(int size) {
            return new TypeModel[size];
        }
    };
}