package com.ikoon.httplibrary.http;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ikoon.httplibrary.BuildConfig;
import com.ikoon.httplibrary.utils.DateDeserializer;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author MrKong
 * @date 18/4/22
 */

public class HttpManager
{
    /**
     * 基础url
     */
    private static String baseUrl = "";
    /**
     * 备用url
     */
    private static String snapShotUrl = "";
    /**
     * 备用url
     */
    private static String standbyUrl = "";
    /**
     * 连接超时时长-默认15秒
     */
    private static int connectionTime = 15;
    /**
     * 获取资源超时时长-默认15秒
     */
    private static int writeTime = 15;
    /**
     * 存储资源超时时长-默认15秒
     */
    private static int readTime = 15;
    
    private static HttpManager instance;
    private OkHttpClient okHttpClient;
    private Gson gson;
    private Object domainService;
    private Object domainServiceStandby;
    private Object cacheDomainService;
    private Object snapShotService;
    private OkHttpClient okHttpCacheClient;
    
    public static HttpManager getInstance()
    {
        if (instance == null)
        {
            synchronized (HttpManager.class)
            {
                if (instance == null)
                {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }
    
    /**
     * 设置接口访问地址
     *
     * @param mBaseurl
     * @param mStandbyUrl
     * @param mSnapShotUrl
     */
    public static void setUrl(String mBaseurl, String mStandbyUrl, String mSnapShotUrl)
    {
        baseUrl = mBaseurl;
        standbyUrl = mStandbyUrl;
        snapShotUrl = mSnapShotUrl;
    }
    
    /**
     * 设置连接时间
     *
     * @param mConnectionTime
     * @param mReadTime
     * @param mWriteTime
     */
    public static void setConnectionTime(int mConnectionTime, int mReadTime, int mWriteTime)
    {
        connectionTime = mConnectionTime;
        readTime = mReadTime;
        writeTime = mWriteTime;
    }
    
    /**
     * 初始化 Http
     *
     * @param httpParamsInterceptor
     * @param httpCacheInterceptor
     * @param cacheDir
     */
    public void initialize(Interceptor httpParamsInterceptor, Interceptor httpCacheInterceptor, File cacheDir)
    {
        // 初始化 Json转化器
        gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        // 初始化 日志打印拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = initHttpLoggingInterceptor();
        // 初始化 okHttp
        okHttpClient = initOkHttpClient(connectionTime, readTime, httpParamsInterceptor, httpLoggingInterceptor);
        // 初始化 OKHttpCache
        okHttpCacheClient = initOkHttpCacheClient(connectionTime, connectionTime, cacheDir, httpParamsInterceptor, httpLoggingInterceptor, httpCacheInterceptor);
    }
    
    /**
     * 初始化 日志打印拦截器
     *
     * @return
     */
    private static HttpLoggingInterceptor initHttpLoggingInterceptor()
    {
        // 新建日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // 日志显示级别
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        return loggingInterceptor;
    }
    
    /**
     * 初始化 OkHttp
     *
     * @param connectTime            链接时长
     * @param readTime               读取时长
     * @param httpParamsInterceptor  参数拦截器
     * @param httpLoggingInterceptor 日志拦截器
     * @return
     */
    private static OkHttpClient initOkHttpClient(int connectTime, int readTime, Interceptor httpParamsInterceptor, Interceptor httpLoggingInterceptor)
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTime, TimeUnit.SECONDS);
        builder.readTimeout(readTime, TimeUnit.SECONDS);
        builder.writeTimeout(writeTime, TimeUnit.SECONDS);
        builder.addInterceptor(httpParamsInterceptor);
        builder.retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG)
        {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        
        return builder.build();
    }
    
    /**
     * @param connectTime            链接时长
     * @param readTime               读取时长
     * @param cacheDir               缓存文件路径
     * @param httpParamsInterceptor  参数拦截器
     * @param httpLoggingInterceptor 日志拦截器
     * @param httpCacheInterceptor   缓存拦截器
     * @return
     */
    private static OkHttpClient initOkHttpCacheClient(int connectTime, int readTime, File cacheDir, Interceptor httpParamsInterceptor, Interceptor httpLoggingInterceptor, Interceptor httpCacheInterceptor)
    {
        File cacheFile = new File(cacheDir, "okhttp_cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTime, TimeUnit.SECONDS);
        builder.readTimeout(readTime, TimeUnit.SECONDS);
        builder.addInterceptor(httpParamsInterceptor);
        builder.addInterceptor(httpCacheInterceptor);
        builder.addNetworkInterceptor(httpCacheInterceptor);
        builder.cache(cache);
        builder.build();
        
        if (BuildConfig.DEBUG)
        {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        
        return builder.build();
    }
    
    /**
     * 获取Retrofit对象
     *
     * @param baseUrl API路径
     * @return
     */
    private static Retrofit getRetrofit(String baseUrl)
    {
        if (getInstance().okHttpClient == null)
        {
            throw new IllegalArgumentException(HttpManager.class.getSimpleName() + " > " + "getRetrofit not initialize");
        }
        return new Retrofit.Builder().baseUrl(appendProtocol(baseUrl))
                                     .addConverterFactory(GsonConverterFactory.create(getInstance().gson))
                                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                     .client(getInstance().okHttpClient)
                                     .build();
    }
    
    /**
     * 获取RetrofitCache对象
     *
     * @param baseUrl API路径
     * @return
     */
    private static Retrofit getCacheRetrofit(String baseUrl)
    {
        if (getInstance().okHttpCacheClient == null)
        {
            throw new IllegalArgumentException(HttpManager.class.getSimpleName() + " > " + "getCacheRetrofit not initialize");
        }
        return new Retrofit.Builder().baseUrl(appendProtocol(baseUrl))
                                     .addConverterFactory(GsonConverterFactory.create(getInstance().gson))
                                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                     .client(getInstance().okHttpCacheClient)
                                     .build();
    }
    
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getDomainService(Class<T> service)
    {
        if (getInstance().domainService == null)
        {
            getInstance().domainService = getRetrofit(baseUrl).create(service);
        }
        return (T) getInstance().domainService;
    }
    
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getDomainService(Class<T> service, String url)
    {
        getInstance().domainServiceStandby = getRetrofit(url).create(service);
        
        return (T) getInstance().domainServiceStandby;
    }
    
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getDomainServiceStandby(Class<T> service)
    {
        if (getInstance().domainServiceStandby == null)
        {
            getInstance().domainServiceStandby = getRetrofit(standbyUrl).create(service);
        }
        return (T) getInstance().domainServiceStandby;
    }
    
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getCacheDomainService(Class<T> service)
    {
        if (getInstance().cacheDomainService == null)
        {
            getInstance().cacheDomainService = getCacheRetrofit(baseUrl).create(service);
        }
        return (T) getInstance().cacheDomainService;
    }
    
    
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getCacheDomainService(Class<T> service, String url)
    {
        getInstance().cacheDomainService = getCacheRetrofit(url).create(service);
        
        return (T) getInstance().cacheDomainService;
    }
    
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getSnapShotService(Class<T> service)
    {
        if (getInstance().snapShotService == null)
        {
            getInstance().snapShotService = getRetrofit(snapShotUrl).create(service);
        }
        return (T) getInstance().snapShotService;
    }
    
    /**
     * @param context    Application Context
     * @param url        Url Address
     * @param requestTag Tag值，取消请求时用到
     * @param callback   异步回调
     */
    public static void asyncCacheRequest(Context context, String url, String requestTag, Callback callback)
    {
        File cacheFile = new File(context.getApplicationContext()
                                         .getCacheDir(), "okhttp_disk_cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        Request request = new Request.Builder().url(url).tag(requestTag).build();
        Call call = getInstance().okHttpCacheClient.newCall(request);
        call.enqueue(callback);
    }
    
    public static String syncGetRequest(String url) throws IOException
    {
        String resultString = null;
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().okHttpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful())
        {
            resultString = response.body().string();
        }
        return resultString;
    }
    
    public static void asyncGetRequest(String url, String requestTag, Callback callback)
    {
        Request request = new Request.Builder().url(url).tag(requestTag).build();
        Call call = getInstance().okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    
    public static void asyncPostRequest(String url, String params, String requestTag, Callback callback)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " + "charset=utf-8"), params);
        Request request = new Request.Builder().url(url).post(requestBody).tag(requestTag).build();
        Call call = getInstance().okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    
    public static void asyncFormPostRequest(String url, Map<String, Object> params, String requestTag, Callback callback)
    {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null)
        {
            for (Map.Entry<String, Object> entry : params.entrySet())
            {
                formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(url)
                                               .post(formBuilder.build())
                                               .tag(requestTag)
                                               .build();
        Call call = getInstance().okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    
    public static void asyncMultipartFormRequest(String url, String params, List<String> filePathList, String requestTag, Callback callback)
    {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        multipartBuilder.addFormDataPart("filedata", params);
        /**
         * multipartBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" +
         * param.key + "\""),
         * RequestBody.create(null, param.value));
         */
        
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        
        if (filePathList != null && filePathList.size() > 0)
        {
            RequestBody fileBody;
            for (int i = 0; i < filePathList.size(); i++)
            {
                File file = new File(filePathList.get(i));
                String contentTypeFile = fileNameMap.getContentTypeFor(filePathList.get(i));
                if (contentTypeFile == null)
                {
                    contentTypeFile = "application/octet-stream";
                }
                fileBody = RequestBody.create(MediaType.parse(contentTypeFile), file);
                multipartBuilder.addFormDataPart("evaluateReq", file.getName(), fileBody);
                
                /**
                 * // 根据文件名设置contentType
                 * multipartBuilder.addPart(Headers.of("Content-Disposition",
                 *                "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + file
                 *                .getName() + "\""),
                 *       fileBody);
                 */
            }
        }
        RequestBody requestBody = multipartBuilder.build();
        Request request = new Request.Builder().url(url).post(requestBody).tag(requestTag).build();
        Call call = getInstance().okHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(callback);
        
    }
    
    private static String appendProtocol(String host)
    {
        Uri uri = Uri.parse(host);
        String url = uri.toString();
        if (!uri.toString().startsWith("http://") && !uri.toString().startsWith("https://"))
        {
            url = "http://" + host;
        }
        if (!url.endsWith("/"))
        {
            url = url + "/";
        }
        return url;
    }
    
    public static void cancelTag(Object tag)
    {
        for (Call call : getInstance().okHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : getInstance().okHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }
    
    public static void cancelAll()
    {
        for (Call call : getInstance().okHttpClient.dispatcher().queuedCalls())
        {
            call.cancel();
        }
        for (Call call : getInstance().okHttpClient.dispatcher().runningCalls())
        {
            call.cancel();
        }
    }
    
}
