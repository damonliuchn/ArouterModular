package com.masonliu.aroutermodular_module1;

import android.app.Activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterProvider;

import java.util.HashMap;
import java.util.Map;


@Route(path = "/provider/moduleOneHelloTwo")
public class HelloTwoProviderImpl extends RouterProvider {

    @Override
    public Map<String, Object> doAction(Activity activity, Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("one", "helloTwo");
        return result;
    }
}