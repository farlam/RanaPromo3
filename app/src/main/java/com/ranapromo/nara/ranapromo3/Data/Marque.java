package com.ranapromo.nara.ranapromo3.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by NOO on 05/09/2015.
 */
public class Marque {

    private static final long serialVersionUID = 1L;
    private String marNom;
    private Date lastupdate;
    private Boolean favorite;
    private String marEmail;
    private String marPhone;
    private String marFax;
    private Integer marPid;

    public String getMarPhone() {
        return marPhone;
    }


    public void setMarPhone(String marPhone) {

        this.marPhone = marPhone;
    }


    public Boolean isFavorite() {
        return favorite;
    }


    public String getMarEmail() {
        return marEmail;
    }


    public String getMarFax() {
        return marFax;
    }


    public void setMarFax(String marFax) {
        this.marFax = marFax;
    }


    public void setMarEmail(String marEmail) {
        this.marEmail = marEmail;
    }


    public void setFavorite(Boolean isFavorite) {
        this.favorite = isFavorite;
    }


    public Marque() {
    }

    public Date getLastupdate() {
        return this.lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getMarNom() {
        return this.marNom;
    }

    public void setMarNom(String marNom) {
        this.marNom = marNom;
    }


    public Integer getMarPid() {
        return marPid;
    }


    public void setMarPid(Integer marPid) {
        this.marPid = marPid;
    }


    public List<Promotion> promotions;

}
