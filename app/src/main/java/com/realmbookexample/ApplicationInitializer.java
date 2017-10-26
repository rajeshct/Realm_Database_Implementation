package com.realmbookexample;

import android.app.Application;

import com.realmbookexample.database.RealmManager;

import io.realm.Realm;

public class ApplicationInitializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmManager.getInstance().initializeRealmConfig();
    }
}
