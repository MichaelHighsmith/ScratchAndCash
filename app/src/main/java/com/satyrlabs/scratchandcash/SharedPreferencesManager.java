package com.satyrlabs.scratchandcash;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferencesManager {

    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setUserPointTotal(int pointTotal) {
        sharedPreferences.edit().putInt("pointTotal", pointTotal).apply();
    }

    public int getUserPointTotal() {
        return sharedPreferences.getInt("pointTotal", 0);
    }



}
