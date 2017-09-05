package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-08-28.
 */

public class InquiryListModel {
    private String number;
    private String inquiryState;
    private String rippleCount;
    private String date;
    private String state;
    private String title;

    public InquiryListModel(String number, String inquiryState, String rippleCount, String date, String state, String title) {
        this.number = number;
        this.inquiryState = inquiryState;
        this.rippleCount = rippleCount;
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

    public String getInquiryState() {
        return inquiryState;
    }

    public void setInquiryState(String inquiryState) {
        this.inquiryState = inquiryState;
    }

    public String getRippleCount() {
        return rippleCount;
    }

    public void setRippleCount(String rippleCount) {
        this.rippleCount = rippleCount;
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
        return "InquiryListModel{" +
                "number='" + number + '\'' +
                ", inquiryState='" + inquiryState + '\'' +
                ", rippleCount='" + rippleCount + '\'' +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
