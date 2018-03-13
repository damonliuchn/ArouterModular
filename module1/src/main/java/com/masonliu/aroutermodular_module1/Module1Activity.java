package com.masonliu.aroutermodular_module1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.masonliu.aroutermodular_base.libplus.arouter.RouterUtil;

@Route(path = "/test/activity")
public class Module1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1_activity_main);
        TextView textView1 = findViewById(R.id.text);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                RouterUtil.go("/test2/activity2");

//                // 2. 跳转并携带参数
//                ARouter.getInstance().build("/test/1")
//                        .withLong("key1", 666L)
//                        .withString("key3", "888")
//                        .navigation();
            }
        });
    }
}
