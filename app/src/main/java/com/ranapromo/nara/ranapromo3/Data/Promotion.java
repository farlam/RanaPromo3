package com.ranapromo.nara.ranapromo3.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by NOO on 05/09/2015.
 */
public class Promotion implements Parcelable{

    static final int NOM_MAR=250;
    private static final int PRO_TITRE = 300;
    public int image_ID;
    private Integer proId;
    private String marNom;
    private String proTitre;
    private Double proPrix;
    private Double proTauxRed;
    private Date proStartDate;
    private Date proEndDate;
    private String proDes;
    private boolean favorite;
    private boolean viewed;
    private Double newPrice;
    private Date lastupdate;
    private Integer marPid;
    private Date proStartTime;
    private Integer proCibAgeStart;
    private Integer proCibAgeEnd;
    private String proCibAgeSexe;
    private String proCibLocation;
    private Date timeLeft;


    public String getMarNom() {
        return marNom;
    }

    public void setMarNom(String marNom) {
        this.marNom = marNom;
    }
    public Promotion(Integer proId, Double proPrix, Double newPrice, Double proPrix1, String proDes,
                     Double proTauxRed, Date timeLeft, String proDes1) {
    }

    public Promotion() {
    }
    public Promotion(Parcel in){
        this.setProId(in.readInt());
        this.setProPrix(in.readDouble());
        this.setMarNom(in.readString());
        this.setProTitre(in.readString());
        this.setProTauxRed(in.readDouble());
        this.setProDes(in.readString());
        this.setProEndDate(new Date(in.readLong()));
    }




    public Promotion(Integer proId, Double proPrix, String marqueName, String proTitre,
                        Double proTauxRed, String proDes, boolean favorite,Date proEndDate) {
        this.proId = proId;
        this.proPrix = proPrix;
        this.marNom = marqueName;
        this.proTitre = proTitre;
        this.proTauxRed = proTauxRed;
        this.proDes = proDes;
        this.favorite = favorite;
        this.proEndDate = proEndDate;
    }


    public Date getTimeLeft() {
        return proEndDate;
    }

    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = proEndDate;
    }

    public Double getNewPrice() {
        return proPrix-proPrix*proTauxRed/100;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Integer getMarPid() {
        return marPid;
    }

    public void setMarPid(Integer marPid) {
        this.marPid = marPid;
    }

    public String getProDes() {
        return proDes;
    }

    public void setProDes(String proDes) {
        this.proDes = proDes;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {

        this.favorite = favorite;
    }

    public Date getLastupdate() {
        return this.lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Date getProEndDate() {
        return this.proEndDate;
    }

    public void setProEndDate(Date proEndDate) {
        this.proEndDate = proEndDate;
    }

    public Double getProPrix() {
        return this.proPrix;
    }

    public void setProPrix(Double proPrix) {
        this.proPrix = proPrix;
    }

    public Date getProStartDate() {
        return this.proStartDate;
    }

    public void setProStartDate(Date proStartDate) {
        this.proStartDate = proStartDate;
    }

    public Double getProTauxRed() {
        return this.proTauxRed;
    }

    public void setProTauxRed(Double proTauxRed) {
        this.proTauxRed = proTauxRed;
    }

    public String getProTitre() {
        return this.proTitre;
    }

    public void setProTitre(String proTitre) {
        this.proTitre = proTitre;
    }

    public Date getProStartTime() {
        return proStartTime;
    }

    public void setProStartTime(Date proStartTime) {

        this.proStartTime = proStartTime;
    }

    public Integer getProCibAgeStart() {
        return proCibAgeStart;
    }

    public void setProCibAgeStart(Integer proCibAgeStart) {

        this.proCibAgeStart = proCibAgeStart;
    }


    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public Integer getProCibAgeEnd() {
        return proCibAgeEnd;
    }

    public void setProCibAgeEnd(Integer proCibAgeEnd) {
        this.proCibAgeEnd = proCibAgeEnd;
    }

    public String getProCibAgeSexe() {
        return proCibAgeSexe;
    }

    public void setProCibAgeSexe(String proCibAgeSexe) {
        this.proCibAgeSexe = proCibAgeSexe;
    }

    public String getProCibLocation() {
        return proCibLocation;
    }

    public void setProCibLocation(String proCibLocation) {

        this.proCibLocation = proCibLocation;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proPid) {
        this.proId = proPid;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        /*out.writeStringArray(new String[]{String.valueOf(this.proId),
                                            this.proPrix.toString(),
                                            this.newPrice.toString(),
                                            this.marPid.toString(),
                                            this.proTitre,
                                            this.proTauxRed.toString(),
                                            this.timeLeft.toString(),
                                            this.proDes,
                                            String.valueOf(this.favorite) });*/


      // boolean favorite
        out.writeInt(this.getProId());
        out.writeDouble(this.getProPrix());
        out.writeString(this.getMarNom());
        out.writeString(this.getProTitre());
        out.writeDouble(this.getProTauxRed());
        out.writeString(this.getProDes());
        out.writeLong(this.getProEndDate().getTime());
    }


    public static final Parcelable.Creator<Promotion> CREATOR
            = new Parcelable.Creator<Promotion>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
}
