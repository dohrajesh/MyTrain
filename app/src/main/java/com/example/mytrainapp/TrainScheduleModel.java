package com.example.mytrainapp;

public class TrainScheduleModel {

    String no,train, dep, arr;
//Constructors for static value ==>Data modal or pojo classes or bean classes
    public TrainScheduleModel(String no, String train, String dep, String arr) {
        this.no = no;
        this.train = train;
        this.dep = dep;
        this.arr = arr;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }
}
