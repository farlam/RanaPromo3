package com.ranapromo.nara.ranapromo3.comman;

import java.util.Date;

public class Holder {

	
	private Object[] theObject;

	public Holder(Date lastUpdate, Object[] theObject) {
		super();
		
		this.theObject = theObject;
	}

	public Holder() {
		// TODO Auto-generated constructor stub
	}

	public Object[] getTheObject() {
		return theObject;
	}

	public void setTheObject(Object[] theObject) {

		this.theObject = theObject;
	}

}
