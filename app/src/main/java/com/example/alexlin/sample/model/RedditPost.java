package com.example.alexlin.sample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditPost {
    public String title;
    public String thumbnail;
}
