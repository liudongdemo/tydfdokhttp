package com.tydfd.tydfdokhttp.http;

/**
 * @Classname UserBean
 * @Description TODO
 * @Date 2019/7/11 15:07
 * @Created by liudo
 * @Author by liudo
 */
public class UserBean {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
