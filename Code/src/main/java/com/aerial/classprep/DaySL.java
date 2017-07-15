package com.aerial.classprep;

/**
 * Created by User on 7/15/2017.
 */

public class DaySL {
    private String sub;
    private String les;

    public DaySL(){

    }

    public DaySL(String sub,String les){
        this.setLes(les);
        this.setSub(sub);
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getLes() {
        return les;
    }

    public void setLes(String les) {
        this.les = les;
    }
}
