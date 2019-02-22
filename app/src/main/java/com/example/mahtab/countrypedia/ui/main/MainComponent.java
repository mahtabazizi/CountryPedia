package com.example.mahtab.countrypedia.ui.main;


import com.example.mahtab.countrypedia.data.di.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MainPresenterModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}