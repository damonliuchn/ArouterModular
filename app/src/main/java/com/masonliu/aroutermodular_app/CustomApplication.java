package com.masonliu.aroutermodular_app;


import android.app.Application;

import com.masonliu.aroutermodular_base.libplus.arouter.RouterUtil;

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RouterUtil.init(this);

        RouterUtil.initModuleApplication(this, "/module2/application");
        //更优雅的处理module的application初始化是处理RouteApplication注解
    }
}
