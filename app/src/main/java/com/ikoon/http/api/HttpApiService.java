package com.ikoon.http.api;

import com.ikoon.http.entity.requestentity.GetSiteListReqEntity;
import com.ikoon.http.entity.responseentity.GetSiteListResEntity;
import com.ikoon.http.entity.responseentity.GetVehicleListResEntity;
import com.ikoon.httplibrary.base.BaseResponseEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author MrKong
 * @date 2017/9/13
 */

public interface HttpApiService
{
    
    /****************************** 业务处理相关 Start ******************************/
    /**
     * 获取企业车辆接口
     *
     * @param UploadSiteId
     * @return
     */
    @GET(HttpGlobalUrls.GET_VEHICLE_LIST)
    Observable<BaseResponseEntity<List<GetVehicleListResEntity.DataBean>>> getVehicleList(@Query("UploadSiteId") int UploadSiteId);
    /**
     * 获取工地列表接口
     *
     * @param getSiteListReqEntity
     * @return
     */
    @POST(HttpGlobalUrls.GET_SITE_LIST)
    Observable<BaseResponseEntity<GetSiteListResEntity.DataBean>> getSiteList(@Body GetSiteListReqEntity getSiteListReqEntity);
    /****************************** 业务处理相关 End ******************************/
    
}
