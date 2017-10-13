package com.touyuanren.perfectplay.bean.requestbean;

/**
 * Created by Liang on 2017/10/13 0013.
 */

public class LoginRequestBean {
    private String email;
    private String password;

    public LoginRequestBean() {

    }

    public LoginRequestBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
