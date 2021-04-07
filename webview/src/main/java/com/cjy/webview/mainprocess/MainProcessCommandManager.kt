package com.cjy.webview.mainprocess

import android.content.ComponentName
import android.content.Intent
import android.text.TextUtils
import com.cjy.baselibrary.BaseApplication
import com.cjy.webview.I2MainProcessAidlInterface
import com.cjy.webview.I2WebViewProcessInterface
import com.cjy.webview.command.ICommand
import com.google.gson.Gson
import java.util.*
import kotlin.collections.HashMap

class MainProcessCommandManager : I2MainProcessAidlInterface.Stub {

    private val commands:HashMap<String,ICommand> = HashMap()

    private constructor():super(){
        val serviceLoader:ServiceLoader<ICommand> = ServiceLoader.load(ICommand::class.java)
        for (iCommand in serviceLoader) {
            if (!commands.containsKey(iCommand.name())){
                commands[iCommand.name()] = iCommand
            }
        }
    }

    override fun handleWebCommand(commandName: String, jsonParams: String,callback: I2WebViewProcessInterface) {
        Instance.executeCommand(
            commandName,
            Gson().fromJson(jsonParams, Map::class.java),
            callback
        )
    }

    private fun executeCommand(commandName: String, params: Map<*, *>,callback: I2WebViewProcessInterface) {
        commands[commandName]?.execute(params,callback)
    }

    companion object {
        val Instance = Holder.holder
    }

    private object Holder {
        val holder = MainProcessCommandManager()
    }
}