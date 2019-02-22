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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesFragment extends Fragment{

    List<CountriesResponse> countriesResponses = new ArrayList<>();
    /*  @BindView(R.id.countries_list)
    RecyclerView countriesList;*/
  RecyclerView countriesList;;
    CountriesAdapter adapter;

    public static final String FRAGMENT_TAG = "COUNTRIES_FRAGMENT";
    public CountriesFragment() {
    }

    public static CountriesFragment newInstance() {
        return new CountriesFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.countries_fragment, container, false);
       // ButterKnife.bind(this, retView);
         countriesList=retView.findViewById(R.id.countries_list);
        initRecyclerView();

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

         adapter = new CountriesAdapter(getActivity(), countriesResponses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        countriesList.setLayoutManager(linearLayoutManager);
        countriesList.setAdapter(adapter);

    }

    public void setResponses(ListResponse<CountriesResponse> responses) {

        countriesResponses.addAll(responses);
        if (adapter!=null)
            adapter.notifyDataSetChanged();




    }
}
