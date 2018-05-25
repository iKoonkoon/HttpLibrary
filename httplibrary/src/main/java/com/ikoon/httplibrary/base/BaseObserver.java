package com.ikoon.httplibrary.base;


import com.ikoon.httplibrary.exception.HttpException;
import com.ikoon.httplibrary.rx.RxManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 *
 * @author MrKong
 * @date 18/4/21
 */

public abstract class BaseObserver<T> implements Observer<T>
{
    
    protected RxManager rxManager;
    
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
        // 统一处理错误
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
