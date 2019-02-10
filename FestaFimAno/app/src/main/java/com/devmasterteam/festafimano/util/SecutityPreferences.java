package com.devmasterteam.festafimano.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SecutityPreferences {
    private final SharedPreferences mSharedPreferences;

    public SecutityPreferences(Context context){
        this.mSharedPreferences = context.getSharedPreferences("Fim de Ano", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key,"" );
    }
}
