package com.example.mytrainapp;

public class TicketDetailModel {

    String slNo, passenger, age, berth;

    public TicketDetailModel(String slNo, String passenger, String age, String berth) {
        this.slNo = slNo;
        this.passenger = passenger;
        this.age = age;
        this.berth = berth;
    }

    public String getSlNo() {
        return slNo;
    }

    public void setSlNo(String slNo) {
        this.slNo = slNo;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBerth() {
        return berth;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }
}
