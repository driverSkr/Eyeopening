package com.example.eye_openingKotlin.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.eye_openingKotlin.EyeopeningApplication

/**
 * 获取DataStore实例
 *
 * @author vipyinzhiwei
 * @since  2021/5/12
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = EyeopeningApplication.context.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, EyeopeningApplication.context.packageName + "_preferences"))
    })