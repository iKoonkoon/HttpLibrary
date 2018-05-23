package com.ikoon.httplibrary.base;

/**
 * Created by MrKong on 18/4/21.
 */

public class BaseResponseEntity<T>
{

    /**
     * code : 1
     * error : null
     * msg : 登录成功
     * detail : {}
     * retain : {}
     */

    private int code;
    private Object error;
    private String msg;
    private T detail;
    private RetainBean retain;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Object getError()
    {
        return error;
    }

    public void setError(Object error)
    {
        this.error = error;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getDetail()
    {
        return detail;
    }

    public DetailBean setDetail(T detail)
    {
        this.detail = detail;

        return null;
    }

    public RetainBean getRetain()
    {
        return retain;
    }

    public void setRetain(RetainBean retain)
    {
        this.retain = retain;
    }

    public static class DetailBean
    {
    }

    public static class RetainBean
    {
    }
}
