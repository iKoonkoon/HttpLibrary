package com.ikoon.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ikoon.httplibrary.base.BaseApi;
import com.ikoon.httplibrary.http.HttpManager;

import io.reactivex.Observable;
import retrofit2.Retrofit;

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
        BaseApi baseApi = new BaseApi()
        {
            @Override
            public Observable getObservable(Retrofit retrofit)
            {
                return null;
            }
        };
    
    
    }
}
