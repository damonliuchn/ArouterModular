package com.masonliu.aroutermodular_module2;

import android.app.Activity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncProvider;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncCallbackWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liumeng02 on 2017/11/30.
 */

@Route(path = "/module2/asyncMethod")
public class HelloAsyncProviderImpl extends RouterAsyncProvider {

    @Override
    public void doAction(Activity activity, String path, Map<String, String> params, RouterAsyncCallbackWrapper callback) {
        Log.e("service/hello", path);
        Map<String, Object> result = new HashMap<>();
        result.put("one", "hello");
        callback.onSuccess(result);
    }
}