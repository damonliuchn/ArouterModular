package com.masonliu.aroutermodular_base.libplus.arouter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.Map;

/**
 * Created by masonliu on 2017/4/13.
 */
public abstract class RouterProvider implements IProvider {
    private Context context;

    @Nullable
    public abstract Map<String, Object> doAction(@Nullable Activity activity, String path, Map<String, String> params);

    @Override
    public void init(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
