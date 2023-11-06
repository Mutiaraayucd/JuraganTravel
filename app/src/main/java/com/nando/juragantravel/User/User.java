package com.nando.juragantravel.User;

public class User {
    private int userId;
    private String userFullname;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userNohp;
    private String userGender;
    private String userAgama;
    private String userTgllahir;
    private String userAlamat;

    public User() {
    }

    public User(int userId, String userFullname, String userName, String userEmail, String userPassword, String userNohp, String userGender, String userAgama, String userTgllahir, String userAlamat) {
        this.userId = userId;
        this.userFullname = userFullname;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNohp = userNohp;
        this.userGender = userGender;
        this.userAgama = userAgama;
        this.userTgllahir = userTgllahir;
        this.userAlamat = userAlamat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNohp() {
        return userNohp;
    }

    public void setUserNohp(String userNohp) {
        this.userNohp = userNohp;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserAgama() {
        return userAgama;
    }

    public void setUserAgama(String userAgama) {
        this.userAgama = userAgama;
    }

    public String getUserTgllahir() {
        return userTgllahir;
    }

    public void setUserTgllahir(String userTgllahir) {
        this.userTgllahir = userTgllahir;
    }

    public String getUserAlamat() {
        return userAlamat;
    }

    public void setUserAlamat(String userAlamat) {
        this.userAlamat = userAlamat;
    }
}

