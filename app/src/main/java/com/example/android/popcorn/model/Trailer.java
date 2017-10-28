package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alfredchang on 2017-09-28.
 */

public class Trailer implements Parcelable {

    private String key;

    private String trailerDescription;

    public Trailer() {

    }

    protected Trailer(Parcel in) {
        key = in.readString();
        trailerDescription = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(trailerDescription);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTrailerDescription() {
        return trailerDescription;
    }

    public void setTrailerDescription(String trailerDescription) {
        this.trailerDescription = trailerDescription;
    }
}
