package com.example.mahtab.countrypedia.data.retrofit.authenticator;

import com.example.mahtab.countrypedia.BuildConfig;
import com.example.mahtab.countrypedia.util.LocalStorage;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAuthenticator implements Authenticator {

    @Inject
    LocalStorage localStorage;

    Retrofit retrofit;

    public TokenAuthenticator(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        return null;
    }

   /* @Override
    public Request authenticate(Route route, Response response) throws IOException {
      //  String refreshToken = localStorage.loadToken().getRefreshToken();


            return response.request().newBuilder()
                    .header("Authorization", "Bearer " + token.getAccessToken())
                    .build();
        }*/


}
