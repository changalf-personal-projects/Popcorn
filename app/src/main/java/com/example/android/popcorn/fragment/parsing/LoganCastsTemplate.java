package com.example.android.popcorn.fragment.parsing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by alfredchang on 2017-10-04.
 */

@JsonObject
public class LoganCastsTemplate extends MovieLogan {

    private final String LOG_TAG = LoganCastsTemplate.class.getSimpleName();

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
    }
}

