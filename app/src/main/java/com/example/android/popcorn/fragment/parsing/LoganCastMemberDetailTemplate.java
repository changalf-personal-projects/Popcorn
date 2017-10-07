package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alfredchang on 2017-10-07.
 */

@JsonObject
public class LoganCastMemberDetailTemplate extends MovieLogan {

    @JsonField
    private String birthday;

    @JsonField(name = "deathday")
    private String deathDate;

    @JsonField
    private String name;

    @JsonField
    private String biography;

    @JsonField(name = "place_of_birth")
    private String birthPlace;

    @JsonField(name = "profile_path")
    private String profilePath;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
