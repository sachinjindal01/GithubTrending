package com.kutumb.trending.repository;

import com.kutumb.trending.model.RepoModel;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
import com.kutumb.trending.TrendingApi.TrendingApiServices;

import io.reactivex.rxjava3.internal.operators.observable.ObservableBlockingSubscribe;

public class Repository {
    private TrendingApiServices trendingApiService;

    @Inject
    public Repository(TrendingApiServices trendingApiService) {
        this.trendingApiService = trendingApiService;
    }

    public Observable<RepoModel> getTrending(Map<String, String> map) {
        return trendingApiService.getRepos(map);
    }

}
