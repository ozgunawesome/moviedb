package cc.ozgun.moviedb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genre {

    @JsonProperty("Action")
    ACTION,
    @JsonProperty("Cartoon")
    CARTOON,
    @JsonProperty("Comedy")
    COMEDY,
    @JsonProperty("Crime")
    CRIME,
    @JsonProperty("Drama")
    DRAMA,
    @JsonProperty("Horror")
    HORROR,
    @JsonProperty("Sci-fi")
    SCIFI
}
