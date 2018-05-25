package com.ikoon.httplibrary.http;

/**
 * 请求数据基础封装
 *
 * @author MrKong
 * @date 2017/9/12
 */

public class HttpSetting
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
     * 连接超时时长-默认6秒
     */
    private int connectionTime = 6;
    /**
     * 获取资源超时时长-默认15秒
     */
    private int readTime = 15;
    /**
     * 有网情况下的本地缓存时间默认60秒
     */
    private int cacheNetWorkTime = 60;
    /**
     * 无网络的情况下本地缓存时间默认30天
     */
    private int cacheNoNetWorkTime = 24 * 60 * 60 * 30;
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
    
    public int getReadTime()
    {
        return readTime;
    }
    
    public void setReadTime(int readTime)
    {
        this.readTime = readTime;
    }
    
    public int getCacheNetWorkTime()
    {
        return cacheNetWorkTime;
    }
    
    public void setCacheNetWorkTime(int cacheNetWorkTime)
    {
        this.cacheNetWorkTime = cacheNetWorkTime;
    }
    
    public int getCacheNoNetWorkTime()
    {
        return cacheNoNetWorkTime;
    }
    
    public void setCacheNoNetWorkTime(int cacheNoNetWorkTime)
    {
        this.cacheNoNetWorkTime = cacheNoNetWorkTime;
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
}
