package com.example.usersad.projectrealm.application;

import android.app.Application;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.GenericRequest;
import com.caverock.androidsvg.SVG;
import com.example.usersad.projectrealm.R;
import com.example.usersad.projectrealm.helpers.SvgDecoder;
import com.example.usersad.projectrealm.helpers.SvgDrawableTrancoder;
import com.example.usersad.projectrealm.helpers.SvgSoftwareLayerSetter;
import com.example.usersad.projectrealm.serviceApi.iCountriesData;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppProjectRealm  extends Application{

    private static iCountriesData countriesData;
    private static GenericRequestBuilder requestBuilder;

    @Override
    public void onCreate() {
        super.onCreate();

        countriesData = getRetrofitBuider().create(iCountriesData.class);

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
        createRequest();
    }

    public static iCountriesData getContriesData(){
        return countriesData;
    }

    public static GenericRequestBuilder getRequestBuilder(){
        return requestBuilder;
    }



    private Retrofit getRetrofitBuider(){

        String baseURL = "https://restcountries.eu/rest/v2/";

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(delayHttp(15))
                .build();
    }

    private OkHttpClient delayHttp(int delay){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        return builder
                .readTimeout(delay, TimeUnit.SECONDS)
                .connectTimeout(delay, TimeUnit.SECONDS)
                .build();
    }

    private void createRequest(){
        requestBuilder = Glide.with(getApplicationContext())
                .using(Glide.buildStreamModelLoader(Uri.class, getApplicationContext()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTrancoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<>());
    }

}
