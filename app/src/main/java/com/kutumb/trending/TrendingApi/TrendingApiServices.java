package com.kutumb.trending.TrendingApi;

import com.kutumb.trending.model.RepoModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import java.util.Map;

public interface TrendingApiServices {
    @GET("/search/repositories")
    Observable<RepoModel> getRepos(@QueryMap Map<String, String> map);
}
