package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class DepositChargeListModel {
    private String requestDate;
    private String depositDate;
    private String name;
    private String money;
    private String accountNumber;
    private String state;

    public DepositChargeListModel(String requestDate, String depositDate, String name, String money, String accountNumber, String state) {
        this.requestDate = requestDate;
        this.depositDate = depositDate;
        this.name = name;
        this.money = money;
        this.accountNumber = accountNumber;
        this.state = state;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
