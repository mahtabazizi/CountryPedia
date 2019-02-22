package com.example.mahtab.countrypedia.ui.main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.mahtab.countrypedia.R;
import com.example.mahtab.countrypedia.data.db.CountriesOflineDataModel;
import com.example.mahtab.countrypedia.data.di.AppModule;
import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.ui.countrieslist.CountriesFragment;
import com.example.mahtab.countrypedia.ui.loadingscreen.LoadingFragment;
import com.example.mahtab.countrypedia.util.ListResponse;
import com.example.mahtab.countrypedia.util.base.BaseActivity;
import com.example.mahtab.countrypedia.util.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements MainContract.View {
    private CountriesFragment countriesFragment;
    private LoadingFragment loadingFragment;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countriesFragment = new CountriesFragment();
        loadingFragment = new LoadingFragment();

        if (checkLocalData()) {
            mainPresenter.start();
            addFragment(R.id.frame_container,
                    loadingFragment,
                    LoadingFragment.LOADING_TAG);

        } else {

            countriesFragment.setOflineResponses(CountriesOflineDataModel.listAll(CountriesOflineDataModel.class));
            replaceFragment(R.id.frame_container,
                    countriesFragment);

        }

    }

    @Override
    public BasePresenter getPresenter() {
        return mainPresenter;
    }

    @Override
    public void injectDependencies() {
        DaggerMainComponent
                .builder()
                .appModule(new AppModule(this))
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showCountryListFragment(ListResponse<CountriesResponse> responses) {
        countriesFragment.setResponses(responses);
        new Handler().postDelayed(() -> replaceFragment(R.id.frame_container,
                countriesFragment), 2000);


        Observable.fromIterable(responses)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CountriesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Local Data is Ready!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CountriesResponse response) {
                        CountriesOflineDataModel dataModel = new CountriesOflineDataModel();
                        dataModel.setCapital(response.getCapital());
                        dataModel.setName(response.getName());
                        dataModel.setPopulation(response.getPopulation());
                        dataModel.save();
                    }
                });


    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }


    public Boolean checkLocalData() {
        try {

            List<CountriesOflineDataModel> securityModel = CountriesOflineDataModel.listAll(CountriesOflineDataModel.class);
            return securityModel == null || securityModel.size() == 0;
        } catch (Exception e) {
            return true;

        }
    }
}
