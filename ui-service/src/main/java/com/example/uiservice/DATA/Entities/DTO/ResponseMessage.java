package com.example.uiservice.DATA.Entities.DTO;

public class ResponseMessage {
    private String result;
    private String reason;

    public ResponseMessage() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
