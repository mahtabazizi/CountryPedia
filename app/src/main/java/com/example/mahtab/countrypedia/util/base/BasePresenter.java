package com.example.mahtab.countrypedia.util.base;

public interface BasePresenter {

    void start();
    void cancelServices();
    public abstract BaseView getView();
}
