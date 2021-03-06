package com.kutumb.trending.di;

import com.kutumb.trending.TrendingApi.TrendingApiServices;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class Network {

    @Provides
    @Singleton
    public static TrendingApiServices providePokemonApiService() {
        return new Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(TrendingApiServices.class);
    }
}
