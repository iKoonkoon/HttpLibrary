package com.ikoon.httplibrary.base;

/**
 * @author MrKong
 * @date 18/4/21
 * @Description 统一实体类
 */

public class BaseResponseEntity<T>
{
    /**
     *  {
     *      code:0,
     *      msg:"",
     *      obj:{
     *
     *      }
     *  }
     */
    
    private int Code;
    private String Message;
    private T Data;
    
    public int getCode()
    {
        return Code;
    }
    
    public void setCode(int code)
    {
        Code = code;
    }
    
    public String getMessage()
    {
        return Message;
    }
    
    public void setMessage(String message)
    {
        Message = message;
    }
    
    public T getData()
    {
        return Data;
    }
    
    public void setData(T data)
    {
        Data = data;
    }
}
