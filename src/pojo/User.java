package pojo;

import org.json.JSONObject;

public class User {
    private String token;
    JSONObject userInfo;
    boolean isLogued;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(JSONObject userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isIsLogued() {
        return isLogued;
    }

    public void setIsLogued(boolean isLogued) {
        this.isLogued = isLogued;
    }

    public User(String token, JSONObject userInfo, boolean isLogued) {
        this.token = token;
        this.userInfo = userInfo;
        this.isLogued = isLogued;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "user{" + "token=" + token + ", userInfo=" + userInfo + ", isLogued=" + isLogued + '}';
    }
    
    
}
