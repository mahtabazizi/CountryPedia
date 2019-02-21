package com.example.mahtab.countrypedia.ui.main;

import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.data.retrofit.CountriesService;
import com.example.mahtab.countrypedia.util.ListResponse;
import com.example.mahtab.countrypedia.util.ServiceCallback;
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

        getCountries();
    }

    @Override
    public void cancelServices() {

        countriesService.cancel();
    }

    @Override
    public void getCountries() {
        countriesService.call(null, new ServiceCallback<ListResponse<CountriesResponse>>(this) {
            @Override
            public void loadSuccess(ListResponse<CountriesResponse> responses) {
                super.loadSuccess(responses);
                view.showCountryListFragment(responses);
            }

            @Override
            public void errorHappened( ) {
                view.showErrorMessage();
            }
        });
    }
}
