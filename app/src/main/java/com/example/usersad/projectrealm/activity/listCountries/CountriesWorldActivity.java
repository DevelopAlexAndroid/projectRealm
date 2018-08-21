package com.example.usersad.projectrealm.activity.listCountries;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.usersad.projectrealm.R;
import com.example.usersad.projectrealm.activity.listCountries.mvp.contractCountriesWorldActivity;
import com.example.usersad.projectrealm.activity.listCountries.mvp.presenterCountriesWorldActivity;
import com.example.usersad.projectrealm.adapter.RvCountriesAdapter;
import com.example.usersad.projectrealm.helpers.onItemClickListener;
import com.example.usersad.projectrealm.model.mDataCountries;

import java.util.ArrayList;
import java.util.List;

public class CountriesWorldActivity extends AppCompatActivity implements contractCountriesWorldActivity.View
                                                                            ,onItemClickListener {

    private List<mDataCountries> countriesList = new ArrayList<>();
    private presenterCountriesWorldActivity presentr = new presenterCountriesWorldActivity(this);
    private RvCountriesAdapter rvCountriesAdapter = new RvCountriesAdapter(countriesList);
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        RecyclerView recyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCountries.setLayoutManager(layoutManager);
        rvCountriesAdapter.setItemClickListener(this);
        recyclerViewCountries.setAdapter(rvCountriesAdapter);
        addListener();
        presentr.onCreate();

    }


    @Override
    public void showProgres() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgres() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(List<mDataCountries> countriesList) {
        rvCountriesAdapter.addDataCountries(countriesList);
        rvCountriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getToastError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stopRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presentr.onDestroy();
    }

    @Override
    public void onItemClick(int possition) {
        Intent intent = new Intent("android.intent.action.All_DATA");
        intent.putExtra("position",possition);
        startActivity(intent);
    }

    private void addListener(){
        swipeRefreshLayout.setOnRefreshListener(() -> presentr.refreshCountries());
    }
}
