package com.cjy.webviewengine;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cjy.baselibrary.autoService.myServiceLoader;
import com.cjy.commonlibrary.autoService.IWebViewService;
import com.cjy.webviewengine.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.tvMiddle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        IWebViewService webViewService = myServiceLoader.Companion.load(IWebViewService.class);
        if (webViewService != null) {
//            webViewService.startWebViewActivity(this,"https://www.baidu.com","baidu",true);
            webViewService.startDemoHtml(this);
        }
    }
}
