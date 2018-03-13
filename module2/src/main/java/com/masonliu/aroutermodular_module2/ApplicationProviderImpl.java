package com.masonliu.aroutermodular_module2;

import android.app.Application;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterApplicationProvider;

/**
 * Created by liumeng02 on 2017/11/30.
 */

@Route(path = "/module2/application")
public class ApplicationProviderImpl extends RouterApplicationProvider {

    @Override
    public void onCreate(Application application) {

    }
}