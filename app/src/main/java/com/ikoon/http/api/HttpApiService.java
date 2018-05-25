package com.ikoon.http.api;

import io.reactivex.Observable;

import retrofit2.http.GET;

import retrofit2.http.Query;

/**
 * Created by MrKong on 2017/9/13.
 */

public interface HttpApiService
{

//    //上传图片
//    @Multipart
//    @POST(GlobalUrls.UPDATA_PICTURE)
//    Observable<String> updataPicture(@Part MultipartBody.Part file,
//                                     @Part("ServiceNum") RequestBody serviceNum,
//                                     @Part("ConstructId") RequestBody constructId,
//                                     @Part("VehicleNo") RequestBody vehicleNo,
//                                     @Part("RecordHisId") RequestBody recordHisId);
    
    @GET("username")
    Observable<String> loginService(@Query("username") String username, @Query("userpwd") String userpwd);
    

    
}