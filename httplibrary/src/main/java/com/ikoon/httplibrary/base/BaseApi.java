package com.ikoon.httplibrary.base;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * 请求数据基础封装
 *
 * @author MrKong
 * @date 2017/9/12
 */

public abstract class BaseApi
{
    /**
     * token
     */
    private String baseToken = "";
    /**
     * session
     */
    private String baseSession = "";
    /**
     * 基础url
     */
    private String baseUrl = "";
    /**
     * 缓存url-可手动设置
     */
    private String cacheUrl;
    /**
     * 超时时间-默认6秒
     */
    private int connectionTime = 6;
    /**
     * 有网情况下的本地缓存时间默认60秒
     */
    private int cookieNetWorkTime = 60;
    /**
     * 无网络的情况下本地缓存时间默认30天
     */
    private int cookieNoNetWorkTime = 24 * 60 * 60 * 30;
    /**
     * retry次数
     */
    private int retryCount = 3;
    /**
     * retry延迟
     */
    private long retryDelay = 100;
    /**
     * retry叠加延迟
     */
    private long retryIncreaseDelay = 100;

//    //是否能取消加载框
//    private boolean cancel = false;
//    //是否显示加载框
//    private boolean showProgress = true;
//    //是否需要缓存处理
//    private boolean cache = false;
    
    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);
    
    public String getBaseToken()
    {
        return baseToken;
    }
    
    public void setBaseToken(String baseToken)
    {
        this.baseToken = baseToken;
    }
    
    public String getBaseSession()
    {
        return baseSession;
    }
    
    public void setBaseSession(String baseSession)
    {
        this.baseSession = baseSession;
    }
    
    public String getBaseUrl()
    {
        return baseUrl;
    }
    
    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }
    
    public String getCacheUrl()
    {
        return cacheUrl;
    }
    
    public void setCacheUrl(String cacheUrl)
    {
        this.cacheUrl = cacheUrl;
    }
    
    public int getConnectionTime()
    {
        return connectionTime;
    }
    
    public void setConnectionTime(int connectionTime)
    {
        this.connectionTime = connectionTime;
    }
    
    public int getCookieNetWorkTime()
    {
        return cookieNetWorkTime;
    }
    
    public void setCookieNetWorkTime(int cookieNetWorkTime)
    {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }
    
    public int getCookieNoNetWorkTime()
    {
        return cookieNoNetWorkTime;
    }
    
    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime)
    {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }
    
    public int getRetryCount()
    {
        return retryCount;
    }
    
    public void setRetryCount(int retryCount)
    {
        this.retryCount = retryCount;
    }
    
    public long getRetryDelay()
    {
        return retryDelay;
    }
    
    public void setRetryDelay(long retryDelay)
    {
        this.retryDelay = retryDelay;
    }
    
    public long getRetryIncreaseDelay()
    {
        return retryIncreaseDelay;
    }
    
    public void setRetryIncreaseDelay(long retryIncreaseDelay)
    {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

//    public boolean isCancel()
//    {
//        return cancel;
//    }
//
//    public void setCancel(boolean cancel)
//    {
//        this.cancel = cancel;
//    }
//
//    public boolean isShowProgress()
//    {
//        return showProgress;
//    }
//
//    public void setShowProgress(boolean showProgress)
//    {
//        this.showProgress = showProgress;
//    }
//
//    public boolean isCache()
//    {
//        return cache;
//    }
//
//    public void setCache(boolean cache)
//    {
//        this.cache = cache;
//    }
    
    
}
