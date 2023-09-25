package com.example.eye_openingJava.extension;

import static androidx.datastore.preferences.PreferenceDataStoreDelegateKt.preferencesDataStore;

import android.content.Context;

import androidx.datastore.core.DataStore;
import androidx.datastore.migrations.SharedPreferencesMigration;
import androidx.datastore.preferences.core.Preferences;

import com.example.eye_openingJava.EyeopeningApplication;

public class ContextExt {

    private static final String PREFS_NAME = EyeopeningApplication.context.getPackageName() + "_preferences";

    /**
     *@Author: create by boge
     *@Createtime: 2023/9/7 15:22
     *@Description: 获取DataStore实例
    */
    /*public static DataStore<Preferences> getDataStore(Context context) {
        return preferencesDataStore(context,
                PREFS_NAME,
                migrations -> {
                    return new SharedPreferencesMigration(context, PREFS_NAME);
                });
    }*/
}
