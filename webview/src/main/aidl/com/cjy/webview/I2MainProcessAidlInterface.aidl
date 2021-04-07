// I2MainProcessAidlInterface.aidl
package com.cjy.webview;

import com.cjy.webview.I2WebViewProcessInterface;

// Declare any non-default types here with import statements

interface I2MainProcessAidlInterface {
    void handleWebCommand(String commandName,String jsonParams,in I2WebViewProcessInterface i2WebViewInterface);
}