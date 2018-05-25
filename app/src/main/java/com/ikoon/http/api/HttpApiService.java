package com.ikoon.http.api;

import com.ikoon.httplibrary.base.BaseResponseEntity;

import io.reactivex.Observable;

import retrofit2.http.GET;

import retrofit2.http.Query;

/**
 * @author MrKong
 * @date 2017/9/13
 */

public interface HttpApiService
{
    
    /**
     * 登录接口
     *
     * @param username  用户名
     * @param userpwd   密码
     * @return
     */
    @GET("username")
    Observable<BaseResponseEntity> loginService(@Query("username") String username, @Query("userpwd") String userpwd);
    
}