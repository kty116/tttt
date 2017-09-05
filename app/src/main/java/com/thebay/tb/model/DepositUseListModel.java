package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class DepositUseListModel {
    private String useDate;
    private String deposit;
    private String use;
    private String content;
    private String balance;

    public DepositUseListModel(String useDate, String deposit, String use, String content, String balance) {
        this.useDate = useDate;
        this.deposit = deposit;
        this.use = use;
        this.content = content;
        this.balance = balance;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
