package com.ikoon.httplibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 方法工具类
 *
 * @author MrKong
 * @date 2016/10/31
 */

public class HttpLibraryUtils
{
    /**
     * 描述：判断网络是否有效.
     *
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context)
    {
        try
        {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null)
            {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected())
                {
                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    
    /**
     * 读取baseUrl
     *
     * @param url
     * @return
     */
    public static String getBaseUrl(String url)
    {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1)
        {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1)
        {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }
    
}
