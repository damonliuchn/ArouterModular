package com.masonliu.aroutermodular_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.masonliu.aroutermodular_base.libplus.arouter.ByeProvider;
import com.masonliu.aroutermodular_base.libplus.arouter.HelloProvider;

public class MainActivity extends AppCompatActivity {
    @Autowired
    HelloProvider helloProvider;
    @Autowired(name = "/service/hello")
    HelloProvider helloProvider2;

    HelloProvider helloProvider3;

    HelloProvider helloProvider4;

    @Autowired
    ByeProvider byeProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. (推荐)使用依赖注入的方式发现服务,通过注解标注字段,即可使用，无需主动获取
        // Autowired注解中标注name之后，将会使用byName的方式注入对应的字段，不设置name属性，会默认使用byType的方式发现服务(当同一接口有多个实现的时候，必须使用byName的方式发现服务)
        ARouter.getInstance().inject(this);
        helloProvider.sayHello("Vergil");
        helloProvider2.sayHello("Vergil");

        // 2. 使用依赖查找的方式发现服务，主动去发现服务并使用，下面两种方式分别是byName和byType
        helloProvider3 = ARouter.getInstance().navigation(HelloProvider.class);
        helloProvider4 = (HelloProvider) ARouter.getInstance().build("/service/hello").navigation();
        helloProvider3.sayHello("Vergil");
        helloProvider4.sayHello("Vergil");

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build("/test/activity").navigation();

//                // 2. 跳转并携带参数
//                ARouter.getInstance().build("/test/1")
//                        .withLong("key1", 666L)
//                        .withString("key3", "888")
//                        .navigation();
            }
        });


        byeProvider.sayBye("ssssss");
    }
}
