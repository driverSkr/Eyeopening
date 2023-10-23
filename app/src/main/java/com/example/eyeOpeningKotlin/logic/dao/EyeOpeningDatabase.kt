package com.example.eyeOpeningKotlin.logic.dao

/**
 * 应用程序所有Dao操作管理类。
 *
 * @author driverSkr
 * @since  2023/10/20
 */
object EyeOpeningDatabase {

    private lateinit var mainPageDao: MainPageDao

    private lateinit var videoDao: VideoDao

    fun getMainPageDao() = if (this::mainPageDao.isInitialized) mainPageDao else MainPageDao()

    fun getVideoDao() = if (this::videoDao.isInitialized) videoDao else VideoDao()
}