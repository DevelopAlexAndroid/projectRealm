package com.example.usersad.projectrealm.activity.allData.mvp;

import android.util.Log;

import com.example.usersad.projectrealm.model.mDataCountries;

import java.util.List;

import io.realm.Realm;

public class repositoryAllInfoCountriesActivity implements contractAllInfoCountriesActivity.Repository {
    private Realm realm;

    @Override
    public mDataCountries getInfoDataBaseCountries(int possition) {

        realm = Realm.getDefaultInstance();
        if (realm.isEmpty()){
            Log.i("mylog","getInfoDataBaseCountries - onFail");
            return null;
        }else {
            Log.i("mylog","getInfoDataBaseCountries - onCompl");

            List<mDataCountries> list = realm.where(mDataCountries.class).findAll();
            return list.get(possition);
        }
    }

    public void closeDB(){
        realm.close();
    }
}
