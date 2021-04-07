package com.cjy.webviewengine.command;

import android.os.RemoteException;
import android.util.Log;

import com.cjy.commonlibrary.autoService.IUserCenterService;
import com.cjy.commonlibrary.eventbus.LoginEvent;
import com.cjy.webview.I2WebViewProcessInterface;
import com.cjy.webview.command.ICommand;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.cjy.baselibrary.autoService.myServiceLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@AutoService(ICommand.class)
public class loginCommand implements ICommand {

    IUserCenterService iUserCenterService = myServiceLoader.Companion.load(IUserCenterService.class);
    I2WebViewProcessInterface callback;
    String callBackName;

    public loginCommand() {
        EventBus.getDefault().register(this);
    }

    @NotNull
    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(@NotNull Map<?, ?> params, I2WebViewProcessInterface callback) {
        Log.d("loginCommand", new Gson().toJson(params));
        if (!iUserCenterService.isLogin()) {
            iUserCenterService.login();
        }
        this.callback = callback;
        this.callBackName = String.valueOf(params.get("callbackName"));
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("accountName",event.getName());
        if (callback!=null){
            try {
                callback.onResult(callBackName,new Gson().toJson(hashMap));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
