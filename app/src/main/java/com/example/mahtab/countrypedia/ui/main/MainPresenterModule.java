package com.example.mahtab.countrypedia.ui.main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    private MainContract.View view;

    public MainPresenterModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    MainContract.View provideMainContractView() {
        return view;
    }
}