package com.example.qeto.discovertbilisi.api.ui.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QETO on 11/20/2016.
 */

public class PlaceModel implements Parcelable {
    public int ID;
    public String Title;
    public double mLatitude = 0.0;
    public double mLongitude = 0.0;
    public PictureModel BasicPicture;
    public String VideoUrl;
    public String Description;
    public List<LinkModel> links;
    public List<PictureModel> Pictures;
    public int TypeID;
    public boolean isSaved;

    public PlaceModel(int ID, String title, double mLatitude, double mLongitude,
                      PictureModel basicPicture, String videoUrl,
                      String description, List<LinkModel> links,
                      List<PictureModel> pictures, int typeID,boolean isSaved) {
        this.ID = ID;
        Title = title;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        BasicPicture = basicPicture;
        VideoUrl = videoUrl;
        Description = description;
        this.links = links;
        Pictures = pictures;
        TypeID = typeID;
        this.isSaved=isSaved;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public PictureModel getBasicPicture() {
        return BasicPicture;
    }

    public void setBasicPicture(PictureModel basicPicture) {
        BasicPicture = basicPicture;
    }

    public List<PictureModel> getPictures() {
        return Pictures;
    }

    public void setPictures(List<PictureModel> pictures) {
        Pictures = pictures;
    }

    protected PlaceModel(Parcel in) {
        ID = in.readInt();
        Title = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
        BasicPicture = (PictureModel) in.readValue(PictureModel.class.getClassLoader());
        VideoUrl = in.readString();
        Description = in.readString();
        if (in.readByte() == 0x01) {
            links = new ArrayList<LinkModel>();
            in.readList(links, LinkModel.class.getClassLoader());
        } else {
            links = null;
        }
        if (in.readByte() == 0x01) {
            Pictures = new ArrayList<PictureModel>();
            in.readList(Pictures, PictureModel.class.getClassLoader());
        } else {
            Pictures = null;
        }
        TypeID = in.readInt();
        isSaved = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Title);
        dest.writeDouble(mLatitude);
        dest.writeDouble(mLongitude);
        dest.writeValue(BasicPicture);
        dest.writeString(VideoUrl);
        dest.writeString(Description);
        if (links == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(links);
        }
        if (Pictures == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(Pictures);
        }
        dest.writeInt(TypeID);
        dest.writeByte((byte) (isSaved ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaceModel> CREATOR = new Parcelable.Creator<PlaceModel>() {
        @Override
        public PlaceModel createFromParcel(Parcel in) {
            return new PlaceModel(in);
        }

        @Override
        public PlaceModel[] newArray(int size) {
            return new PlaceModel[size];
        }
    };
}