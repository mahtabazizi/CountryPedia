package com.example.mahtab.countrypedia.data.retrofit;

import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.util.ListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoint {

    @GET("all")
    Call<ListResponse<CountriesResponse>> getCountries();
}
