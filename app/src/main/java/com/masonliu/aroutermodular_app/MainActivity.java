package com.masonliu.aroutermodular_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.masonliu.aroutermodular_base.libplus.arouter.RouterAsyncCallback;
import com.masonliu.aroutermodular_base.libplus.arouter.RouterUtil;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 简单跳转
                RouterUtil.go("/test/activity");
                // 2. 跳转携带参数
//                RouterUtil.goWith("/test/activity")
//                        .withLong("longKey", 0x555555L)
//                        .withString("stringKey", "66666")
//                        .navigation();
                // 3. 跳转携带参数并有ActivityResult
                RouterUtil.goWith("/test/activity")
                        .withString("data", "app传过来的内容")
                        .navigation(MainActivity.this, 100);
                // 4. 同步调用
                Map<String, Object> res = RouterUtil.exec(MainActivity.this, "/service/hello");
                String resV = (String) res.get("one");
                Toast.makeText(view.getContext(), resV, Toast.LENGTH_LONG).show();

                // 5. 异步调用
                RouterUtil.execAsync(MainActivity.this, "/service/hello",
                        new RouterAsyncCallback() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onSuccess(@Nullable Map<String, Object> result) {

                            }

                            @Override
                            public void onFailed(@Nullable Map<String, Object> error) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
            }
        });
    }
}
