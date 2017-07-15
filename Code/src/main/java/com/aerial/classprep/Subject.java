package com.aerial.classprep;

/**
 * Created by User on 7/14/2017.
 */

public class Subject {
    private String sid;
    private String name;
    private String code;
    private String stDate;
    private String endDate;
    private String day;
    private int weeks;

    public Subject(){

    }

    public Subject(String sid,String name, String code, String stDate, String endDate, String day, int weeks) {
        this.setSid(sid);
        this.setName(name);
        this.setCode(code);
        this.setStDate(stDate);
        this.setEndDate(endDate);
        this.setDay(day);
        this.setWeeks(weeks);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }
}
