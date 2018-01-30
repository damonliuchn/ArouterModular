package com.masonliu.aroutermodular_base.libplus.arouter;

import android.app.Activity;
import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.Map;

/**
 * Created by masonliu on 2017/4/13.
 */
public abstract class RouterProvider implements IProvider {
    protected Context context;

    public abstract Map<String, Object> doAction(Activity activity, String path, Map<String, String> params);

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
