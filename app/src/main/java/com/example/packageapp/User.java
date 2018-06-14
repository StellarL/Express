package com.example.packageapp;

/**
 * Created by 李馨 on 2018/6/14.
 */

public class User {

    private int _id;
    private String username;
    private String password;
    private String relName;
    private String idCard;
    private String idImg;

    public User() {
    }

    public User(int _id, String username, String password, String relName, String idCard, String idImg) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.relName = relName;
        this.idCard = idCard;
        this.idImg = idImg;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

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

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", relName='" + relName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", idImg='" + idImg + '\'' +
                '}';
    }
}
