package com.masonliu.aroutermodular_base.libplus.arouter;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by liumeng02 on 2018/3/13.
 */

public interface RouterAsyncCallback {
    void onStart();

    void onSuccess(@Nullable Map<String, Object> result);

    void onFailed(@Nullable Map<String, Object> error);

    void onFinish();
}
