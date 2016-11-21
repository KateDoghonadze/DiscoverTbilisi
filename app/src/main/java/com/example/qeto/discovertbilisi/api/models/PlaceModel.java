package com.example.qeto.discovertbilisi.api.models;

import android.location.Location;

import java.util.List;

/**
 * Created by QETO on 11/20/2016.
 */

public class PlaceModel {
    public int ID;
    public String Title;
    public double mLatitude = 0.0;
    public double mLongitude = 0.0;
    public PictureModel BasicPicture;
    public String VideoUrl;
    public String Description;
    public List<LinkModel> links;
    public List<PictureModel> Pictures;

    public PlaceModel(int ID, String title, double mLatitude, double mLongitude, PictureModel basicPicture, String videoUrl, String description, List<LinkModel> links, List<PictureModel> pictures) {
        this.ID = ID;
        Title = title;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        BasicPicture = basicPicture;
        VideoUrl = videoUrl;
        Description = description;
        this.links = links;
        Pictures = pictures;
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
}
