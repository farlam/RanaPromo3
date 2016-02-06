package com.ranapromo.nara.ranapromo3.comman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.ranapromo.nara.ranapromo3.Data.Lancement;
import com.ranapromo.nara.ranapromo3.Data.LogData;
import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.Data.MarqueDataHolder;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.Entity.PromotionCDT;
import com.ranapromo.nara.ranapromo3.Data.Store;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.fmk.impl.DataProviderImpl3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by smati on 31/07/2015.
 */
public class DataBaseHelper {


    MyPromo myPromo;
    private static final String DATA_BASE_NAME = "mydabase1";
    private static final String FACTORY_TABLE = "factory";
    private static final String FACTORY_ID = "id";
    private static final String FACTORY_NAME = "name";
    private static final String FACTORY_IMG_DIR = "dir";
    private static final String FACTORY_ISMYFAV = "fav";

    private static final String PROM0_TABLE = "Promo";
    private static final String PROM0_TABLE_ID = "id";
    private static final String PROM0_DES = "des";
    private static final String PROM0_TITLE = "titre";
    private static final String PROM0_REF = "ref";
    private static final String PROM0_TAUX = "taux";
    private static final String PROM0_PRIX = "Prix";
    private static final String PROM0_DATE_DEBUT = "dateDebut";
    private static final String PROM0_DATE_FIN = "dateFin";
    private static final String PROM0_FAV = "favorite";
    private static final String PROM0_MAR_ID = "mar_id";
    private static final String PROM0_MAR_NOM = "marque";
    private static final String PROM0_VIEW = "view";

    private static final String STORE_TABLE="Store";
    private static final String STORE_ID="id";
    private static final String STORE_LIEU="lieu";
    private static final String STORE_NAME="name";
    private static final String STORE_MARQUE="marque";
    private static final String  STORE_LAT="lat";
    private static final String  STORE_LON="lon";


    private static final String LANCEMENT_TABLE ="lancement";
    private static final String LAN_ID = "lan_pid";
    private static final String LAN_MARQUE = "lan_marque";
    private static final String LAN_TITLE = "lan_title";
    private static final String LAN_DES = "lan_des";
    private static final String LAN_DATE = "lan_date";
    private static final String LAN_FAV = "favorite";

    private static final String LOG_ACTIVITY_TABLE ="log";
    private static final String LOG_ID ="id";
    private static final String LOG_ACTION = "logaction";
    private static final String LOG_TABLE = "logtable";
    private static final String LOG_VALUE = "logvalue";



    private SQLiteDatabase ourDataBase;
    Context context = null;




    public static class MyPromo extends SQLiteOpenHelper {




        public MyPromo(Context context) {
            super(context, DATA_BASE_NAME, null, 5);
            Util.logDebug("Data Base created");
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + LOG_ACTIVITY_TABLE + " (" +
                    LOG_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    LOG_ACTION + " TEXT NOT NULL, " +
                    LOG_TABLE + " TEXT NOT NULL, " +
                    LOG_VALUE + " INTEGER NOT NULL);");

            Util.logDebug("Table " + LOG_ACTIVITY_TABLE + " created");

            db.execSQL("CREATE TABLE " + FACTORY_TABLE + " (" +
                    FACTORY_ID + " TEXT NOT NULL UNIQUE, " +
                    FACTORY_NAME + " TEXT NOT NULL, " +
                    FACTORY_ISMYFAV + " INTEGER default 0, " +
                    FACTORY_IMG_DIR + " TEXT);");
            
            Util.logDebug("Table " + FACTORY_TABLE + " created");

            db.execSQL("CREATE TABLE " + PROM0_TABLE + " (" +
                    PROM0_TABLE_ID + " INTEGER NOT NULL UNIQUE, " +
                    PROM0_MAR_ID + " INTEGER , " +
                    PROM0_MAR_NOM + " TEXT , " +
                    PROM0_DES + " TEXT , " +
                    PROM0_TITLE + " TEXT , " +
                    PROM0_TAUX + " REAL, " +
                    PROM0_PRIX + " REAL, " +
                    PROM0_DATE_DEBUT + " INTEGER, " +
                    PROM0_DATE_FIN + " INTEGER, " +
                    PROM0_VIEW + " INTEGER default 0, " +
                    PROM0_FAV + " INTEGER default 0);");
            Util.logDebug("Table " + PROM0_TABLE + " created");
            
            
            db.execSQL("CREATE TABLE " + STORE_TABLE + " (" +
                    STORE_ID + " INTEGER PRIMARY KEY, " +
                    STORE_LIEU + " TEXT , " +
                    STORE_NAME + " TEXT, " +
                    STORE_MARQUE + " TEXT, " +
                    STORE_LAT + " REAL, " +
                    STORE_LON + " REAL);");

            Util.logDebug("Table " + STORE_TABLE + " created");

            db.execSQL("CREATE TABLE " + LANCEMENT_TABLE + " (" +
                    LAN_ID + " INTEGER PRIMARY KEY, " +
                    LAN_MARQUE + " TEXT NOT NULL, " +
                    LAN_TITLE + " TEXT, " +
                    LAN_DES + " TEXT, " +
                    LAN_FAV + " INTEGER default 0,"+
                    LAN_DATE + " INTEGER);");

            Util.logDebug("Table " + LANCEMENT_TABLE + " created");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Util.logDebug("Data Base Updated");
            db.execSQL("DROP TABLE IF EXISTS " + FACTORY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PROM0_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + STORE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + LANCEMENT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + LOG_ACTIVITY_TABLE);


            onCreate(db);
        }
    }

    public DataBaseHelper(Context context) {
        this.context = context;

    }

    public DataBaseHelper open() {
        myPromo = new MyPromo(context);
        ourDataBase = myPromo.getWritableDatabase();
        return this;
    }

    public void close() {
        myPromo.close();
    }


    public boolean logActivity(String action,String table,int value){

        boolean result = false;
        //LOG_ACTIVITY_TABLE
        ContentValues cv = new ContentValues();
        cv.put(LOG_ACTION, action);
        cv.put(LOG_TABLE, table);
        cv.put(LOG_VALUE, value);
        try {
            ourDataBase.insertOrThrow(LOG_ACTIVITY_TABLE, null, cv);
            Util.logDebug("insert log value action="+action+" table="+table+" value="+value+" into log table");
            return true;
        } catch (Exception e) {
            Util.logError("Error inserting log value action="+action+" table="+table+" error is "+e.getMessage());
            return false;
        }
    }


    public void createFactoryEntry(Marque[] marques) {
        ContentValues cv;
        String source = null;
        String des = null;

        for (Marque mr : marques) {
            cv = new ContentValues();
            cv.put(FACTORY_ID, mr.getMarPid());
            cv.put(FACTORY_NAME, mr.getMarNom());

            try {
                long a = ourDataBase.insertOrThrow(FACTORY_TABLE, null, cv);
                Util.logDebug("Marque with id = " + mr.getMarNom() + " inserted into database");


            } catch (Exception e) {
                Util.logDebug("error white adding row Marque to database");
                Util.logDebug("updating row instead " + mr.getMarPid() + "to database");
                ourDataBase.update(FACTORY_TABLE, cv, "id = ?", new String[]{mr.getMarPid().toString()});
            }

       /*     try {
                source = context.getString(R.string.server_base_url) + "/" + mr.getMarNom() + ".jpg";
                des = context.getCacheDir() + "/" + mr.getMarNom() + ".jpg";
                Util.logDebug("down load file " + source);
                Util.getBitmapFromURL(source, des);
            } catch (Exception e) {
                Util.logError("error white downlanding file " + source);
            }
       */ }
    }
    
    
    
    public boolean setFavoritMarque(String _codeMarq){
    	boolean result = false;
    	int val = 0;
    	try{
    		String[] colomus = new String[]{FACTORY_ISMYFAV};
            Cursor c = ourDataBase.query(FACTORY_TABLE, colomus, "name = "+"'"+_codeMarq+"'", null, null, null, null);
        
            int facIndex = c.getColumnIndex(FACTORY_ISMYFAV);
            c.moveToFirst();
            val = c.getInt(facIndex);
            if(val == 0) val = 1;
               else val = 0; 
    	
    	    ContentValues cv = new ContentValues();
    	    cv.put(FACTORY_ISMYFAV, val);
    	    
    	    ourDataBase.update(FACTORY_TABLE, cv, "name = ?", new String[]{_codeMarq});
    	    Util.logDebug("update favorite Marque " + _codeMarq + " to value" + val);
    	} catch(Exception e){
    		Util.logError("error white updating favorite Marque " + _codeMarq+" error is "+e.getMessage());
    	}
    	return val == 1;
    }


    public void createPromoEntry(Promotion[] proms) {
        ContentValues cv;
        for (Promotion mr : proms) {
            cv = new ContentValues();

            //cv.put(PROM0_TABLE_ID,mr.getId());
            cv.put(PROM0_TABLE_ID, mr.getProId());
            cv.put(PROM0_TITLE, mr.getProTitre());
            cv.put(PROM0_DES, mr.getProDes());
            cv.put(PROM0_PRIX, mr.getProPrix());
            cv.put(PROM0_TAUX, mr.getProTauxRed());
            cv.put(PROM0_DATE_DEBUT, mr.getProStartDate().getTime());
            cv.put(PROM0_DATE_FIN, mr.getProEndDate().getTime());
            //cv.put(PROM0_FAV, mr.isFavorite());
            cv.put(PROM0_MAR_ID, mr.getMarPid());
            cv.put(PROM0_MAR_NOM, mr.getMarNom());
            
            try {
                long a = ourDataBase.insertOrThrow(PROM0_TABLE, null, cv);
                Util.logDebug("Prom with id = " + mr.getProId() + " and marque " + mr.getMarNom() + " inserted into database value of result = " + a);
            } catch (Exception e) {
                Util.logError("error white adding row Promo "+mr.getProId()+ "to database taux egale "+mr.getProTauxRed()+" error is"+e);
                Util.logDebug("updating row instead " + mr.getProId() + "to database");
                //int a = 0;
                //cv.put(PROM0_VIEW, a);
                ourDataBase.update(PROM0_TABLE, cv, "id = ?", new String[]{mr.getProId().toString()});
            }
        }
    }


    public Marque[] getMarques() {
        List<Marque> marques = new ArrayList<Marque>();
        String[] colomus = new String[]{FACTORY_ID, FACTORY_NAME,FACTORY_ISMYFAV};
        Cursor c = ourDataBase.query(FACTORY_TABLE, colomus, null, null, null, null, null);
        int RowId = c.getColumnIndex(FACTORY_ID);
        int FocNamIdex = c.getColumnIndex(FACTORY_NAME);
        int facIndex = c.getColumnIndex(FACTORY_ISMYFAV);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Marque marque = new Marque();
            marque.setMarPid(Integer.valueOf(c.getString(RowId)));
            marque.setMarNom(c.getString(FocNamIdex));
            int val = c.getInt(facIndex);
            if(val == 0) marque.setFavorite(false);
            else marque.setFavorite(true);
            marques.add(marque);
        }
        return marques.toArray(new Marque[marques.size()]);
    }


    public Lancement[] getLancement() {
        List<Lancement> marques = new ArrayList<Lancement>();
        String[] colomus = new String[]{LAN_ID, LAN_MARQUE,LAN_TITLE,LAN_DES,LAN_DATE};
        Cursor c = ourDataBase.query(LANCEMENT_TABLE, colomus, null, null, null, null, null);
         for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Lancement lan = new Lancement();
            lan.setMarNom(c.getString(c.getColumnIndex(LAN_MARQUE)));
            lan.setLanTitre(c.getString(c.getColumnIndex(LAN_TITLE)));
            lan.setLanDes(c.getString(c.getColumnIndex(LAN_DES)));
            lan.setLanPid(c.getInt(c.getColumnIndex(LAN_ID)));
            marques.add(lan);
        }
        return marques.toArray(new Lancement[marques.size()]);
    }
    
    
  


    public Promotion[] getProms() {
        List<Promotion> proms = new ArrayList<Promotion>();
        String[] colomus = new String[]{PROM0_TABLE_ID, PROM0_DES, PROM0_PRIX,
                PROM0_TAUX, PROM0_DATE_DEBUT, PROM0_DATE_FIN, PROM0_MAR_ID,PROM0_MAR_NOM};

        Cursor c = ourDataBase.query(PROM0_TABLE, colomus, null, null, null, null, null);

        int rowId = c.getColumnIndex(PROM0_TABLE_ID);
        int proDes = c.getColumnIndex(PROM0_DES);
        int proPrix = c.getColumnIndex(PROM0_PRIX);
        int proTaux = c.getColumnIndex(PROM0_TAUX);
        int proDd = c.getColumnIndex(PROM0_DATE_DEBUT);
        int proDf = c.getColumnIndex(PROM0_DATE_FIN);
        int markDf = c.getColumnIndex(PROM0_MAR_ID);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Promotion prom = new Promotion();
            //prom.setP(c.getString(rowId));
            prom.setProTitre(c.getString(proDes));
            prom.setProPrix(c.getDouble(proDes));
            prom.setProTauxRed(c.getDouble(proTaux));
            prom.setMarPid(c.getInt(markDf));
            prom.setMarNom(c.getString(c.getColumnIndex(PROM0_MAR_NOM)));
            proms.add(prom);
        }
        return proms.toArray(new Promotion[proms.size()]);
    }
    

    public Store[] getAllStoreByMarque(String marque){
        List<Store> stores = new ArrayList<Store>();
        String[] colomus = new String[]{STORE_MARQUE, STORE_LIEU, STORE_NAME};
        Cursor c = ourDataBase.query(STORE_TABLE, colomus, STORE_MARQUE + " = ? ", new String[]{marque}, null, null, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Store sto = new Store();
            sto.setMarNom(c.getString(c.getColumnIndex(STORE_MARQUE)));
            sto.setStoLieu(c.getString(c.getColumnIndex(STORE_LIEU)));
            sto.setStoName(c.getString(c.getColumnIndex(STORE_NAME)));
            stores.add(sto);
        }
        return stores.toArray(new Store[stores.size()]);
    }



    public Promotion[] getFavNotViewedProms(String marque) {
        List<Promotion> proms = new ArrayList<Promotion>();
        String[] colomus = new String[]{PROM0_TABLE_ID,PROM0_TITLE, PROM0_DES, PROM0_PRIX,
                PROM0_TAUX, PROM0_DATE_DEBUT, PROM0_DATE_FIN, PROM0_MAR_ID};

        Cursor c = ourDataBase.query(PROM0_TABLE, colomus, PROM0_MAR_NOM + " = ? and " + PROM0_VIEW + " = 0 ", new String[]{marque}, null,null, null);
        int rowId = c.getColumnIndex(PROM0_TITLE);
        int proDes = c.getColumnIndex(PROM0_DES);
        int proPrix = c.getColumnIndex(PROM0_PRIX);
        int proTaux = c.getColumnIndex(PROM0_TAUX);
        int proDd = c.getColumnIndex(PROM0_DATE_DEBUT);
        int proDf = c.getColumnIndex(PROM0_DATE_FIN);
        int markDf = c.getColumnIndex(PROM0_MAR_ID);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Promotion prom = new Promotion();
            prom.setProId(c.getInt(c.getColumnIndex(PROM0_TABLE_ID)));
            prom.setProTitre(c.getString(rowId));
            prom.setProDes(c.getString(proDes));
            prom.setProPrix(c.getDouble(proPrix));
            prom.setProTauxRed(c.getDouble(proTaux));
            prom.setProStartDate(new Date(c.getLong(proDd)));
            prom.setProEndDate(new Date(c.getLong(proDf)));
            prom.setProTauxRed(c.getDouble(proTaux));
            //prom.setMarque(c.getString(markDf));
            proms.add(prom);
        }
        return proms.toArray(new Promotion[proms.size()]);
    }

	public com.ranapromo.nara.ranapromo3.Data.Marque[] getBestMarques() {
		    List<Marque> marques = new ArrayList<Marque>();
		    
	        String[] colomus = new String[]{FACTORY_ID, FACTORY_NAME,FACTORY_ISMYFAV};
	        
	        Cursor c = ourDataBase.query(FACTORY_TABLE, colomus, FACTORY_ISMYFAV + " = " + "'" + 1 + "'", null, null, null, null);
	        
	        int RowId = c.getColumnIndex(FACTORY_ID);
	        int FocNamIdex = c.getColumnIndex(FACTORY_NAME);
	        int facIndex = c.getColumnIndex(FACTORY_ISMYFAV);
	        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
	            Marque marque = new Marque();
	            //marque.setMarCod(c.getString(RowId));
	            marque.setMarNom(c.getString(FocNamIdex));
	            int val = c.getInt(facIndex);
	            if(val == 0) marque.setFavorite(false);
	            else marque.setFavorite(true);
	            marques.add(marque);
	        }
	        return marques.toArray(new Marque[marques.size()]);
	}
	
	
	public Cursor getBestMarques2() {
	    List<Marque> marques = new ArrayList<Marque>();
	    
        String[] colomus = new String[]{FACTORY_ID, FACTORY_NAME,FACTORY_ISMYFAV};
        
        String[] args = new String[]{"1"};
		
        String str="select * from ("+
         "select factory.name as name, 0 as val,  factory.id as _id from factory"+
         " where"+
         " factory.fav= 1"+ 
         " group by factory.name"+
         " union"+
         " select factory.name as marque, count(promo.marque) as val,  factory.id as _id from promo,factory"+
         " where"+
         " factory.id=promo.marque and"+
         " factory.fav= ? and promo.view = 0"+
         " group by promo.marque) group by name";
        

		Util.logDebug(str);
		Cursor c = ourDataBase.rawQuery(str, args);
		
		
		return c;
   }


    public MarqueDataHolder[] getBestMarques3() {
        List<MarqueDataHolder> proms = new ArrayList<MarqueDataHolder>();

        String[] colomus = new String[]{FACTORY_ID, FACTORY_NAME,FACTORY_ISMYFAV};

        String[] args = new String[]{"1"};

        String str="select * from ("+
                "select factory.name as name, 0 as val,  factory.id as idMarque from factory"+
                " where"+
                " factory.fav= 1"+
                " group by factory.name"+
                " union"+
                " select factory.name as marque, count(promo.marque) as val,  factory.id as idMarque from promo,factory"+
                " where"+
                " factory.id=promo.mar_id and"+
                " factory.fav= ? and promo.view = 0"+
                " group by promo.marque) group by name";


        Util.logDebug(str);
        Cursor c = ourDataBase.rawQuery(str , args );
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            MarqueDataHolder prom = new MarqueDataHolder();
            prom.setMarque(c.getString(c.getColumnIndex("name")));
            prom.setIdMarque(c.getInt(c.getColumnIndex("idMarque")));
            prom.setOccNumber(c.getInt(c.getColumnIndex("val")));
            proms.add(prom);
        }

        Util.logDebug("getAllPromo executed and returned "+proms.size()+ " elements");
        return proms.toArray(new MarqueDataHolder[proms.size()]);

    }
	
	public Promotion[] getAllPromo() {
	    List<Promotion> proms = new ArrayList<Promotion>();
	    
        String str = "select * from Promo";
		Cursor c = ourDataBase.rawQuery(str , null);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Promotion prom = new Promotion();
            prom.setProId(c.getInt(c.getColumnIndex(PROM0_TABLE_ID)));
            prom.setProTitre(c.getString(c.getColumnIndex(PROM0_TITLE)));
            prom.setProDes(c.getString(c.getColumnIndex(PROM0_DES)));
            prom.setProPrix(c.getDouble(c.getColumnIndex(PROM0_PRIX)));
            prom.setProTauxRed(c.getDouble(c.getColumnIndex(PROM0_TAUX)));
            prom.setFavorite(c.getInt(c.getColumnIndex(PROM0_FAV)) == 1);
            prom.setViewed(c.getInt(c.getColumnIndex(PROM0_VIEW)) == 1);
            prom.setProStartDate(new Date(c.getLong(c.getColumnIndex(PROM0_DATE_DEBUT))));
            prom.setProEndDate(new Date(c.getLong(c.getColumnIndex(PROM0_DATE_FIN))));
            prom.setMarPid(c.getInt(c.getColumnIndex(PROM0_MAR_ID)));
            prom.setMarNom(c.getString(c.getColumnIndex(PROM0_MAR_NOM)));
            proms.add(prom);
        }
	    Util.logDebug("getAllPromo executed and returned "+proms.size()+ " elements");
		return proms.toArray(new Promotion[proms.size()]);
   }

	public boolean setFavoritPromo(Integer refProm) {
		boolean result = false;
    	int val = 0;
    	try{
    		String[] colomus = new String[]{PROM0_FAV};
            Cursor c = ourDataBase.query(PROM0_TABLE, colomus, PROM0_TABLE_ID +" = "+refProm, null, null, null, null);
            
            int facIndex = c.getColumnIndex(PROM0_FAV);
            c.moveToFirst();
            val = c.getInt(facIndex);
            if(val == 0) val = 1;
               else val = 0; 
    	
    	    ContentValues cv = new ContentValues();
    	    cv.put(PROM0_FAV, val);
    	    ourDataBase.update(PROM0_TABLE, cv, "id = ?", new String[]{refProm.toString()});
    	    Util.logDebug("update favorite promotion  " + refProm+" to value "+val);
    	} catch(Exception e){
    		Util.logError("error white updating favorite Marque " + refProm+" error "+e.getMessage());
    	}
    	return val == 1;
	}


    public void createLancementEntry(Lancement[] lancements) {
        ContentValues cv;
        String source = null;
        String des = null;

        for (Lancement mr : lancements) {
            cv = new ContentValues();
            cv.put(LAN_ID, mr.getLanPid());
            cv.put(LAN_MARQUE, mr.getMarNom());
            cv.put(LAN_TITLE, mr.getLanTitre());
            cv.put(LAN_DES, mr.getLanDes());
            cv.put(LAN_DATE, mr.getLanDate().getTime());
            try {
                long a = ourDataBase.insertOrThrow(LANCEMENT_TABLE, null, cv);
                Util.logDebug("Lancement with id = " + mr.getMarPid() + " inserted into database");

            } catch (Exception e) {
                Util.logDebug("error white adding row Lancement to database");
                Util.logDebug("updating row instead " + mr.getMarPid() + "to database");
                int a = 0;
                ourDataBase.update(LANCEMENT_TABLE, cv, "lan_pid = ?", new String[]{mr.getLanPid().toString()});
            }
        }
    }
	
	
	public void createStoreEntry(Store[] stores) {
        ContentValues cv;
        for (Store store : stores) {
            cv = new ContentValues();
            cv.put(STORE_ID,store.getStoPid());
            cv.put(STORE_LIEU,store.getStoLieu());
            cv.put(STORE_MARQUE, store.getMarNom());
            cv.put(STORE_NAME, store.getStoName());
            cv.put(STORE_LAT, store.getStoLat());
            cv.put(STORE_LON, store.getStoLan());
            //cv.put(PROM0_MARQUE, mr.getMarque());
            try {
                long a = ourDataBase.insertOrThrow(STORE_TABLE, null, cv);
                Util.logDebug("store with id = " + store.getStoName() + " inserted into database");

            } catch (Exception e) {
                Util.logError("error white adding row store to database");
                Util.logDebug("updating row instead " + store.getStoName() + "to database");
                ourDataBase.update(STORE_TABLE, cv, "id = ?", new String[]{store.getStoPid().toString()});
            }
        }
    }

	public boolean setPromviewed(Integer proRef) {
         boolean result = false;
		 Util.logDebug("set promotion " + proRef + " to viewd");
		 ContentValues cv = new ContentValues();
 	     cv.put(PROM0_VIEW, 1);
         try {
             ourDataBase.update(PROM0_TABLE, cv, "id = ?", new String[]{proRef.toString()});
             Util.logDebug("update promotion " + proRef + " to value" + 1);
             result = true;
         }catch (Exception e){
             Util.logError("Error updating promotion to set it to viewed");
         }
         return result;

	}


    public LogData[] upDateLogActivities() {
        String str = "select * from log";
        List<LogData> proms = new ArrayList<LogData>();
        DataProviderImpl3 dataPro = new DataProviderImpl3(context.getString(R.string.server_url));
        Cursor c = ourDataBase.rawQuery(str , null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            LogData log = new LogData();
            log.setAction(c.getString(c.getColumnIndex(LOG_ACTION)));
            log.setValue(c.getInt(c.getColumnIndex(LOG_VALUE)));
            log.setTableName(c.getString(c.getColumnIndex(LOG_TABLE)));
            proms.add(log);
        }
        return proms.toArray(new LogData[proms.size()]);
    }


    public void deleteLogEntry(int id) {
        Util.logError("Delete enty with id ="+id);
    }


}





   
    
    



    
    
