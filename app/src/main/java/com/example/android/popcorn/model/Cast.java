package com.example.android.popcorn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alfredchang on 2017-10-02.
 */

public class Cast implements Parcelable {

    private String name;
    private String thumbnail;
    private String profilePath;
    private String character;
    private String id;
    private String birthday;
    private String deathday;
    private String biography;
    private String birthplace;

    public Cast() {

    }

    protected Cast(Parcel in) {
        name = in.readString();
        thumbnail = in.readString();
        profilePath = in.readString();
        character = in.readString();
        id = in.readString();
        birthday = in.readString();
        deathday = in.readString();
        biography = in.readString();
        birthplace = in.readString();
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(thumbnail);
        parcel.writeString(profilePath);
        parcel.writeString(character);
        parcel.writeString(id);
        parcel.writeString(birthday);
        parcel.writeString(deathday);
        parcel.writeString(biography);
        parcel.writeString(birthplace);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }
}
