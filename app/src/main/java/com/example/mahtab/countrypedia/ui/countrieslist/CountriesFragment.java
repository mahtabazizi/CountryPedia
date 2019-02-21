package com.example.mahtab.countrypedia.ui.countrieslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahtab.countrypedia.R;
import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import com.example.mahtab.countrypedia.util.ListResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesFragment extends Fragment{

    private static ListResponse<CountriesResponse> countriesResponse;
    private static Context mContext;
    @BindView(R.id.countries_list)
    RecyclerView countriesList;

    public static final String FRAGMENT_TAG = "COUNTRIES_FRAGMENT";
    public CountriesFragment() {
    }

    public static CountriesFragment newInstance(Context context) {
        mContext=context;
        return new CountriesFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.countries_fragment, container, false);
        ButterKnife.bind(this, retView);
        return retView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // initRecyclerView();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRecyclerView() {

        CountriesAdapter adapter = new CountriesAdapter(mContext, countriesResponse);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        countriesList.setLayoutManager(linearLayoutManager);
        countriesList.setAdapter(adapter);

    }

    public void setResponses(ListResponse<CountriesResponse> responses) {
        countriesResponse=responses;
        initRecyclerView();    }
}
