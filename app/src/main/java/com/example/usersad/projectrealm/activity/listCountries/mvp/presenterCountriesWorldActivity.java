package com.example.usersad.projectrealm.activity.listCountries.mvp;

import android.util.Log;

import com.example.usersad.projectrealm.model.mDataCountries;

import java.util.List;

public class presenterCountriesWorldActivity implements contractCountriesWorldActivity.Presentr,
                                                            contractCountriesWorldActivity.Repository.callCompleted,
                                                            contractCountriesWorldActivity.Repository.databaseCheckCompleted{

    private contractCountriesWorldActivity.View activity;
    private repositoryCountriesWorldActivity repository = new repositoryCountriesWorldActivity();

    public presenterCountriesWorldActivity (contractCountriesWorldActivity.View viewActivity)
                                            {this.activity = viewActivity;}


    @Override
    public void onCreate() {
        activity.showProgres();
        checkDataBase();
    }


    @Override
    public void onDestroy() {
        repository.closeRepositoriy();
        activity = null;
    }

    @Override
    public void onCompletedCall(List<mDataCountries> list) {
        activity.setAdapter(list);
        activity.hideProgres();
        activity.stopRefreshing();
        repository.saveDataBase();
    }

    @Override
    public void onCompleteddatabaseCheck(List<mDataCountries> list) {
        Log.i("mylog",list.get(1).getName() + "  it's DATABASE!");
        activity.setAdapter(list);
        activity.hideProgres();
    }

    @Override
    public void onFailuredatabaseCheck() {
        repository.callCountries(this);
        activity.hideProgres();
        activity.getToastError("DataBase empty!");
    }

    @Override
    public void onFailureCall() {
        activity.getToastError("Fail call!");
        activity.hideProgres();
    }

    private void checkDataBase(){
        //if dataBase.Empty =>
        repository.dataBaseCountries(this);
    }

    public void refreshCountries(){
        repository.callCountries(this);
    }
}
