package com.example.usersad.projectrealm.activity.listCountries.mvp;

import com.example.usersad.projectrealm.model.mDataCountries;

import java.util.List;

public interface contractCountriesWorldActivity {

    interface View{

        void showProgres();
        void hideProgres();
        void setAdapter(List<mDataCountries> countriesList);
        void getToastError(String message);
        void stopRefreshing();

    }

    interface Presentr{

        void onCreate();
        void onDestroy();

    }

    interface Repository{

        interface callCompleted{
            void onCompletedCall(List<mDataCountries> list);
            void onFailureCall();
        }
        void callCountries(callCompleted listener);

        interface databaseCheckCompleted{
            void onCompleteddatabaseCheck(List<mDataCountries> list);
            void onFailuredatabaseCheck();
        }
        void dataBaseCountries(databaseCheckCompleted listener);
    }

}
