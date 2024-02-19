package com.helloworld.vitauditoriumbookingsys.Model;

public class Admin {
    private String name;
    private String phoneNumber;
    private String password;
    private String adminID;

    public Admin(){
    }

    public Admin(String name, String phoneNumber, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegNo() {
        return adminID;
    }

    public void setAdminID(String regNo) {
        this.adminID = regNo;
    }

}
