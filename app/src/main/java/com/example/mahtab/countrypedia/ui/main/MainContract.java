package com.example.mahtab.countrypedia.ui.main;

import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.util.ListResponse;
import com.example.mahtab.countrypedia.util.base.BaseView;
import com.example.mahtab.countrypedia.util.IPresenter;

public interface MainContract {
   interface Presenter extends IPresenter {
        void getCountries();
    }

    interface View extends BaseView {

        void showCountryListFragment(ListResponse<CountriesResponse> responses);

        void showErrorMessage();
    }
}