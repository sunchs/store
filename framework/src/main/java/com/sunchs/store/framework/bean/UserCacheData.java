package com.sunchs.store.framework.bean;

public class UserCacheData {

    private int id;
    private int type;
    private String userName;
    private String name;
    private String token;
    private int hospitalId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Override
    public String toString() {
        return "UserCacheData{" +
                "id=" + id +
                ", type=" + type +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", hospitalId=" + hospitalId +
                '}';
    }
}


