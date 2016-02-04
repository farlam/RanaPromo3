package com.ranapromo.nara.ranapromo3.fmk.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.ranapromo.nara.ranapromo3.Data.Lancement;
import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.Data.Store;
import com.ranapromo.nara.ranapromo3.comman.Holder;
import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.fmk.HttpManager;
import com.ranapromo.nara.ranapromo3.fmk.IDataProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataProviderImpl3 implements IDataProvider {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String url="http://alsof.ml/";
	public DataProviderImpl3(String url){
		this.url = url;
	}
	
	@Override
	public Holder getMarque() throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
		String marQueContent = HttpManager.GetData(url + "getAllMarque.php?format=json");
		System.out.println(marQueContent);
		try {
			List<Object> marques = parceJSON(marQueContent,"Marque",
					                        new String[] {"MAR_PID","MAR_NOM","MAR_EMAIL","MAR_PHONE","MAR_FAX","LASTUPDATE"},
					                        new Class[] {Integer.class,String.class,String.class,String.class,String.class,Date.class});
			
			/*List<Object> marques = parceJSON(storeContent,"Store",
                    new String[] {"STO_NAME"},
                    new Class[] {String.class});*/

			result = new Holder();
			
			
			result.setTheObject(marques.toArray(new Marque[marques.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private List<Object> parceJSON(String content,String objectName,
			                       String[] fieldNames,
			                       Class[] types) throws Exception {
		try {
			List<Object> objectList = new ArrayList<Object>();
			JSONObject json = new JSONObject(content);
			Util.logDebug(content);
			JSONArray ar = json.getJSONArray(objectName + "s");
			String className = "com.ranapromo.nara.ranapromo3.Data" + "." + objectName;

			for (int i = 0; i < ar.length(); i++) {
				Object myObject = Class.forName(className).newInstance();
				JSONObject obj = ar.getJSONObject(i);
				JSONObject obj2 = obj.getJSONObject(objectName);
				for (int j = 0; j < fieldNames.length; j++) {
					String value = obj2.getString(fieldNames[j]);
					if(value != null) {
                     	Util.setProperty(myObject, fieldNames[j], types[j], value);
					}
				}
				objectList.add(myObject);
			}
			return objectList;
		}
		catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Holder getMarqueByDate(Date date) throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
        String strDate=null;
        String content = HttpManager.GetData(url+"getAllMarque.php?format=json");
		
		if(date != null){
			try {
				strDate="&date="+URLEncoder.encode(format.format(date), "UTF-8");
				content = HttpManager.GetData(url+"getAllMarque.php?format=json"+strDate);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//String content = HttpManager.GetData("http://alsof.ml/allmarque.xml");
		
		try {
			List<Object> marques = parceJSON(content,"Marque",
                    new String[] {"MAR_PID","MAR_NOM","MAR_EMAIL","MAR_PHONE","MAR_FAX","LASTUPDATE"},
                    new Class[] {Integer.class,String.class,String.class,String.class,String.class,Date.class});
		
			result = new Holder();
			result.setTheObject(marques.toArray(new Marque[marques.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Holder getAllPromo() throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
		String content = HttpManager.GetData(url+"getAllPromo.php?format=json");
		Util.logDebug(content);
		
		try {
			List<Object> promos = parceJSON(content,"Promotion",
					new String[] {"PRO_ID","MAR_PID","MAR_NOM","PRO_TITRE","PRO_DES","PRO_TAUX_RED",
							"PRO_PRIX","PRO_START_DATE","PRO_END_DATE",
							"PRO_START_TIME","PRO_CIB_AGE_START","PRO_CIB_AGE_END",
							"PRO_CIB_AGE_SEXE","PRO_CIB_LOCATION","LASTUPDATE"},
					new Class[] {Integer.class,Integer.class,String.class,String.class,String.class,Double.class,
							Double.class,Date.class,Date.class,
							Date.class,Integer.class,Integer.class,
							String.class,String.class,Date.class});

			result = new Holder();
			result.setTheObject(promos.toArray(new Promotion[promos.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.logError(e.getMessage());
		}
	return result;
	}

	

	@Override
	public Holder getPromoByDate(Date date) throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
        String strDate=null;
        String content = HttpManager.GetData(url+"getAllPromo.php?format=json");
		if(date != null){
			try {
				strDate="&date="+URLEncoder.encode(format.format(date), "UTF-8");
				content = HttpManager.GetData(url+"getAllPromo.php?format=json"+strDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.logError("Impossible de récuperer les promotions  du servuer "+url);
			}
		}
		//String content = HttpManager.GetData(url+"getAllPromo.php?format=json"+strDate);
		//System.out.println(content);
		
		try {
			List<Object> promos = parceJSON(content,"Promotion",
					new String[] {"PRO_ID","MAR_PID","MAR_NOM","PRO_TITRE","PRO_DES","PRO_TAUX_RED",
							"PRO_PRIX","PRO_START_DATE","PRO_END_DATE",
							"PRO_START_TIME","PRO_CIB_AGE_START","PRO_CIB_AGE_END",
							"PRO_CIB_AGE_SEXE","PRO_CIB_LOCATION","LASTUPDATE"},
					new Class[] {Integer.class,Integer.class,String.class,String.class,String.class,Double.class,
							Double.class,Date.class,Date.class,
							Date.class,Integer.class,Integer.class,
							String.class,String.class,Date.class});

			result = new Holder();
			result.setTheObject(promos.toArray(new Promotion[promos.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return result;
	}

	@Override
	public Holder getStoreByDate(Date date) throws Exception {
		Holder result = null;
		String strDate=null;
		String content = HttpManager.GetData(url+"getAllStore.php?format=json");
		
		if(date != null){
			strDate="&date="+format.format(date);
			content = HttpManager.GetData(url+"getAllStore.php?format=json"+strDate);
			
		}
		//http://alsof.ml/allmarque.xml
		
		//String content = HttpManager.GetData("http://alsof.ml/allmarque.xml");
		
		try {
			List<Object> stores  = parceJSON(content,"Store",
                    new String[] {"MAR_PID","STO_NAME","STO_LAT","STO_LAN",
					              "STO_DATE_START","STO_DATE_END","STO_WORD_DAY","LASTUPDATE"},
                    new Class[]  {Integer.class,String.class,Float.class,Float.class,
					              Date.class,Date.class,String.class,Date.class});
			result = new Holder();
			result.setTheObject(stores.toArray(new Store[stores.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Holder getAllStore() throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
		String content = HttpManager.GetData(url+"getAllStore.php?user=2&format=json");
		//String content = HttpManager.GetData("http://alsof.ml/allmarque.xml");
		
		try {
			List<Object> stores  = parceJSON(content,"Store",
                    new String[] {"STO_LIEU","STO_PID","MAR_NOM","MAR_PID","STO_NAME","STO_LAT","STO_LAN",
					              "STO_DATE_START","STO_DATE_END","LASTUPDATE"},
                    new Class[]  {String.class,Integer.class,String.class,Integer.class,String.class,Float.class,Float.class,
					              Date.class,Date.class,Date.class});
			result = new Holder();
			result.setTheObject(stores.toArray(new Store[stores.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean setViewedPromotion(int promId) throws Exception {
		boolean result = false;
			String content = HttpManager.GetData(url+"setViewedProm.php?promoId="+promId);
			Util.logDebug("Promotion " +promId+" updated in server to viewed");
			return true;
	}


	@Override
	public boolean isValidUser(String id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setFavorite(String userId, String refPromo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Holder getAllLancement() throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
		String content = HttpManager.GetData(url+"getAllLancement.php?user=2&format=json");
		//String content = HttpManager.GetData("http://alsof.ml/allmarque.xml");
		
		try {
			List<Object> lamncements  = parceJSON(content,"Lancement",
                    new String[] {"LAN_PID","MAR_NOM","MAR_PID","LAN_TITRE","LAN_DES","LAN_DATE","LASTUPDATE"},
                    new Class[]  {Integer.class,String.class,Integer.class,String.class,String.class,Date.class,Date.class});
			result = new Holder();
			result.setTheObject(lamncements.toArray(new Lancement[lamncements.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Holder getLancementByDate(Date date) throws Exception {
		Holder result = null;
		//http://alsof.ml/allmarque.xml
        String strDate=null;
		if(date != null){
			try {
				strDate="&date="+URLEncoder.encode(format.format(date), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String content = HttpManager.GetData(url+"getAllLancement.php?format=json"+strDate);
		//String content = HttpManager.GetData("http://alsof.ml/allmarque.xml");
		
		try {
			List<Object> lamncements  = parceJSON(content,"Lancement",
					new String[] {"LAN_PID","MAR_NOM","MAR_PID","LAN_TITRE","LAN_DES","LAN_DATE","LASTUPDATE"},
					new Class[]  {Integer.class,String.class,Integer.class,String.class,String.class,Date.class,Date.class});		result = new Holder();
			result.setTheObject(lamncements.toArray(new Marque[lamncements.size()]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
