package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alfredchang on 2017-11-27.
 */

public class Director implements Parcelable {

    private String name;
    private String profilePath;

    public Director() {

    }

    protected Director(Parcel in) {
        name = in.readString();
        profilePath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(profilePath);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Director> CREATOR = new Creator<Director>() {
        @Override
        public Director createFromParcel(Parcel in) {
            return new Director(in);
        }

        @Override
        public Director[] newArray(int size) {
            return new Director[size];
        }
    };
}
