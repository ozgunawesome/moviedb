package cc.ozgun.moviedb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

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
    private final Map<String, String> map = new HashMap<>();

    Genre(String displayName) {
        this.displayName = displayName;
        map.put("value", this.name());
        map.put("label", this.displayName());
    }

    public String displayName() {
        return this.displayName;
    }

    public Map<String, String> asTokenfieldDictionary() {
        return map;
    }
}
