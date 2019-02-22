package com.example.mahtab.countrypedia.data.retrofit;

import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.util.base.BaseService;
import com.example.mahtab.countrypedia.util.ListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CountriesService extends BaseService<Void, ListResponse<CountriesResponse>> {

    private Retrofit retrofit;

    public CountriesService(Retrofit retrofit) {
        this.retrofit = retrofit;
       // setLocalStorage(localStorage);
    }

    @Override
    protected Call<ListResponse<CountriesResponse>> getRetrofitCall(Void request, Map<String, String> header, Map<String, String> query, String... path) {
        return retrofit.create(EndPoint.class).getCountries();
    }
}