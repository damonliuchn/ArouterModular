package com.masonliu.aroutermodular_app;


import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    private void initARouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.printStackTrace();
        ARouter.init(this);
    }
}
