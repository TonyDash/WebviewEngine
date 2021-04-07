package com.cjy.webviewengine

import com.cjy.baselibrary.BaseApplication
import com.cjy.baselibrary.loadsir.CustomCallback
import com.cjy.baselibrary.loadsir.ErrorCallback
import com.cjy.baselibrary.loadsir.LoadingCallback
import com.cjy.baselibrary.loadsir.TimeoutCallback
import com.cjy.baselibrary.loadsir.EmptyCallback
import com.kingja.loadsir.core.LoadSir


class MyApplication:BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .commit()
    }
}