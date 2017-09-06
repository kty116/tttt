package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-09-04.
 */

public class PointListModel {
    private String section;
    private String point;
    private String date;

    public PointListModel(String section, String point, String date) {
        this.section = section;
        this.point = point;
        this.date = date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

