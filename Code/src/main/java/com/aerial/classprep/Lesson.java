package com.aerial.classprep;

/**
 * Created by User on 7/14/2017.
 */

public class Lesson {
    private String lid;
    private String sid;
    private String name;
    private String sName;
    private String day;

    public Lesson(){

    }

    public Lesson(String lid, String sid, String name,String sname,String day){

        this.setLid(lid);
        this.setSid(sid);
        this.setName(name);
        this.setsName(sname);
        this.setDay(day);
    }


    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
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

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
