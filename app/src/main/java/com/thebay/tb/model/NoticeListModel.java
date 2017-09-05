package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-08-28.
 */

public class NoticeListModel {
    private String number;
    private String date;
    private String state;
    private String title;

    public NoticeListModel(String number, String date, String state, String title) {
        this.number = number;
        this.date = date;
        this.state = state;
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NoticeListModel{" +
                "number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
