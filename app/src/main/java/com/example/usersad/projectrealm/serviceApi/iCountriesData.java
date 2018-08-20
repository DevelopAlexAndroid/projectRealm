package com.example.usersad.projectrealm.serviceApi;

import com.example.usersad.projectrealm.model.mDataCountries;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface iCountriesData {

    @GET("all")
    Single<List<mDataCountries>> getCountries();

}
