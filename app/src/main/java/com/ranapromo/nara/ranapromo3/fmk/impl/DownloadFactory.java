package com.ranapromo.nara.ranapromo3.fmk.impl;

import android.util.Log;

import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.fmk.IDownloadHandler;

public class DownloadFactory {
    public static IDownloadHandler getFactory(String className){
    	IDownloadHandler result = null;
		try {
			result = (IDownloadHandler) Class.forName(className).newInstance();
		} catch (Exception e) {
			Log.d(Util.DEBUG_VAL, "impossible de cr�er une instance de " + className);
		}
    	return result;
    }
}
