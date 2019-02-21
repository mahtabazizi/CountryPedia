package com.example.mahtab.countrypedia.ui.main;

import com.example.mahtab.countrypedia.data.retrofit.CountriesService;
import com.example.mahtab.countrypedia.util.base.BasePresenter;
import com.example.mahtab.countrypedia.util.base.BaseView;

import javax.inject.Inject;

public class MainPresenter implements BasePresenter , MainContract.Presenter {


    MainContract.View view;

    @Inject
    CountriesService countriesService;

    @Inject
    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public BaseView getView() {
        return view;
    }



    @Override
    public void start() {

    }

    @Override
    public void cancelServices() {

    }

    @Override
    public void getCountries() {

    }
}
