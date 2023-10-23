package com.example.eyeOpeningKotlin.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.eyeOpeningKotlin.extension.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 *
 * @author driverSkr
 * 创建日期：2023/10/18
 *
 * 异步获取数据
 * [getData] [readBooleanFlow] [readFloatFlow] [readIntFlow] [readLongFlow] [readStringFlow]
 * 同步获取数据
 * [getSyncData] [readBooleanData] [readFloatData] [readIntData] [readLongData] [readStringData]
 *
 * 异步写入数据
 * [putData] [saveBooleanData] [saveFloatData] [saveIntData] [saveLongData] [saveStringData]
 * 同步写入数据
 * [putSyncData] [saveSyncBooleanData] [saveSyncFloatData] [saveSyncIntData] [saveSyncLongData] [saveSyncStringData]
 *
 * 异步清除数据
 * [clear]
 * 同步清除数据
 * [clearSync]
 *
 * 描述：DataStore 工具类
 *
 */

object DataStoreUtils {

    fun readBooleanData(key: String,default: Boolean = false): Boolean {
        var value = false
        runBlocking {
            dataStore.data.first{
                value = it[booleanPreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }

    fun readStringData(key: String,default: String = ""): String{
        var value = ""
        runBlocking {
            dataStore.data.first {
                value = it[stringPreferencesKey(key)] ?: default
                true
            }
        }
        return value
    }

    suspend fun saveBooleanData(key: String,value: Boolean){
        dataStore.edit { mutablePreferences ->
            mutablePreferences[booleanPreferencesKey(key)] = value
        }
    }

    fun saveSyncBooleanData(key: String,value: Boolean) = runBlocking { saveBooleanData(key, value) }

    suspend fun saveStringData(key: String,value: String){
        dataStore.edit { mutablePreferences ->
            mutablePreferences[stringPreferencesKey(key)] = value
        }
    }
}