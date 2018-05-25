package com.ikoon.httplibrary.http;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author MrKong
 * @date 18/4/21
 */
public class HttpParamsInterceptor implements Interceptor
{
    private static final String TAG = "HttpParamsInterceptor";
    
    private Map<String, String> queryParamsMap = new HashMap<>();
    private Map<String, String> paramsMap = new HashMap<>();
    private Map<String, String> headerParamsMap = new HashMap<>();
    private List<String> headerLinesList = new ArrayList<>();
    
    public HttpParamsInterceptor()
    {
    
    }
    
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Log.i(TAG, "HttpParamsInterceptor: thread ===> " + Thread.currentThread().getName());
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        HttpUrl.Builder hostBuilder = request.url().newBuilder();
        refactorRequest(request, hostBuilder);
        requestBuilder.url(hostBuilder.build());
        
        // process header params inject
        Headers.Builder headerBuilder = request.headers().newBuilder();
        
        if (headerParamsMap.size() > 0)
        {
            Iterator iterator = headerParamsMap.entrySet().iterator();
            while (iterator.hasNext())
            {
                Map.Entry entry = (Map.Entry) iterator.next();
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }
        
        if (headerLinesList.size() > 0)
        {
            for (String line : headerLinesList)
            {
                headerBuilder.add(line);
            }
        }
        
        requestBuilder.headers(headerBuilder.build());
        // process header params end
        
        
        // process queryParams inject whatever it's GET or POST
        if (queryParamsMap.size() > 0)
        {
            injectParamsIntoUrl(request, requestBuilder, queryParamsMap);
        }
        // process header params end
        
        
        // process post body inject
        if (request.method().equals("POST") && request.body().contentType() != null && request.body().contentType().subtype().equals("x-www-form-urlencoded"))
        {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (paramsMap.size() > 0)
            {
                Iterator iterator = paramsMap.entrySet().iterator();
                while (iterator.hasNext())
                {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
                }
            }
            RequestBody formBody = formBodyBuilder.build();
            String postBodyString = bodyToString(request.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
        } else
        {    // can't inject into body, then inject into url
            injectParamsIntoUrl(request, requestBuilder, paramsMap);
        }
        
        request = requestBuilder.build();
        
        
        Response response;
        try
        {
            response = chain.proceed(request);
        } catch (Exception var27)
        {
            Log.e(TAG, "error: " + request.url() + " " + var27.getMessage());
            var27.printStackTrace();
            response = new Response.Builder().code(500).request(request).protocol(Protocol.HTTP_1_1).build();
        }
        return response;
    }
    
    // func to inject params into url
    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap)
    {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        
        refactorRequest(request, httpUrlBuilder);
        
        if (paramsMap.size() > 0)
        {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext())
            {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        
        requestBuilder.url(httpUrlBuilder.build());
    }
    
    private static String bodyToString(final RequestBody request)
    {
        try
        {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
            {
                copy.writeTo(buffer);
            } else
            {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e)
        {
            return "did not work";
        }
    }
    
    public static class Builder
    {
        
        HttpParamsInterceptor interceptor;
        
        public Builder()
        {
            interceptor = new HttpParamsInterceptor();
        }
        
        public Builder addParam(String key, String value)
        {
            interceptor.paramsMap.put(key, value);
            return this;
        }
        
        public Builder addParamsMap(Map<String, String> paramsMap)
        {
            interceptor.paramsMap.putAll(paramsMap);
            return this;
        }
        
        public Builder addHeaderParam(String key, String value)
        {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }
        
        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap)
        {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }
        
        public Builder addHeaderLine(String headerLine)
        {
            int index = headerLine.indexOf(":");
            if (index == -1)
            {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }
        
        public Builder addHeaderLinesList(List<String> headerLinesList)
        {
            for (String headerLine : headerLinesList)
            {
                int index = headerLine.indexOf(":");
                if (index == -1)
                {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }
        
        public Builder addQueryParam(String key, String value)
        {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }
        
        public Builder addQueryParamsMap(Map<String, String> queryParamsMap)
        {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }
        
        public HttpParamsInterceptor build()
        {
            return interceptor;
        }
        
    }
    
    private String appendProtocol(String host)
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
    
    /**
     * 动态域名修改
     */
    private void refactorRequest(Request request, HttpUrl.Builder httpUrlBuilder)
    {
        String domain;
        switch (request.url().host())
        {
            //api domain
            case "1.1.1.1":
                domain = appendProtocol("");
                break;
            default:
                return;
        }
        
        HttpUrl httpUrl = HttpUrl.parse(domain);
        httpUrlBuilder.host(httpUrl.host());
        httpUrlBuilder.port(httpUrl.port());
        
        List<String> segments = httpUrl.pathSegments();
        
        List<String> originalSegments = request.url().pathSegments();
        
        List<String> requestSegments = new ArrayList<>();
        requestSegments.addAll(segments);
        requestSegments.addAll(originalSegments);
        
        for (int i = 0; i < originalSegments.size(); i++)
        {
            httpUrlBuilder.removePathSegment(originalSegments.size() - 1 - i);
        }
        
        for (String segment : requestSegments)
        {
            httpUrlBuilder.addPathSegment(segment);
        }
    }
    
}
