package com.example.alexlin.sample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditWrapper {
    public String kind;
    @JsonProperty("data")
    public RedditList redditList;
}
