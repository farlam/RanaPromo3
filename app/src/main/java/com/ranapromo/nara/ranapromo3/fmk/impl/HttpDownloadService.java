package com.ranapromo.nara.ranapromo3.fmk.impl;

import java.io.File;

import java.util.Arrays;
import java.util.Date;
import android.content.Context;

import com.ranapromo.nara.ranapromo3.Data.Lancement;
import com.ranapromo.nara.ranapromo3.Data.Marque;
import com.ranapromo.nara.ranapromo3.Data.Promotion;
import com.ranapromo.nara.ranapromo3.Data.Store;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Holder;
import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.fmk.IDateService;
import com.ranapromo.nara.ranapromo3.fmk.IDownloadHandler;
import com.ranapromo.nara.ranapromo3.fmk.impl.DataProviderImpl3;
import com.ranapromo.nara.ranapromo3.fmk.impl.DateServiceImpl;

//import com.entities.Store;
public class HttpDownloadService implements IDownloadHandler {
    //private static final String FILE_NAME2 = "http://192.168.1.10:8080/promo.bin";

    private Marque[] marque;
    private static String NAMESPACE = "http://ws.com/";
    private static final String WS_METHOD_NAME = "getMarques";
    private static final String WS_METHOD_NAME_PROM = "getAllPromo";

    public HttpDownloadService() {

    }

    @Override
    public void downLoad(Context ctx) {
        Util.logDebug("Chargement du fichier vie Http Service....");
        try {
            downloadFile(ctx);
        } catch (Exception e) {
            Util.logError("Cannot connect to the serveur "+e.getMessage());
        }
        Util.logDebug("fin du chargement du fichier vie Http Service....");
    }

    public void downloadFile(Context ctx) throws Exception {
        DataBaseHelper da = new DataBaseHelper(ctx);
        da.open();
        DataProviderImpl3 dataPro = new DataProviderImpl3(ctx.getString(R.string.server_url));
        //DataProdiverImpl dataPro = new DataProdiverImpl(ctx.getString(R.string.server_url));
        //get the cache directory

        File theDir = ctx.getCacheDir();

        File myFile = new File(theDir, "promotiondate.bin");
        Util.logDebug("create service date" + myFile.getAbsolutePath());
        IDateService serDate = new DateServiceImpl(myFile.getAbsolutePath());
        Date date = serDate.getLastUpdateDate();
        
        Util.logDebug("Call Promotion database v3");
        //Holder h2 = dataPro.getPromoByDate(date);
        Holder h2 = dataPro.getAllPromo();
        Promotion[] proms = Arrays.copyOf(h2.getTheObject(), h2.getTheObject().length, Promotion[].class);

        Util.logDebug("Call Marque database");
        //Holder h = dataPro.getMarqueByDate(date);
        Holder h = dataPro.getMarque();
        Marque[] marques = Arrays.copyOf(h.getTheObject(), h.getTheObject().length, Marque[].class);

        //serDate.saveLastUpdateDate(new Date());

        Util.logDebug("Call Lancement database");

        Holder h3 = dataPro.getAllLancement();
        Lancement[] lancements = Arrays.copyOf(h3.getTheObject(), h3.getTheObject().length, Lancement[].class);


        Util.logDebug("Call Store databasessss");
        Holder storeHolder = dataPro.getAllStore();

        Store[] stores = Arrays.copyOf(storeHolder.getTheObject(), storeHolder.getTheObject().length, Store[].class);
        
        if (marques != null) {
            if (marques.length != 0) {
                Util.logDebug("find " + marques.length + " marques add them to local database");
                da.createFactoryEntry(marques);
            }
        }

        if (proms != null) {
            if (proms.length != 0) {
                Util.logDebug("find " + proms.length + " promotions add them to local database");
                da.createPromoEntry(proms);
            }
        }

        if (lancements != null) {
            if (lancements.length != 0) {
                Util.logDebug("find " + lancements.length + " Lancement add them to local database");
                da.createLancementEntry(lancements);
            }
        }

        if (stores != null) {
           if (stores.length != 0) {
              Util.logDebug("find " + stores.length + " stores add them to local database");
              da.createStoreEntry(stores);
            }
        }

        da.close();
    }


}

