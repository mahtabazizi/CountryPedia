package com.example.mahtab.countrypedia.ui.countrieslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahtab.countrypedia.R;
import com.example.mahtab.countrypedia.data.model.countries.CountriesResponse;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<CountriesResponse> countriesResponses;

    public CountriesAdapter(Context context, List<CountriesResponse> countriesResponses) {
        this.context = context;
        this.countriesResponses = countriesResponses;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.countries_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CountriesResponse countriesResponse = countriesResponses.get(position);


        holder.countryCapital.setText(countriesResponse.getCapital());

        holder.countryName.setText(countriesResponse.getName());

        holder.layoutItem.setOnClickListener(v -> {

            Toast.makeText(context, "the population is "+countriesResponse.getPopulation().toString(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return countriesResponses == null ? 0 : countriesResponses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void addItem(CountriesResponse countriesResponse) {
        countriesResponses.add(countriesResponse);
        notifyDataSetChanged();
    }

    public void addItem(List<CountriesResponse> countriesResponses) {
        countriesResponses.addAll(countriesResponses);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.country_name_text)
        TextView countryName;

        @BindView(R.id.country_capital_text)
        TextView countryCapital;

        @BindView(R.id.layout_item)
        View layoutItem;


        public ViewHolder(View view) {
            super(view);
          //  ButterKnife.bind(this, view);
            countryName=view.findViewById(R.id.country_name_text);
            countryCapital=view.findViewById(R.id.country_capital_text);
            layoutItem=view.findViewById(R.id.layout_item);
        }
    }
}