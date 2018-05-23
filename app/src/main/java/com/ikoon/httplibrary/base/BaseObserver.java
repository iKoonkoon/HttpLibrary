package com.ikoon.httplibrary.base;


import com.ikoon.httplibrary.exception.HttpException;
import com.ikoon.httplibrary.rx.RxManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by MrKong on 18/4/21.
 */

public abstract class BaseObserver<T> implements Observer<T>
{

    protected RxManager rxManager;

    /**
     * 管理网络请求
     *
     * 防止当Activity销毁时网络还在请求导致空指针异常
     *
     * @param rxManager
     */
    public BaseObserver(RxManager rxManager)
    {
        this.rxManager = rxManager;
    }

    @Override
    public void onSubscribe(Disposable d)
    {
        rxManager.add(d);
    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e)
    {
        //统一处理错误
        String message = HttpException.handlerException(e).getMessage();
        int code = HttpException.handlerException(e).getCode();

        onError("errorCode:" + code + "errorMessage:" + message, Integer.toString(code), message);
    }

    @Override
    public void onComplete()
    {

    }

    /**
     * 错误返回
     *
     * @param codeMessage
     * @param code
     * @param message
     */
    protected abstract void onError(String codeMessage, String code, String message);


}
