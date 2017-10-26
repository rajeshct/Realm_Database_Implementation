package com.realmbookexample.database;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 */
public class RealmManager {

    private static RealmManager realmManager = new RealmManager();
    private static final String TAG = "RealmManager";

    private Realm realm;
    private RealmConfiguration realmConfiguration;
    private int activityCount = 0;

    public static RealmManager getInstance() {
        return realmManager;
    }

    private RealmManager() {
    }

    public void initializeRealmConfig() {
        if (realmConfiguration == null) {
            Log.d(TAG, "Initializing Realm configuration.");
            setRealmConfiguration(new RealmConfiguration.Builder()
                    .initialData(new RealmInitialData())
                    .deleteRealmIfMigrationNeeded()
                    .build());
        }
    }

    private void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public Realm getRealm() {
        return realm;
    }

    public void incrementCount() {
        if (activityCount == 0) {
            if (realm != null) {
                if (!realm.isClosed()) {
                    Log.w(TAG, "Unexpected open Realm found.");
                    realm.close();
                }
            }
            Log.d(TAG, "Incrementing Activity Count [0]: opening Realm.");
            realm = Realm.getDefaultInstance();
        }
        activityCount++;
        Log.d(TAG, "Increment: Count [" + activityCount + "]");
    }

    public void decrementCount() {
        activityCount--;
        Log.d(TAG, "Decrement: Count [" + activityCount + "]");
        if (activityCount <= 0) {
            Log.d(TAG, "Decrementing Activity Count: closing Realm.");
            activityCount = 0;
            realm.close();
            if (Realm.compactRealm(realmConfiguration)) {
                Log.d(TAG, "Realm compacted successfully.");
            }
            realm = null;
        }
    }
}
