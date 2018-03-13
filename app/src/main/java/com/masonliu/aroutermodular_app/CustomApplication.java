package com.masonliu.aroutermodular_app;


import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.masonliu.aroutermodular_base.libplus.arouter.RouterUtil;

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RouterUtil.init(this);
    }
}
