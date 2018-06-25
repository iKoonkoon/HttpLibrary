package com.ikoon.http.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ikoon.http.api.HttpApiService;
import com.ikoon.http.entity.requestentity.GetSiteListReqEntity;
import com.ikoon.http.entity.responseentity.GetSiteListResEntity;
import com.ikoon.http.entity.responseentity.GetVehicleListResEntity;
import com.ikoon.httplibrary.base.BaseHttpResultFunction;
import com.ikoon.httplibrary.base.BaseObserver;
import com.ikoon.httplibrary.http.HttpManager;
import com.ikoon.httplibrary.rx.RxManager;
import com.ikoon.httplibrary.rx.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrKong
 */
public class MainActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        initView();
        initData();
        doBusiness();
    }
    
    private void initData()
    {
    
    }
    
    private void initView()
    {
    
    }
    
    private void doBusiness()
    {
        // Get 请求示例
        getVehicleList();
        // Post 请求示例
        getSiteList("");
    }
    
    /**
     * 访问获取企业车辆列表接口
     */
    private void getVehicleList()
    {
        HttpManager.getDomainServiceStandby(HttpApiService.class)
                   .getVehicleList(0)
                   .map(new BaseHttpResultFunction<List<GetVehicleListResEntity.DataBean>>())
                   .compose(RxSchedulers.<List<GetVehicleListResEntity.DataBean>>io_main())
                   .subscribe(new BaseObserver<List<GetVehicleListResEntity.DataBean>>(new RxManager())
                   {
            
                       @Override
                       public void onNext(List<GetVehicleListResEntity.DataBean> dataBeans)
                       {
                       }
            
                       @Override
                       protected void onError(String codeMessage, String code, String message)
                       {
                       }
                   });
    }
    
    /**
     * 访问获取工地列表接口
     */
    private void getSiteList(String siteName)
    {
        ArrayList<Integer> siteTypesList = new ArrayList<>();
        siteTypesList.add(1);
        siteTypesList.add(2);
        siteTypesList.add(3);
        siteTypesList.add(4);
        siteTypesList.add(5);
        
        GetSiteListReqEntity getSiteListReqEntity = new GetSiteListReqEntity();
        getSiteListReqEntity.setSiteTypes(siteTypesList);
        getSiteListReqEntity.setDepartmentId(3);
        getSiteListReqEntity.setDepartmentName("武汉市");
        getSiteListReqEntity.setPageIndex(1);
        getSiteListReqEntity.setPageSize(1000);
        getSiteListReqEntity.setSiteName(siteName);
        
        HttpManager.getDomainService(HttpApiService.class)
                   .getSiteList(getSiteListReqEntity)
                   .map(new BaseHttpResultFunction<GetSiteListResEntity.DataBean>())
                   .compose(RxSchedulers.<GetSiteListResEntity.DataBean>io_main())
                   .subscribe(new BaseObserver<GetSiteListResEntity.DataBean>(new RxManager())
                   {
            
            
                       @Override
                       public void onNext(GetSiteListResEntity.DataBean dataBean)
                       {
                       }
            
                       @Override
                       protected void onError(String codeMessage, String code, String message)
                       {
                       }
                   });
    }
}
