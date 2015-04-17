package vn.smartdev.javatrainingpractice.springmvcpractice.util;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1547401644057895028L;

    private String field;
    private boolean isSuccess = true;
    private String messageInfo;
    private T body;

    public BaseResponse() {

    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }



}
