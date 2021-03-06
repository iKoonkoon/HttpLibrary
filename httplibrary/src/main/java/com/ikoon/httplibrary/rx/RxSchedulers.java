package com.ikoon.httplibrary.rx;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author MrKong
 * @date 2018/6/13
 * @description 线程调度工具类
 */

public class RxSchedulers
{
    public static <T> ObservableTransformer<T, T> io_main()
    {
        return new ObservableTransformer<T, T>()
        {
            @Override
            public ObservableSource<T> apply(Observable<T> observable)
            {
                return observable.subscribeOn(Schedulers.io())
                                 .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
