package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alfredchang on 2017-11-30.
 */

public class Producer implements Parcelable {

    private String name;
    private String profilePath;

    public Producer() {

    }

    protected Producer(Parcel in) {
        name = in.readString();
        profilePath = in.readString();
    }

    public static final Creator<Producer> CREATOR = new Creator<Producer>() {
        @Override
        public Producer createFromParcel(Parcel in) {
            return new Producer(in);
        }

        @Override
        public Producer[] newArray(int size) {
            return new Producer[size];
        }
    };

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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(profilePath);
    }
}
