package com.example.dependencyinjectorwithdaggerexample;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import androidx.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    private Context context;

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    public SharedPrefModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPref() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
