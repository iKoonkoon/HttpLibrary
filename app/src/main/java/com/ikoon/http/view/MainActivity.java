package com.ikoon.http.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ikoon.http.R;
import com.ikoon.http.api.HttpApiService;
import com.ikoon.httplibrary.base.BaseObserver;
import com.ikoon.httplibrary.base.BaseResponseEntity;
import com.ikoon.httplibrary.http.HttpManager;

/**
 * @author MrKong
 */
public class MainActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        HttpManager.getDomainService(HttpApiService.class)
                   .loginService("","")
                   .subscribe(new BaseObserver<BaseResponseEntity>() {
                       @Override
                       public void onNext(BaseResponseEntity baseResponseEntity)
                       {
        
                       }
    
                       @Override
                       protected void onError(String codeMessage, String code, String message)
                       {
        
                       }
                   });
    
    
    }
}
