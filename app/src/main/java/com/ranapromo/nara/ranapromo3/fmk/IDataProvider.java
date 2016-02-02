package com.ranapromo.nara.ranapromo3.fmk;

import java.util.Date;


import com.ranapromo.nara.ranapromo3.comman.Holder;


public interface IDataProvider {
	
	Holder getMarque();
	Holder getMarqueByDate(Date date);
	Holder getAllPromo();
	Holder getAllLancement();
	Holder getLancementByDate(Date date);
	Holder getPromoByDate(Date date);
	Holder getStoreByDate(Date date);
	Holder getAllStore();

	//public boolean createUser(User theUser);
	public boolean isValidUser(String id, String password);
	public boolean setFavorite(String userId, String refPromo);
}
