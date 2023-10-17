package com.example.eyeOpeningKotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/*
* 自定义Application，在这里进行全局的初始化操作
* 2023/10/17
* */
class EyeopeningApplication: Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}