package com.ikoon.http.api;

/**
 * @author MrKong
 * @date 2017/9/13
 */

public class HttpGlobalUrls
{
    /**
     * api url
     *
     */
    public static final String RELEASE_URL = "";
    public static final String DEBUG_URL = "";
    public static final String DEVELOP_URL1 = "http://192.168.1.207:7998/";
    public static final String DEVELOP_URL2 = "http://192.168.1.199:7777/";
    public static final String DEVELOP_URL3 = "http://192.168.1.207:7998/";
    public static final String BASE_URL = DEVELOP_URL1;
    
    /**
     * 登录接口
     */
    public static final String LOGIN = "/api/AppUserLogin/Login";
    
    /**
     * 获取企业全部车辆接口
     */
    public static final String GET_VEHICLE_LIST = "/api/UploadSite/GetVehList";
    
    /**
     * 获取工地接口
     */
    public static final String GET_SITE_LIST = "/api/AppReports/GetSiteList";
}
