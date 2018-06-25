package com.ikoon.http.api;

import com.ikoon.httplibrary.http.HttpCacheInterceptor;
import com.ikoon.httplibrary.http.HttpManager;
import com.ikoon.httplibrary.http.HttpParamsInterceptor;

import java.io.File;
import java.util.HashMap;

import static com.ikoon.http.app.BaseApplication.getAppContext;


/**
 * @author MrKong
 * @date 2018/6/14
 * @description 初始化 设置 http
 */

public class HttpInitialize
{
    private static HttpInitialize instance;
    private HttpCacheInterceptor mHttpCacheInterceptor;
    private HttpParamsInterceptor mHttpParamsInterceptor;
    
    public static HttpInitialize getInstance()
    {
        if (instance == null)
        {
            synchronized (HttpManager.class)
            {
                if (instance == null)
                {
                    instance = new HttpInitialize();
                }
            }
        }
        return instance;
    }
    
    public void initialize()
    {

        // 设置接口地址
        HttpManager.setUrl(HttpGlobalUrls.BASE_URL, null,null);
        // 初始化 http
        HttpManager.getInstance()
                   .initialize(getHttpParamsInterceptor(), getHttpCacheInterceptor(), getCacheFile());
    }
    
    public HttpCacheInterceptor getHttpCacheInterceptor()
    {
        if (mHttpCacheInterceptor == null)
        {
            mHttpCacheInterceptor = new HttpCacheInterceptor(getAppContext());
        }
        return mHttpCacheInterceptor;
    }
    
    public HttpParamsInterceptor getHttpParamsInterceptor()
    {
        if (mHttpParamsInterceptor == null)
        {
            HashMap<String, String> paramsMap = new HashMap<>(2);
            paramsMap.put("Content-Type", "application/json;charset=UTF-8");
            paramsMap.put("token", "23CB43AED5234E6CA07E0DA7AB76923E");
            mHttpParamsInterceptor = new HttpParamsInterceptor.Builder().addHeaderParamsMap(paramsMap).build();
        }
        return mHttpParamsInterceptor;
    }
    
    public File getCacheFile()
    {

        return new File("");
    }
}
