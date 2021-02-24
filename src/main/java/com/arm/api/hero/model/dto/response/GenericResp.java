package com.arm.api.hero.model.dto.response;

import com.arm.api.hero.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class GenericResp<R> {

    private R result;
    private String message;

    @JsonIgnore
    private Exception exception;

    public GenericResp()
    {
       this.message= Constant.AppEnum.MSG_SUCCESS.getValue();
    }


    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString()
    {
        return message;
    }
}
