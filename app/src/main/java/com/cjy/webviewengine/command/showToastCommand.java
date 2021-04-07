package com.cjy.webviewengine.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.cjy.baselibrary.BaseApplication;
import com.cjy.webview.I2WebViewProcessInterface;
import com.cjy.webview.command.ICommand;
import com.google.auto.service.AutoService;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

@AutoService(ICommand.class)
public class showToastCommand implements ICommand {
    @NotNull
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(@NotNull Map<?, ?> params, I2WebViewProcessInterface callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.Companion.getApplication(),
                        String.valueOf(params.get("message")),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
