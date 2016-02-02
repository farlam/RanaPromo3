package com.ranapromo.nara.ranapromo3.Data;
import java.io.Serializable;


import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;


/**
 * The persistent class for the store database table.
 *
 */

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stoPid;
    private Float stoLan;
    private Float stoLat;
    private String stoName;
    private String marNom;
    private Integer marPid;
    private Date lastupdate;
    private Date stoDateStart;
    private Date stoDateEnd;
    private String stoWordDay;
    private String stoLieu;


    public String getMarNom() {
        return marNom;
    }

    public void setMarNom(String marNom) {
        this.marNom = marNom;
    }

    public Integer getStoPid() {
        return stoPid;
    }

    public void setStoPid(Integer stoPid) {
        this.stoPid = stoPid;
    }



    public String getStoLieu() {
        return stoLieu;
    }

    public void setStoLieu(String stoLieu) {
        this.stoLieu = stoLieu;
    }

    public Store() {
    }






    public Float getStoLan() {
        return this.stoLan;
    }

    public void setStoLan(Float stoLan) {
        this.stoLan = stoLan;
    }



    public Float getStoLat() {
        return this.stoLat;
    }

    public void setStoLat(Float stoLat) {
        this.stoLat = stoLat;
    }



    public String getStoName() {
        return this.stoName;
    }

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }




    public Date getLastupdate() {
        return lastupdate;
    }


    public void setLastupdate(Date lastupdate) {

        this.lastupdate = lastupdate;
    }


    public Integer getMarPid() {
        return marPid;
    }


    public void setMarPid(Integer marPid) {
        this.marPid = marPid;
    }


    public Date getStoDateStart() {
        return stoDateStart;
    }


    public void setStoDateStart(Date stoDateStart) {

        this.stoDateStart = stoDateStart;
    }


    public Date getStoDateEnd() {
        return stoDateEnd;
    }


    public void setStoDateEnd(Date stoDateEnd) {
        this.stoDateEnd = stoDateEnd;
    }


    public String getStoWordDay() {
        return stoWordDay;
    }


    public void setStoWordDay(String stoWordDay) {
        this.stoWordDay = stoWordDay;
    }
}