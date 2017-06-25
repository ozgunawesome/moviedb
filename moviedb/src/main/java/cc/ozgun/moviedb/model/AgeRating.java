package cc.ozgun.moviedb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AgeRating {

    @JsonProperty("18+")
    EIGHTEEN,
    @JsonProperty("16+")
    SIXTEEN,
    @JsonProperty("12+")
    TWELVE,
    @JsonProperty("6+")
    SIX

}
