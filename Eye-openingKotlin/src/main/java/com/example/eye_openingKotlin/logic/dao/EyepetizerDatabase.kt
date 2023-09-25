package com.example.eye_openingKotlin.logic.dao

/**
 * 应用程序所有Dao操作管理类。
 *
 * @author vipyinzhiwei
 * @since  2020/5/2
 */
object EyepetizerDatabase {

    private lateinit var mainPageDao: MainPageDao

    private lateinit var videoDao: VideoDao

    fun getMainPageDao() = if (this::mainPageDao.isInitialized) mainPageDao else MainPageDao()

    fun getVideoDao() = if (this::videoDao.isInitialized) videoDao else VideoDao()
}