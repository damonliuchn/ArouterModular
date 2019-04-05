package com.masonliu.aroutermodular_app;


import android.app.Application;

import com.masonliu.aroutermodular_base.libplus.arouter.RouterUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RouterUtil.init(this);

        //RouterUtil.initModuleApplication(this, "/provider/moduleOneApplication");
    }
}
