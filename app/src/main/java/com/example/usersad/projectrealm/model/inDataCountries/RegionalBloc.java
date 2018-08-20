package com.example.usersad.projectrealm.model.inDataCountries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RegionalBloc extends RealmObject {

    @SerializedName("acronym")
    @Expose
    private String acronym;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("otherAcronyms")
    @Expose
    private RealmList<String> otherAcronyms = null;
    @SerializedName("otherNames")
    @Expose
    private RealmList<String> otherNames = null;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<String> getOtherAcronyms() {return otherAcronyms; }

    public RealmList<String> getOtherNames() {return otherNames;}
}
