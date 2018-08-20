package com.example.usersad.projectrealm.activity.listCountries.mvp;

import android.util.Log;

import com.example.usersad.projectrealm.application.AppProjectRealm;
import com.example.usersad.projectrealm.model.mDataCountries;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class repositoryCountriesWorldActivity implements contractCountriesWorldActivity.Repository{

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<mDataCountries> list;
    private Realm realm;

    @Override
    public void callCountries(callCompleted listener) {
        Log.i("mylog","callCountries ");
        Disposable disposable = AppProjectRealm.getContriesData().getCountries()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    list = data;
                    Log.i("mylog",data.get(0).getName() + " first name - callCountries");
                    listener.onCompletedCall(list);
                },throwable -> {
                    listener.onFailureCall();
                    Log.i("mylog"," - callCountries - fail " + throwable);
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void dataBaseCountries(databaseCheckCompleted listener) {
        Log.i("mylog","Start ");

       //check database empty
        realm = Realm.getDefaultInstance();
        if (realm.isEmpty()){
            Log.i("mylog","dataBaseCountries - onFailuredatabaseCheck");
            listener.onFailuredatabaseCheck();
        }else {
            Log.i("mylog","dataBaseCountries - onCompleteddatabaseCheck");
            listener.onCompleteddatabaseCheck(realm.where(mDataCountries.class).findAll());
        }
    }

    public void saveDataBase(){
        realm.executeTransaction(realm1 -> realm1.insert(list));
    }

    public void closeRepositoriy(){
        realm.close();
        compositeDisposable.clear();
    }
}








