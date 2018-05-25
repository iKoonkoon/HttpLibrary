package com.ikoon.httplibrary.exception;

/**
 * 回调统一请求异常
 * <p>
 *
 * @author MrKong
 * @date 2017/9/12
 */

public class ApiException extends Exception
{
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String Message;
    
    public ApiException(Throwable e)
    {
        super(e);
    }
    
    public ApiException(Throwable cause, @CodeException.CodeExceptions int code, String showMsg)
    {
        super(showMsg, cause);
        setCode(code);
        setMessage(showMsg);
    }
    
    @CodeException.CodeExceptions
    public int getCode()
    {
        return code;
    }
    
    public void setCode(@CodeException.CodeExceptions int code)
    {
        this.code = code;
    }
    
    @Override
    public String getMessage()
    {
        return Message;
    }
    
    public void setMessage(String message)
    {
        this.Message = message;
    }
}
