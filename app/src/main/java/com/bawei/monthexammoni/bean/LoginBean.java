package com.bawei.monthexammoni.bean;

import java.io.Serializable;

/**
 * LoginBean（登录顶层Bean类）
 */
public class LoginBean implements Serializable {
    private UserInfoBean result;
    private String message;
    private String status;
    public UserInfoBean getResult() {
        return result;
    }
    public void setResult(UserInfoBean result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
