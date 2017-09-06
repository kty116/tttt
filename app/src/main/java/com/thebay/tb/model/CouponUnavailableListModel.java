package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class CouponUnavailableListModel {
    private String number;
    private String couponName;
    private String discount;
    private String validity;  //유효기간
    private String dateIssued;  //발급일
    private String dateUse;  //사용일
    private String condition;  //상태

    public CouponUnavailableListModel(String number, String couponName, String discount, String validity, String dateIssued, String dateUse, String condition) {
        this.number = number;
        this.couponName = couponName;
        this.discount = discount;
        this.validity = validity;
        this.dateIssued = dateIssued;
        this.dateUse = dateUse;
        this.condition = condition;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDateUse() {
        return dateUse;
    }

    public void setDateUse(String dateUse) {
        this.dateUse = dateUse;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
