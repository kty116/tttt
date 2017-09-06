package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class DepositRefundListModel {
    private String requestDate;
    private String bankName;
    private String name;
    private String accountNumber;
    private String refundMoney;
    private String state;

    public DepositRefundListModel(String requestDate, String bankName, String name, String accountNumber, String refundMoney, String state) {
        this.requestDate = requestDate;
        this.bankName = bankName;
        this.name = name;
        this.accountNumber = accountNumber;
        this.refundMoney = refundMoney;
        this.state = state;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
