package com.ikoon.httplibrary.http;

import android.content.Context;

import com.ikoon.httplibrary.utils.HttpLibraryUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHttp 缓存拦截器
 *
 * @author MrKong
 * @date 18/4/22
 */

public class HttpCacheInterceptor implements Interceptor
{
    
    private Context mContext;
    
    public HttpCacheInterceptor(Context context)
    {
        mContext = context;
    }
    
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        if (HttpLibraryUtils.isNetworkAvailable(mContext))
        {
            Response response = chain.proceed(request);
            int maxAge = 5;
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + maxAge).build();
        } else
        {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            Response response = chain.proceed(request);
            
            //use next variable cache times is 7 days
            int maxStale = 60 * 60 * 24 * 7;
            
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, only-if-cached, max-stale=2419200").build();
        }
    }
    
}
