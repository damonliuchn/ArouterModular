package com.masonliu.aroutermodular_base.libplus.arouter;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by masonliu on 2017/4/13.
 */

public class RouterUtil {
    public static void init(Application application) {
        //if (AppUtil.getBuidDebugable()) {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.printStackTrace();
        //}
        ARouter.init(application);
    }

    public static Map<String, Object> go(Activity source, String pathQuery) {
        try {
            URL url = new URL("http://native" + pathQuery);
            Uri uri = Uri.parse(url.toString());
            RouterProvider routerService = (RouterProvider) ARouter.getInstance().build(uri).navigation();
            if (routerService != null) {
                return routerService.doAction(source, url.getPath(), getUrlParams(url));
            }
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
