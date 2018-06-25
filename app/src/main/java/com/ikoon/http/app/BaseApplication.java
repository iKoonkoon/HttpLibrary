package com.ikoon.http.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.ikoon.http.api.HttpInitialize;

public class BaseApplication  extends MultiDexApplication
{
    
    private static BaseApplication baseApplication;
    private static boolean debug;
    
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        baseApplication = this;
        
        // 初始化 Http
        HttpInitialize.getInstance().initialize();
    }
    
    public static void init(){
        setDebug(true);
    }
    
    public static void init(boolean debug){
        setDebug(debug);
    }
    
    public static void setDebug(boolean debug) {
        BaseApplication.debug = debug;
    }
    
    public static boolean isDebug() {
        return debug;
    }
    
    public static Application getApplication() {
        return baseApplication;
    }
    
    public static Context getAppContext() {
        return baseApplication;
    }
    
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    
    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}