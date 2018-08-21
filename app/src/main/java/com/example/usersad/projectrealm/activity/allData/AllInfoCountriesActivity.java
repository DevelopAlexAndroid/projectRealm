package com.example.usersad.projectrealm.activity.allData;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.usersad.projectrealm.R;
import com.example.usersad.projectrealm.activity.allData.mvp.contractAllInfoCountriesActivity;
import com.example.usersad.projectrealm.activity.allData.mvp.presenterAllInfoCountriesActivity;
import com.example.usersad.projectrealm.application.AppProjectRealm;
import com.example.usersad.projectrealm.model.mDataCountries;

public class AllInfoCountriesActivity extends AppCompatActivity implements contractAllInfoCountriesActivity.View {

    private int index;
    private ProgressBar progressBar;
    private presenterAllInfoCountriesActivity presenter = new presenterAllInfoCountriesActivity(this);
    private TextView textName,textRegion,textLaung,textCapital;
    private ImageView imageFlag;
    private GenericRequestBuilder requestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info_countries);

        progressBar = findViewById(R.id.progressBar);
        imageFlag = findViewById(R.id.imageFlag);
        textName = findViewById(R.id.textName);
        textRegion = findViewById(R.id.textRegion);
        textLaung = findViewById(R.id.textLaung);
        textCapital = findViewById(R.id.textCapital);

        requestBuilder = AppProjectRealm.getRequestBuilder();

        Intent intent = getIntent();
        index = intent.getIntExtra("position",0);
        Log.i("mylog", String.valueOf(index));

        presenter.onCreate();
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
    public void showInfoCountries(mDataCountries mDataCountries) {
        Log.i("mylog",mDataCountries.getName());

        textName.setText(mDataCountries.getName());
        textRegion.setText(mDataCountries.getRegion());
        textLaung.setText(mDataCountries.getLanguages().get(0).getName());
        textCapital.setText(mDataCountries.getCapital());

        Uri uri = Uri.parse(mDataCountries.getFlag());
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(imageFlag);

    }

    @Override
    public int getPossition() {
        return index;
    }

    @Override
    public void getToastError(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
