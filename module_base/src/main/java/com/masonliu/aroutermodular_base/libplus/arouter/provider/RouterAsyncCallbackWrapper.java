package com.masonliu.aroutermodular_base.libplus.arouter.provider;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by masonliu on 2000/3/13.
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
        RouterAsyncCallbackWrapper wrapper = new RouterAsyncCallbackWrapper(callback);
        return wrapper;
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
