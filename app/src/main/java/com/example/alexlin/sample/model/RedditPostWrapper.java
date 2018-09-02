package com.example.alexlin.sample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditPostWrapper {
    public String kind;
    @JsonProperty("data")
    public RedditPost redditPost;
}
