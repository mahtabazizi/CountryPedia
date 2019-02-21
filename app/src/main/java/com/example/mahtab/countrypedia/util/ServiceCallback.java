package com.example.mahtab.countrypedia.util;

public abstract class ServiceCallback<T> {

    private IPresenter presenter;

    public ServiceCallback(IPresenter presenter) {
        this.presenter = presenter;
    }

    public void loadSuccess(T entity) {
    }

  /*  public void errorHappened(ServiceError error) {
        if(presenter != null) {
            presenter.onServiceError(error);
        }
    }*/

    public void localDataLoaded(T entity){

    }

    public void storeDataDone(){

    }

}
