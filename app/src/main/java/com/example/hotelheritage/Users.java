package com.example.hotelheritage;

public class Users {

    private String name;
    private Integer conNo;
    private String dob;
    private String email;
    private String username;
    private String password;

    public Users() {
    }

    public Users(String name, Integer conNo, String dob, String email, String username, String password) {
        this.name = name;
        this.conNo = conNo;
        this.dob = dob;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConNo() {
        return conNo;
    }

    public void setConNo(Integer conNo) {
        this.conNo = conNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
