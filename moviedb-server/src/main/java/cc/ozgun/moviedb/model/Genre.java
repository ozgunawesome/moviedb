package cc.ozgun.moviedb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genre {

    @JsonProperty("Action")
    ACTION("Action"),
    @JsonProperty("Cartoon")
    CARTOON("Cartoon"),
    @JsonProperty("Comedy")
    COMEDY("Comedy"),
    @JsonProperty("Crime")
    CRIME("Crime"),
    @JsonProperty("Drama")
    DRAMA("Drama"),
    @JsonProperty("Horror")
    HORROR("Horror"),
    @JsonProperty("Sci-fi")
    SCIFI("Sci-fi");

    private String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return this.displayName;
    }

}
