package com.masonliu.aroutermodular_module1;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.HelloProvider;

/**
 * Created by liumeng02 on 2017/11/30.
 */

@Route(path = "/service/hello", name = "测试服务")
public class HelloProviderImpl implements HelloProvider {

    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }

    @Override
    public void init(Context context) {

    }
}