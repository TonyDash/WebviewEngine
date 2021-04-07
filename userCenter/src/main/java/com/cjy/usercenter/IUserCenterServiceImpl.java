package com.cjy.usercenter;

import android.content.Intent;

import com.cjy.baselibrary.BaseApplication;
import com.cjy.commonlibrary.autoService.IUserCenterService;
import com.google.auto.service.AutoService;

@AutoService(IUserCenterService.class)
public class IUserCenterServiceImpl implements IUserCenterService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.Companion.getApplication(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.Companion.getApplication().startActivity(intent);
    }
}
