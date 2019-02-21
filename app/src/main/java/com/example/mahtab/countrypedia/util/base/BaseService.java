package com.example.mahtab.countrypedia.util.base;

import com.example.mahtab.countrypedia.util.ServiceCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class BaseService<T, R> {

    protected final static String PAGE_SIZE_QUERY_KEY = "pageSize";

    protected FetchingState serverFetchingState = FetchingState.IDLE;
    protected FetchingState dbFetchingState = FetchingState.IDLE;
    protected FetchingState dbStoringState = FetchingState.IDLE;

    private Call<R> call;
    private ServiceCallback<R> callback;
    private Gson gson = new Gson();
    //private LocalStorage localStorage;
    private Map<String, String> header;


    public ServiceCallback<R> getCallback() {
        return callback;
    }

    protected void invalidateFetchingState(){
        serverFetchingState = FetchingState.IDLE;
    }

    protected void invalidateDbFetchingState(){
        dbFetchingState = FetchingState.IDLE;
        dbStoringState = FetchingState.IDLE;
    }


    public void call(T request, final ServiceCallback<R> callback) {
        call(request, null, null, callback);
    }

    public void call(T request, Map<String, String> _header, Map<String, String> query, final ServiceCallback<R> callback, String... path) {
        if (serverFetchingState.equals(FetchingState.IDLE)) {
            onResponseServerFetchingStateChanged(FetchingState.LOADING);
           /* addHeaderItems(_header);
            addHeaderItem("agent-type", "Android");
            addHeaderItem("version-name", getAppVersion());
            addHeaderItem("merchant-name", getMerchantName());*/
            call = getRetrofitCall(request, header, query, path);
            enqueueCall(call, callback);
        }
    }

   /* protected void addHeaderItem(String key, String value) {
        if (header == null) {
            header = new HashMap<>();
        }
        header.put(key, value);
    }

    protected void addHeaderItems(Map<String, String> h) {
        if (h == null) {
            return;
        }
        if (header == null) {
            header = new HashMap<>();
        }
        header.putAll(h);
    }*/

    protected void enqueueCall(Call<R> call, final ServiceCallback<R> c) {
        loadLocal();
        this.callback = c;
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, retrofit2.Response<R> response) {
            //    LogManager.d("__RESPONSE__", call.request().url().toString());
                if (response.isSuccessful()) {
                    R serviceResponse = response.body();
                    storeLocal(serviceResponse);
                    if (callback != null) {
                        onResponseServerFetchingStateChanged(FetchingState.LOADED); // should be first line
                        callback.loadSuccess(serviceResponse);
                        //callback.serviceDone();
                    }
                    else
                        onResponseServerFetchingStateChanged(FetchingState.IDLE);
                } else {
                    String errorJson = null;
                    try {
                        errorJson = response.errorBody().string();
                       // ServiceError errorMessage = gson.fromJson(errorJson, ServiceError.class);
                        if (callback != null) {
                          //  callback.errorHappened(errorMessage);
                            //callback.serviceDone();
                        }
                    } catch (Exception e) {
                       /* ServiceError errorMessage = new ServiceError();
                        errorMessage.setCode(0);
                        errorMessage.setMessage("" + e.getMessage());*/
/*
                        if (callback != null) {
                            callback.errorHappened(errorMessage);
                            //callback.serviceDone();
                        }*/
                    }
                    onResponseServerFetchingStateChanged(FetchingState.IDLE);
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                onResponseServerFetchingStateChanged(FetchingState.IDLE);

                if (callback != null) {
                  //  callback.errorHappened(error);
                }
            }
        });
    }


    public void cancel() {
        invalidateFetchingState();
        invalidateDbFetchingState();
        if (call != null) {
            call.cancel();
            callback = null;
        }
    }

    protected abstract Call<R> getRetrofitCall(T request, Map<String, String> header, Map<String, String> query, String... path);


    protected void storeLocal(R response) {

    }

    public void loadLocal() {

    }

/*
    protected LocalStorage getLocalStorage() {
        return localStorage;
    }

    public void setLocalStorage(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }
*/

    public FetchingState getServerFetchingState() {
        return serverFetchingState;
    }

    public FetchingState getDbFetchingState() {
        return dbFetchingState;
    }

    protected final void onResponseServerFetchingStateChanged(FetchingState state){
        this.serverFetchingState = state;
        if(state.equals(FetchingState.LOADED))
            invalidateFetchingState();
    }

    public enum FetchingState {
        IDLE, LOADING, LOADED
    }
}
