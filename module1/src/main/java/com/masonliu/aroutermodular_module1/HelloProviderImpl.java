package com.masonliu.aroutermodular_module1;

import android.app.Activity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liumeng02 on 2017/11/30.
 */

@Route(path = "/module1/syncMethod")
public class HelloProviderImpl extends RouterProvider {

    @Override
    public Map<String, Object> doAction(Activity activity, String method, Map<String, String> params) {
        Log.e("service/hello", method);
        Map<String, Object> result = new HashMap<>();
        result.put("one", "hello");
        return result;
    }
}