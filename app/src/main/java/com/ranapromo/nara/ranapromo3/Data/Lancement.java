package com.ranapromo.nara.ranapromo3.Data;

import java.io.Serializable;
import java.util.Date;

public class Lancement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private Integer lanPid;
	private Integer marPid;
 	private String marNom;
	private String lanTitre;
	private String lanDes;
	private Date lanDate;
	private Date lastupdate;

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	private boolean favorite;
	
	public String getLanTitre() {
		return lanTitre;
	}
	public String getLanDes() {
		return lanDes;
	}
	public Date getLanDate() {
		return lanDate;
	}
	public Date getLastupdate() {
		return lastupdate;
	}
	
	public void setLanTitre(String lanTitre) {
		
		this.lanTitre = lanTitre;
	}
	public void setLanDes(String lanDes) {
		
		this.lanDes = lanDes;
	}
	public void setLanDate(Date lanDate) {
		
		this.lanDate = lanDate;
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


	public Integer getLanPid() {
		return lanPid;
	}

	public void setLanPid(Integer lanPid) {
		this.lanPid = lanPid;
	}
	public String getMarNom() {
		return marNom;
	}

	public void setMarNom(String marNom) {
		this.marNom = marNom;
	}
}
