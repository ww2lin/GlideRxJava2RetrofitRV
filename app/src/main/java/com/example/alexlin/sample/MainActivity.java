package com.example.alexlin.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.alexlin.sample.ApiEndpoint.RetrofitApi;
import com.example.alexlin.sample.model.RedditPost;
import com.example.alexlin.sample.model.RedditPostWrapper;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.computation;
import static io.reactivex.schedulers.Schedulers.io;

public class MainActivity extends AppCompatActivity {
    String API_BASE_URL = "https://www.reddit.com/";

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Adapter adapter = new Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter.setCallback(this::onClick);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder =
            new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        compositeDisposable.add(retrofit.create(RetrofitApi.class)
                                    .getPost()
                                    .subscribeOn(io())
                                    .observeOn(computation())
                                    .map(redditWrapper -> redditWrapper.redditList.postWrappers)
                                    .flatMapIterable(redditPostWrappers -> redditPostWrappers)
                                    .map(redditPostWrapper -> redditPostWrapper.redditPost)
                                    .toList()
                                    .observeOn(mainThread())
                                    .subscribe(this::onSuccess, this::onFail));
    }

    private void onSuccess(List<RedditPost> redditPosts) {
        adapter.setData(redditPosts);
        adapter.notifyDataSetChanged();
    }

    private void onFail(Throwable throwable) {

    }


    public void onClick(RedditPost redditPost) {
        Toast.makeText(this, redditPost.title, Toast.LENGTH_LONG).show();
    }

    public interface Callback {
        void onClick(RedditPost redditPost);
    }
}
