package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by alfredchang on 2017-10-04.
 */

@JsonObject
public class LoganCastTemplate extends MovieLogan {

    private final String LOG_TAG = LoganCastTemplate.class.getSimpleName();

    @JsonField
    private Credits credits;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    @JsonObject
    public static class Credits {

        @JsonField
        private List<Cast> cast;

        public List<Cast> getCast() {
            return cast;
        }

        public void setCast(List<Cast> cast) {
            this.cast = cast;
        }

        @JsonObject
        public static class Cast {

            @JsonField
            private String name;

            @JsonField(name = "profile_path")
            private String profilePath;

            @JsonField
            private String character;

            @JsonField
            private String id;

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
        }

        @JsonField
        private List<Crew> crew;

        @JsonObject
        public static class Crew {

            @JsonField
            private String job;

            @JsonField
            private String name;

            @JsonField(name = "profile_path")
            private String profilePath;

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
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
        }

        public List<Crew> getCrew() {
            return crew;
        }

        public void setCrew(List<Crew> crew) {
            this.crew = crew;
        }
    }
}

