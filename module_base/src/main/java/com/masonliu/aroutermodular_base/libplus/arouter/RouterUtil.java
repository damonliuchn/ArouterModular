package com.masonliu.aroutermodular_base.libplus.arouter;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

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

    public static final String SCHEME_AUTHORITY = "http://native";

    public static void init(Application application) {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.printStackTrace();
        ARouter.init(application);
    }

    public static void initModuleApplication(Application application, String path) {
        try {
            RouterApplicationProvider routerService = (RouterApplicationProvider) ARouter.getInstance()
                    .build(Uri.parse(SCHEME_AUTHORITY + path)).navigation();
            if (routerService != null) {
                routerService.onCreate(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    //  /a/b?a=b   app://account/login?name=aa
    public static Map<String, Object> exec(@Nullable Activity source, String pathQuery) {
        try {

            pathQuery = wrapPathQuery(pathQuery);

            URL urlObj = new URL(SCHEME_AUTHORITY + pathQuery);
            RouterProvider routerService = (RouterProvider) ARouter.getInstance().build(Uri.parse(SCHEME_AUTHORITY + pathQuery)).navigation();
            if (routerService != null) {
                Map<String, String> query = getUrlParams(urlObj);
                return routerService.doAction(source, query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void execAsync(@Nullable Activity source, String pathQuery, RouterAsyncCallback callback) {
        Map<String, Object> error = new HashMap<>();
        try {
            URL urlObj = new URL(SCHEME_AUTHORITY + pathQuery);
            RouterAsyncProvider routerService = (RouterAsyncProvider) ARouter.getInstance().build(Uri.parse(urlObj.toString())).navigation();
            if (routerService != null) {
                Map<String, String> query = getUrlParams(urlObj);
                routerService.doAction(source, query, RouterAsyncCallbackWrapper.newInstance(callback));
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

    /**
     * example : app://account/login?a=b => /_app_/_account_/login?a=b
     * @param content
     * @return
     */
    private static String wrapPathQuery(String content){
        try {
            Uri uri = Uri.parse(content);
            if (!TextUtils.isEmpty(uri.getScheme()) && !TextUtils.isEmpty(uri.getHost())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("/_");
                stringBuilder.append(uri.getScheme());
                stringBuilder.append("_/_");
                stringBuilder.append(uri.getHost());
                stringBuilder.append("_");
                stringBuilder.append(uri.getPath());
                //添加query
                String splits []=content.split("\\?");
                if(splits.length>1){
                    stringBuilder.append("?");
                    stringBuilder.append(splits[1]);
                }
                Log.e("sssssss",stringBuilder.toString());
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
