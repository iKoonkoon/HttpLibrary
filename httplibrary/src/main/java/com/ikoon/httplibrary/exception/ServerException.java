package com.ikoon.httplibrary.exception;

/**
 * @author MrKong
 * @date 18/4/22
 */

public class ServerException extends RuntimeException
{
    private int errorCode;
    private String errorMsg;
    
    public ServerException(int errorCode, String errorMsg)
    {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public int getErrorCode()
    {
        return this.errorCode;
    }
    
    public String getErrorMsg()
    {
        return this.errorMsg;
    }
}