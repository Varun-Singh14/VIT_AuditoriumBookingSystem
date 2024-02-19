package com.helloworld.vitauditoriumbookingsys.Model;

public class RoomE {
    private String regNo;
    private String name;
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private String date;
    private String status;


    public RoomE() {
    }

    public RoomE(String regNo, String name, String title, String description, String startTime, String endTime, String status) {
        this.regNo = regNo;
        this.name = name;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getStatus() {return  status;}

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}