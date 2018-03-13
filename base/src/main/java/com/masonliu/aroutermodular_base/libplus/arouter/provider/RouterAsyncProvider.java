package com.masonliu.aroutermodular_base.libplus.arouter.provider;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.Map;

/**
 * Created by masonliu on 2017/4/13.
 */
public abstract class RouterAsyncProvider implements IProvider {
    private Context context;

    @Nullable
    public abstract void doAction(@Nullable Activity activity, String path, Map<String, String> params, RouterAsyncCallbackWrapper callback);

    @Override
    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public Context getContext() {
        return context;
    }
}
