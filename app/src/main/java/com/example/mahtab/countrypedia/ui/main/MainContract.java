package com.example.mahtab.countrypedia.ui.main;

import com.example.mahtab.countrypedia.util.BaseView;
import com.example.mahtab.countrypedia.util.IPresenter;

public interface MainContract {
   interface Presenter extends IPresenter {
        void getCountries();
    }

    interface View extends BaseView {

    }
}