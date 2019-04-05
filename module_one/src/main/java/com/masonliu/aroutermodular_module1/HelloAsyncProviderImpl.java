package com.masonliu.aroutermodular_module1;

import android.app.Activity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncCallbackWrapper;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;


@Route(path = "/provider/moduleOneHelloAsync")
public class HelloAsyncProviderImpl extends RouterAsyncProvider {

    @Override
    public void doAction(Activity activity, Map<String, String> params, RouterAsyncCallbackWrapper callback) {
        //Toast.makeText(activity,params.get("name"),1).show();
        Map<String, Object> result = new HashMap<>();
        result.put("one", "helloAsync");
        callback.onSuccess(result);
    }
}