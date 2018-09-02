package com.example.alexlin.sample.ApiEndpoint;

import com.example.alexlin.sample.model.RedditWrapper;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("/.json")
    Observable<RedditWrapper> getPost();
}
