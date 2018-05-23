package com.ikoon.httplibrary.http;

import android.content.Context;

import com.ikoon.httplibrary.utils.AppUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by MrKong on 2017/9/12
 * <p>
 * OKHttp 缓存拦截器
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
        if (AppUtil.isNetworkAvailable(mContext))
            {
                Response response = chain.proceed(request);
                int maxAge = 5;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        else
            {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);

                //use next variable cache times is 7 days
                int maxStale = 60 * 60 * 24 * 7;

                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .build();
            }
    }

}
