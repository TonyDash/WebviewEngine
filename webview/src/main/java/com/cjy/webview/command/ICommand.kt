package com.cjy.webview.command

import com.cjy.webview.I2WebViewProcessInterface

interface ICommand {
    fun name():String
    fun execute(params:Map<*,*>,callback: I2WebViewProcessInterface)
}