package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-08-28.
 */

public class MessageListModel {
    private String number;
    private String date;
    private String title;

    public MessageListModel(String number, String date, String title) {
        this.number = number;
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MessageListModel{" +
                "number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
