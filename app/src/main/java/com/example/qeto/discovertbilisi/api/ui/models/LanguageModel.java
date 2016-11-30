package com.example.qeto.discovertbilisi.api.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by QETO on 11/28/2016.
 */

public class LanguageModel implements Parcelable {
    public int ID;
    public String Name;
    public List<TypeModel> Types;

    public LanguageModel(int ID, String name, List<TypeModel> types) {
        this.ID = ID;
        Name = name;
        Types = types;
    }

    public List<TypeModel> getTypes() {
        return Types;
    }

    public void setTypes(List<TypeModel> types) {
        Types = types;
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

    protected LanguageModel(Parcel in) {
        ID = in.readInt();
        Name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LanguageModel> CREATOR = new Parcelable.Creator<LanguageModel>() {
        @Override
        public LanguageModel createFromParcel(Parcel in) {
            return new LanguageModel(in);
        }

        @Override
        public LanguageModel[] newArray(int size) {
            return new LanguageModel[size];
        }
    };
}