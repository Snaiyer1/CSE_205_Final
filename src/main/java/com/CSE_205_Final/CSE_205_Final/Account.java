package com.CSE_205_Final.CSE_205_Final;

public class Account {
    private int num;
    private String type;
    private Double balance;

    public Account(){}

    public Account(String type) {
        this.type = type;
    }

    public int getNumber() {
        return num;
    }

    public String getType() {
        return type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void deposit(Float amount) {}

    public Boolean withdraw (Float amount) {

        return true;

    }

}
