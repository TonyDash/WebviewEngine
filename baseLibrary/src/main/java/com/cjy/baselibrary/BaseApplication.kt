package com.cjy.baselibrary

import android.app.Application

open class BaseApplication : Application() {

    companion object {
        var application: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}