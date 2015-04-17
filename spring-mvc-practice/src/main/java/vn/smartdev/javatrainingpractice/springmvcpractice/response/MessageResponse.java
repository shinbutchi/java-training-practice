package vn.smartdev.javatrainingpractice.springmvcpractice.response;

import vn.smartdev.javatrainingpractice.springmvcpractice.util.BaseResponse;

import java.io.Serializable;
import java.util.List;

public class MessageResponse<T> implements Serializable {
    private static final long serialVersionUID = 1547401644057895028L;
    List<BaseResponse> baseResponses;
    private boolean isSuccessResponse;

    public List<BaseResponse> getBaseResponses() {
        return baseResponses;
    }

    public void setBaseResponses(List<BaseResponse> baseResponses) {
        this.baseResponses = baseResponses;
    }

    public boolean isSuccessResponse() {
        return isSuccessResponse;
    }

    public void setIsSuccessResponse(boolean isSuccessResponse) {
        this.isSuccessResponse = isSuccessResponse;
    }
}
