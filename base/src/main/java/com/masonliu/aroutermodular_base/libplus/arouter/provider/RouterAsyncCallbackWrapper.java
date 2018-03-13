package com.masonliu.aroutermodular_base.libplus.arouter.provider;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by liumeng02 on 2018/3/13.
 */

public class RouterAsyncCallbackWrapper {
    private RouterAsyncCallback callback;

    public RouterAsyncCallbackWrapper(RouterAsyncCallback callback) {
        this.callback = callback;
    }

    public static RouterAsyncCallbackWrapper newInstance(RouterAsyncCallback callback) {
        if (callback != null) {
            callback.onStart();
        }
        RouterAsyncCallbackWrapper fragment = new RouterAsyncCallbackWrapper(callback);
        return fragment;
    }

    public void onSuccess(@Nullable Map<String, Object> result) {
        callback.onSuccess(result);
        callback.onFinish();
    }

    public void onFailed(@Nullable Map<String, Object> error) {
        callback.onFailed(error);
        callback.onFinish();
    }
}
