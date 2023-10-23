package com.example.eyeOpeningKotlin.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import com.example.eyeOpeningKotlin.EyeopeningApplication

/**
 * 获取DataStore实例
 *
 * @author driverSkr
 * @since  2023/10/17
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = EyeopeningApplication.context.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context,EyeopeningApplication.context.packageName + "_preferences"))
    }
)