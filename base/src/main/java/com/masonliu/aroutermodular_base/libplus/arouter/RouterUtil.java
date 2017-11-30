package com.masonliu.aroutermodular_base.libplus.arouter;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 1、模块页面跳转：通过指定path实现
 2、模块间调用：通过在Router Module里注册Provider实现
 3、模块间调用：为避免繁琐的增加Provider，可以使用CommonProvider
 4、模块间调用：还可以使用反射进行调用，但不推荐这样做，这样就没有模块暴露服务的契约了，
 当被调用者发生改变，则使用反射的调用方无法使用。
 */
public class RouterUtil {
    public static void init(Application application) {
        ARouter.init(application);
    }

    private static Map<String, Object> Provider(Activity source, String path) {
        try {
            URL url = new URL("http://app" + path);
            CommonProvider routerService = (CommonProvider) ARouter.getInstance().build(url.getPath()).navigation();
            return routerService.doAction(source, url.getPath(), getUrlParams(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> getUrlParams(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new HashMap<>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
