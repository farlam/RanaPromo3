package com.ranapromo.nara.ranapromo3.comman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smati on 17/01/2016.
 */
public class Util {
    public static final String LOCAL_ACTION = "Promo_favorit";
    public static final String PROMO_VIEWD = "Promo_viewed";
    public static final String ACTION_VIEWED = "VIEWED";
    public static final String ACTION_FAVORIT = "FAVORIT";
    public static String DEBUG_VAL = "MYAA";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



    public static boolean isMustDownload(String destFile) {
        File file = new File(destFile);
        Log.d(DEBUG_VAL, "cherche du fichier " + destFile + " dans cache de telephone");
        return file.exists();
        /*HttpURLConnection connection = null;
        if(file.exists()){
            Log.d(DEBUG_VAL,"File destFile existe");
            try {
                String fileName = file.getName();
                Log.d(DEBUG_VAL,"chercher le fichier "+ fileName +" dans le serveur pour comparer la version");
                URL url = new URL("http://192.168.1.14/test/uploads/"+fileName);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                if(file.lastModified() != connection.getIfModifiedSince())
                {
                    Log.d(DEBUG_VAL,"Les fichiers ne sont pas les memes téléchargement de la nouvelle version");
                    return true;
                }

                return false;
            } catch (IOException e) {
                if(connection!=null) connection.disconnect();
                e.printStackTrace();
                Log.e(DEBUG_VAL,"erreur de chargement du fichier "+e.getMessage() );
                return false;
            }
        } else
        {
            return true;
        }*/
    }

    public static Bitmap getBitmapFromURL(String src, String dest) {
        HttpURLConnection connection = null;
        InputStream input = null;
        FileOutputStream out = null;
        try {
            if (src == null) {
                Log.d(DEBUG_VAL, "Impossible de télécharger une image null");
                return null;
            }

            URL url = new URL(src);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            ByteArrayList arrayList = new ByteArrayList();
            File file = new File(dest);
            out = new FileOutputStream(file);
            byte[] buffer = new byte[2024];
            int read = 0;
            while ((read = input.read(buffer)) != -1) {
                System.out.println(read);
                arrayList.add(buffer, 0, read);
                out.write(buffer, 0, read);
            }
            out.flush();
            Log.d(DEBUG_VAL, "Image " + dest + " Téléchargée avec succes");

            //Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Bitmap myBitmap = BitmapFactory.decodeByteArray(arrayList.getArray(), 0, arrayList.getArray().length);
            return myBitmap;
        } catch (IOException e) {
            Log.e(DEBUG_VAL, e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) connection.disconnect();
                if (input != null) input.close();
                if (out != null) out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.d(DEBUG_VAL, "error while loading file " + src + " " + e.getMessage());
            }
        }
    }


    public static void logDebug(String s) {
        Log.d(DEBUG_VAL, s);
    }

    public static void logError(String s) {
        Log.e(DEBUG_VAL, s);
    }


    public static String getSetterName(String fiedName){
        String value = "set";
        String[] s = fiedName.split("_");
        for(int i = 0;i<s.length;i++){
            String first = s[i].substring(0, 1).toUpperCase();
            String last = s[i].substring(1).toLowerCase();
            value = value.concat(first+last);
        }
        return value;
    }


    public static void setProperty(Object obj,String fieldName,Class fieldType,String value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ParseException, InvocationTargetException {
        Class c = obj.getClass();
        //System.out.println(c);
        Method m = c.getDeclaredMethod(getSetterName(fieldName), new Class[]{fieldType});
        m.invoke(obj, new Object[]{Convert(value, fieldType)});
    }


    public static Object Convert(String object,Class fieldType ) throws ParseException{
        Object result = object;

        if(fieldType == Date.class){
            try
            {
                result = format.parse(object);
            }catch (Exception e) {
                try{
                   result = format2.parse(object);
                } catch (Exception e1){
                    result = null;
                }
            }
        }

        if(fieldType == Boolean.class){
            result = Boolean.parseBoolean(object);
        }

        if(fieldType == Double.class){
            result = Double.parseDouble(object);
        }

        if(fieldType == Integer.class){
            result = Integer.parseInt(object);
        }

        if(fieldType == Float.class){
            result = Float.parseFloat(object);
        }

        return result;
    }


}