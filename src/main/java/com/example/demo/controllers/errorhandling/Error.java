package com.example.demo.controllers.errorhandling;

import java.util.Map;

public class Error {
    public Integer status;
    public String error;
    public String timeStamp;

    public Error(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.timeStamp = errorAttributes.get("timestamp").toString();
    }

}