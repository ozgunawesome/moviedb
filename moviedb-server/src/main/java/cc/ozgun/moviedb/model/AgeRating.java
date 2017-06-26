package cc.ozgun.moviedb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AgeRating {

    @JsonProperty("18+")
    EIGHTEEN("18+"),
    @JsonProperty("16+")
    SIXTEEN("16+"),
    @JsonProperty("12+")
    TWELVE("12+"),
    @JsonProperty("6+")
    SIX("6+");

    private String displayName;

    AgeRating(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return this.displayName;
    }
}
