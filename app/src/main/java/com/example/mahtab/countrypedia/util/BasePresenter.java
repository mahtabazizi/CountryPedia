package com.example.mahtab.countrypedia.util;

public interface BasePresenter {

    void start();
    void cancelServices();
    public abstract BaseView getView();
}
