package com.example.mahtab.countrypedia.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mahtab.countrypedia.R;
import com.example.mahtab.countrypedia.data.di.AppModule;
import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.ui.countrieslist.CountriesFragment;
import com.example.mahtab.countrypedia.util.ListResponse;
import com.example.mahtab.countrypedia.util.base.BaseActivity;
import com.example.mahtab.countrypedia.util.base.BasePresenter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter.start();
        addFragment(android.R.id.content,
                new CountriesFragment(),
                CountriesFragment.FRAGMENT_TAG);

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

     //   CountriesFragment.setResponses(responses);
       // replaceFragment();
        CountriesFragment.newInstance(this).setResponses(responses);

    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
