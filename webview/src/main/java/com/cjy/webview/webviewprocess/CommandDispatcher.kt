package com.cjy.webview.webviewprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.cjy.baselibrary.BaseApplication
import com.cjy.webview.I2MainProcessAidlInterface
import com.cjy.webview.I2WebViewProcessInterface
import com.cjy.webview.mainprocess.MainProcessCommandService

class CommandDispatcher private constructor() : ServiceConnection {


    private var i2MainProcessAidlInterface:I2MainProcessAidlInterface? = null

    fun initAidlConnection(){
        BaseApplication.application?.let {
            it.bindService(Intent().apply {
                this.setClass(it,MainProcessCommandService::class.java)
            },this, Context.BIND_AUTO_CREATE)
        }
    }


    companion object{
        val Instance = Holder.holder
    }

    private object Holder{
        val holder = CommandDispatcher()
    }

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        i2MainProcessAidlInterface = I2MainProcessAidlInterface.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName) {
        i2MainProcessAidlInterface = null
        initAidlConnection()
    }

    override fun onBindingDied(name: ComponentName?) {
        super.onBindingDied(name)
        i2MainProcessAidlInterface = null
        initAidlConnection()
    }

    fun executeCommand(commandName: String, jsonParams: String,webview:BaseWebView){
        i2MainProcessAidlInterface?.run {
            this.handleWebCommand(commandName, jsonParams, object : I2WebViewProcessInterface.Stub(){
                override fun onResult(callName: String, response: String) {
                    webview.handleCallback(callName,response)
                }

            })
        }
    }
}