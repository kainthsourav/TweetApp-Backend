package com.example.model;

public class Credentials {

    private String loginId;
    private String password;
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String passwordKey) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Credentials [loginId=" + loginId + ", password=" + password + "]";
    }


}
