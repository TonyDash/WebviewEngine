package com.cjy.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.cjy.webview.I2WebViewProcessInterface;
import com.cjy.webview.WebViewCallBack;
import com.cjy.webview.bean.JsParam;
import com.cjy.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.cjy.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.cjy.webview.webviewprocess.webviewclient.MyWebViewClient;
import com.google.gson.Gson;

import java.util.Map;

public class BaseWebView extends WebView {
    public static final String TAG = "WebView";

    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        CommandDispatcher.Companion.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "xiangxuewebview");
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        setWebViewClient(new MyWebViewClient(webViewCallBack));
        setWebChromeClient(new MyWebChromeClient(webViewCallBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                CommandDispatcher.Companion.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param), this);
            }
        }
    }

    public void handleCallback(String callbackName, String response) {
        post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
                    String jscode = "javascript:xiangxuejs.callback('" + callbackName + "'," + response + ")";
                    evaluateJavascript(jscode, null);
                }
            }
        });

    }
}
