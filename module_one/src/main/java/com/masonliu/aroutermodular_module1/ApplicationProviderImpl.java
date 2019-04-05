package com.masonliu.aroutermodular_module1;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterApplicationProvider;


@Route(path = "/provider/moduleOneApplication")
public class ApplicationProviderImpl extends RouterApplicationProvider {

    @Override
    public void onCreate(Application application) {
        Log.d("ApplicationProviderImpl","/provider/moduleOneApplication");
    }
}