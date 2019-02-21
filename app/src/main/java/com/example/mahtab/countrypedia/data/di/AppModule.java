package com.example.mahtab.countrypedia.data.di;

import android.content.Context;

import com.example.mahtab.countrypedia.BuildConfig;
import com.example.mahtab.countrypedia.data.retrofit.CountriesService;
import com.example.mahtab.countrypedia.data.retrofit.authenticator.TokenAuthenticator;
import com.example.mahtab.countrypedia.util.LocalStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }
    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public Cache provideHttpCache() {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkhttpClient(Cache cache, TokenAuthenticator authenticator) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        builder.dispatcher(dispatcher);
        builder.cache(cache);
        builder.authenticator(authenticator);
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(25, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build();
    }
    @Provides
    @Singleton
    public CountriesService provideCountriesService(Retrofit retrofit, LocalStorage localStorage) {
        return new CountriesService(retrofit, localStorage);
    }
}
