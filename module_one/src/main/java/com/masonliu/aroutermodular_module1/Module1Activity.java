package com.masonliu.aroutermodular_module1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.masonliu.aroutermodular_base.libplus.arouter.RouterEvent;

import de.greenrobot.event.EventBus;

@Route(path = "/activity/moduleOneActivity")
public class Module1Activity extends AppCompatActivity {
    @Autowired
    String stringKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1_activity_main);
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);

        TextView textView1 = findViewById(R.id.text);
        textView1.setText(textView1.getText() + stringKey);

        EventBus.getDefault().post(new RouterEvent("Module1ActivityOnCreate",""));
    }

    public void onEvent(String s){

    }
}
