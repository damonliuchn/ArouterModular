package com.masonliu.aroutermodular_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.masonliu.aroutermodular_base.libplus.arouter.RouterEvent;
import com.masonliu.aroutermodular_base.libplus.arouter.RouterUtil;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncCallback;

import java.util.Map;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. 简单跳转
//                RouterUtil.go("/activity/moduleOneActivity");
                // 2. 跳转携带参数
//                RouterUtil.goWith("/activity/moduleOneActivity")
//                        .withString("stringKey", "66666")
//                        .navigation();
                // 3. 跳转携带参数并有ActivityResult
//                RouterUtil.goWith("/activity/moduleOneActivity")
//                        .withString("stringKey", "app传过来的内容")
//                        .navigation(MainActivity.this, 100);
                // 4. 同步调用
                Map<String, Object> res = RouterUtil.exec(MainActivity.this,
                        "app://account/login?a=b&c=%e5%93%88%e5%93%88");
                if(res!=null){
                    String resV = (String) res.get("one");
                    Toast.makeText(view.getContext(), resV, Toast.LENGTH_LONG).show();
                }

                // 5. 异步调用
//                RouterUtil.execAsync(MainActivity.this,
//                        "/provider/moduleOneHelloAsync?name=sss",
//                        new RouterAsyncCallback() {
//                            @Override
//                            public void onStart() {
//
//                            }
//
//                            @Override
//                            public void onSuccess(@Nullable Map<String, Object> result) {
//                                String resV = (String) result.get("one");
//                                Toast.makeText(MainActivity.this, resV, Toast.LENGTH_LONG).show();
//                            }
//
//                            @Override
//                            public void onFailed(@Nullable Map<String, Object> error) {
//
//                            }
//
//                            @Override
//                            public void onFinish() {
//
//                            }
//                        });
            }
        });
    }


    public void onEvent(RouterEvent routerEvent){
        if("Module1ActivityOnCreate".equals(routerEvent.getEventName())){
            Toast.makeText(MainActivity.this, routerEvent.getEventName(), Toast.LENGTH_LONG).show();
        }
    }
}
