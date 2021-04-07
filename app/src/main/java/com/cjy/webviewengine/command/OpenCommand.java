package com.cjy.webviewengine.command;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.cjy.baselibrary.BaseApplication;
import com.cjy.webview.I2WebViewProcessInterface;
import com.cjy.webview.command.ICommand;
import com.google.auto.service.AutoService;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

@AutoService({ICommand.class})
public class OpenCommand implements ICommand {
    @NotNull
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(@NotNull Map params, I2WebViewProcessInterface callback) {
        String targetClass = String.valueOf(params.get("targetClass"));
        if (!TextUtils.isEmpty(targetClass)){
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.Companion.getApplication(),targetClass));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.Companion.getApplication().startActivity(intent);
        }
    }
}
