package com.ranapromo.nara.ranapromo3.Entity;


import android.annotation.SuppressLint;

import com.ranapromo.nara.ranapromo3.Data.Promotion;

@SuppressLint("ParcelCreator")
public class PromotionCDT extends Promotion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  idMarque;

	public String getIdMarque() {
		return idMarque;
	}

	public void setIdMarque(String idMarque) {
		this.idMarque = idMarque;
	}

}
