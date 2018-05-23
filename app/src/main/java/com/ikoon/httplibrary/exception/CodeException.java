package com.ikoon.httplibrary.exception;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义错误code类型:注解写法
 * <p>
 * 可自由扩展
 *
 * Created by MrKong on 2017/9/12.
 */

public class CodeException {

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 1000;
    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1001;
    /**
     * 连接超时
     */
    public static final int TIMEOUT_ERROR = 1002;
    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1003;
    /**
     * 解析错误
     */
    public static final int UNKNOWN_HOST_ERROR = 1004;
    /**
     * 非法数据异常
     */
    public static final int ILLEGAL_STATE_ERROR = 1005;
    /**
     * 运行时异常 包含自定义异常
     */
    public static final int RUNTIME_ERROR = 1006;
    /**
     * 空指针错误
     */
    public static final int NULL_POINTER_ERROR = 1007;
    /**
     * 类转换错误
     */
    public static final int CAST_ERROR = 1008;
    /**
     * Json数据解析错误
     */
    public static final int JSON_PARSE_ERROR = 1009;

    
    @IntDef({UNKNOWN_ERROR, NETWORK_ERROR, TIMEOUT_ERROR, SSL_ERROR, UNKNOWN_HOST_ERROR,
            ILLEGAL_STATE_ERROR, RUNTIME_ERROR, NULL_POINTER_ERROR, CAST_ERROR, JSON_PARSE_ERROR    })
    @Retention(RetentionPolicy.SOURCE)
    
    public @interface CodeExceptions
    {
    }


    /**
     * 对应HTTP的状态码
     */
    public static class Http {
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int REQUEST_TIMEOUT = 408;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int BAD_GATEWAY = 502;
        public static final int SERVICE_UNAVAILABLE = 503;
        public static final int GATEWAY_TIMEOUT = 504;
    }

    /**
     * Request请求码
     */
    public static class Request {
        //未知错误
        public static final int UNKNOWN = 1000;
        //解析错误
        public static final int PARSE_ERROR = 1001;
        //网络错误
        public static final int NETWORK_ERROR = 1002;
        //协议出错
        public static final int HTTP_ERROR = 1003;
        //证书出错
        public static final int SSL_ERROR = 1005;
        //连接超时
        public static final int TIMEOUT_ERROR = 1006;
        //调用错误
        public static final int INVOKE_ERROR = 1007;
        //类转换错误
        public static final int CONVERT_ERROR = 1008;
    }
}
