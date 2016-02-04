package com.ranapromo.nara.ranapromo3.fmk;

import java.util.Date;


import com.ranapromo.nara.ranapromo3.comman.Holder;


public interface IDataProvider {
	
	Holder getMarque() throws Exception;
	Holder getMarqueByDate(Date date) throws Exception;
	Holder getAllPromo() throws Exception;
	Holder getAllLancement() throws Exception;
	Holder getLancementByDate(Date date) throws Exception;
	Holder getPromoByDate(Date date) throws Exception;
	Holder getStoreByDate(Date date) throws Exception;
	Holder getAllStore() throws Exception;
	boolean setViewedPromotion(int promId) throws Exception;

	//public boolean createUser(User theUser);
	public boolean isValidUser(String id, String password);
	public boolean setFavorite(String userId, String refPromo);
}
