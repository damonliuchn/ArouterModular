package com.masonliu.aroutermodular_base.libplus.arouter;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by liumeng on 2017/11/30.
 */

public interface HelloProvider extends IProvider {
    String sayHello(String name);
}