package com.example.usersad.projectrealm.activity.allData.mvp;

import android.util.Log;

import com.example.usersad.projectrealm.model.mDataCountries;

public class presenterAllInfoCountriesActivity implements contractAllInfoCountriesActivity.Presentr {


    private repositoryAllInfoCountriesActivity repository = new repositoryAllInfoCountriesActivity();
    private contractAllInfoCountriesActivity.View actvityInfo;


    public presenterAllInfoCountriesActivity(contractAllInfoCountriesActivity.View view){this.actvityInfo = view;}

    @Override
    public void onCreate() {
        actvityInfo.showProgres();
        int possition = actvityInfo.getPossition();

        mDataCountries infoCountries = repository.getInfoDataBaseCountries(possition);

        if(infoCountries != null){
            Log.i("mylog","infoCountries == null");
            actvityInfo.showInfoCountries(infoCountries);
            actvityInfo.hideProgres();
        }else {
            Log.i("mylog","infoCountries != null");
            actvityInfo.getToastError("Произошла ошибка!");
            actvityInfo.hideProgres();
        }
    }

    @Override
    public void onDestroy() {
        repository.closeDB();
        actvityInfo = null;
    }


}
