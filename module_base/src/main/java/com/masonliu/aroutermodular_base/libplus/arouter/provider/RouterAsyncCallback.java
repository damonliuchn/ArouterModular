package com.masonliu.aroutermodular_base.libplus.arouter.provider;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by masonliu on 2000/3/13.
 */

public interface RouterAsyncCallback {
    void onStart();

    void onSuccess(@Nullable Map<String, Object> result);

    void onFailed(@Nullable Map<String, Object> error);

    void onFinish();
}
