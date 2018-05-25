package com.ikoon.http.api;


import com.ikoon.http.app.BaseApplication;
import com.ikoon.httplibrary.http.HttpCacheInterceptor;
import com.ikoon.httplibrary.http.HttpManager;
import com.ikoon.httplibrary.http.HttpParamsInterceptor;
import com.ikoon.httplibrary.http.HttpSetting;

import java.io.File;

public class Http extends HttpSetting
{
    public Http()
    {
        setBaseUrl("");
    
    
        HttpParamsInterceptor httpParamsInterceptor = new HttpParamsInterceptor();
        HttpCacheInterceptor httpCacheInterceptor = new HttpCacheInterceptor(BaseApplication.getAppContext());
        File file = new File("");
    
        HttpManager.getInstance().initialize(httpParamsInterceptor, httpCacheInterceptor, file);
        
        
    }
 
}
