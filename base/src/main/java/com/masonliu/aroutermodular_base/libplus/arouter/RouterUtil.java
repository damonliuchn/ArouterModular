package com.masonliu.aroutermodular_base.libplus.arouter;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterApplicationProvider;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncCallback;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncCallbackWrapper;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterAsyncProvider;
import com.masonliu.aroutermodular_base.libplus.arouter.provider.RouterProvider;

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
        //

    }

    public static Map<String, Object> exec(@Nullable Activity source, String pathQuery) {
        try {
            URL url = new URL("http://native" + pathQuery);
            Uri uri = Uri.parse(url.toString());
            RouterProvider routerService = (RouterProvider) ARouter.getInstance().build(uri).navigation();
            if (routerService != null) {
                Map<String, String> query = getUrlParams(url);
                String method = getMethod(query);
                return routerService.doAction(source, method, query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initModuleApplication(Application application, String path) {
        try {
            URL url = new URL("http://native" + path);
            Uri uri = Uri.parse(url.toString());
            RouterApplicationProvider routerService = (RouterApplicationProvider) ARouter.getInstance().build(uri).navigation();
            if (routerService != null) {
                routerService.onCreate(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void execAsync(@Nullable Activity source, String pathQuery, RouterAsyncCallback callback) {
        Map<String, Object> error = new HashMap<>();
        try {
            URL url = new URL("http://native" + pathQuery);
            Uri uri = Uri.parse(url.toString());
            RouterAsyncProvider routerService = (RouterAsyncProvider) ARouter.getInstance().build(uri).navigation();
            if (routerService != null) {
                Map<String, String> query = getUrlParams(url);
                String method = getMethod(query);
                routerService.doAction(source, method, query, RouterAsyncCallbackWrapper.newInstance(callback));
            } else {
                if (callback != null) {
                    error.put("errorMsg", "async service not found");
                    callback.onFailed(error);
                    callback.onFinish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (callback != null) {
                error.put("errorMsg", e.toString());
                callback.onFailed(error);
                callback.onFinish();
            }
        }
    }

    public static void go(String path) {
        try {
            ARouter.getInstance().build(path).navigation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Postcard goWith(String path) {
        try {
            return ARouter.getInstance().build(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> getUrlParams(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new HashMap<>();
        String query = url.getQuery();
        if (query != null && query.length() > 0) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }
        return query_pairs;
    }

    private static String getMethod(Map<String, String> query) {
        String method = query.get("method");
        if (method == null || method.length() == 0) {
            method = "default";
        }
        return method;
    }
}
