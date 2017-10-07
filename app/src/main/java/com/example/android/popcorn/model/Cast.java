package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alfredchang on 2017-10-02.
 */

public class Cast implements Parcelable {

    private String name;
    private String profilePath;
    private String character;
    private String id;

    public Cast() {

    }

    protected Cast(Parcel in) {
        name = in.readString();
        profilePath = in.readString();
        character = in.readString();
        id = in.readString();
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(profilePath);
        parcel.writeString(character);
        parcel.writeString(id);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }
}
