package com.masonliu.aroutermodular_module1;

import android.app.Application;

import com.masonliu.aroutermodular_base.libplus.arouter.application.IRouterApplication;
import com.masonliu.aroutermodular_base.libplus.arouter.application.RouteApplication;

/**
 * Created by liumeng02 on 2017/11/30.
 */

@RouteApplication
public class ApplicationProviderImpl implements IRouterApplication {
    @Override
    public void onCreate(Application application) {

    }
}