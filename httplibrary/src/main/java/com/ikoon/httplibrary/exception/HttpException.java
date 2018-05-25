package com.ikoon.httplibrary.exception;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.IOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.SSLHandshakeException;


/**
 * Created by MrKong on 18/4/21.
 */

public class HttpException
{
    private static final String HttpException_MSG = "网络错误";
    private static final String ConnectException_MSG = "连接失败";
    private static final String JSONException_MSG = "fastjeson解析失败";
    private static final String UnknownHostException_MSG = "无法解析该域名";
    
    public static ApiException handlerException(Throwable throwable)
    {
        ApiException exception = null;
        if (throwable instanceof retrofit2.HttpException)
        {
            retrofit2.HttpException httpException = (retrofit2.HttpException) throwable;
            exception = new ApiException(httpException);
            try
            {
                exception.setMessage(httpException.response().errorBody().string());
            } catch (IOException e)
            {
                e.printStackTrace();
                exception.setMessage(e.getMessage());
            }
        } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException || throwable instanceof ConnectTimeoutException || throwable instanceof UnknownHostException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.NETWORK_ERROR);
            exception.setMessage("网络连接超时，请检查您的网络状态，稍后重试！");
        } else if (throwable instanceof TimeoutException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.TIMEOUT_ERROR);
            exception.setMessage("网络链接超时");
        } else if (throwable instanceof SSLHandshakeException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.SSL_ERROR);
            exception.setMessage("证书验证失败");
        } else if (throwable instanceof NullPointerException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.NULL_POINTER_ERROR);
            exception.setMessage("空指针异常");
        } else if (throwable instanceof ClassCastException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.CAST_ERROR);
            exception.setMessage("类型转换错误");
        } else if (throwable instanceof IllegalStateException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.ILLEGAL_STATE_ERROR);
            exception.setMessage("非法数据异常");
        } else if (throwable instanceof JsonParseException || throwable instanceof JSONException || throwable instanceof JsonSyntaxException || throwable instanceof JsonSerializer || throwable instanceof NotSerializableException || throwable instanceof ParseException)
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.JSON_PARSE_ERROR);
            exception.setMessage("Json数据解析错误");
        } else if (throwable instanceof ServerException)
        {
            exception = new ApiException(throwable);
            exception.setCode(((ServerException) throwable).getErrorCode());
            exception.setMessage(((ServerException) throwable).getErrorMsg());
        } else
        {
            exception = new ApiException(throwable);
            exception.setCode(CodeException.UNKNOWN_ERROR);
            exception.setMessage("未知错误");
        }
        return exception;
    }
    
}