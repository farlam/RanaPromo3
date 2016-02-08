package com.ranapromo.nara.ranapromo3.fmk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ranapromo.nara.ranapromo3.Data.Lancement;
import com.ranapromo.nara.ranapromo3.Data.LogData;
import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.comman.DataBaseHelper;
import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.fmk.impl.DataProviderImpl3;

public class UpdaterService extends Service {
    public UpdaterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Util.logDebug("StartUpdater service started to update data");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Util.logDebug("StartUpdater service started to update data");
        final DataProviderImpl3 dataPro= new DataProviderImpl3(this.getString(R.string.server_url));
        final DataBaseHelper da = new DataBaseHelper(this);
        if(Util.isOnline(this)) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        da.open();
                        LogData[] logs = da.upDateLogActivities();

                        Util.logDebug(logs.length + " entries founded in log database update the server");
                        for (LogData mr : logs) {
                            if(dataPro.setViewedPromotion(mr.getValue()))
                            {
                                da.deleteLogEntry(mr.getId());
                            }
                        }
                        Util.logDebug("update log data successfully ");
                        da.close();
                    } catch (Exception e) {
                        Util.logError("Update service error ");
                        Util.logError("Error is "+e.getMessage());
                    } finally {

                    }
                }
            });
            t.start();
        }
        stopSelf();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
