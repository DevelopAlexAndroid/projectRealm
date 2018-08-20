package com.example.usersad.projectrealm.activity.allData.mvp;

import com.example.usersad.projectrealm.model.mDataCountries;


public interface contractAllInfoCountriesActivity {

    interface View{

        void showProgres();
        void hideProgres();
        void showInfoCountries(mDataCountries mDataCountries);
        int getPossition();
        void getToastError(String message);

    }

    interface Presentr{

        void onCreate();
        void onDestroy();

    }

    interface Repository{

        mDataCountries getInfoDataBaseCountries(int possition);

    }
}
