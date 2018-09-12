package com.msdeneme.newms.controller.core.response;

import com.msdeneme.newms.controller.core.BaseRestResponse;

public class ItemStatusResponse extends BaseRestResponse {

    private String status;
    private String value;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
