package com.example.alexlin.sample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditList {
    public String after;
    @JsonProperty("children")
    public ArrayList<RedditPostWrapper> postWrappers = new ArrayList<>();
}
