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
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //super.onCreate();
        Util.logDebug("StartUpdater service started to update data");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Util.logDebug("StartUpdater service started to update data");
        DataProviderImpl3 dataPro= new DataProviderImpl3(this.getString(R.string.server_url));
        if(Util.isOnline(this)) {
            DataBaseHelper da = new DataBaseHelper(this);
            try {
                da.open();
                LogData[] logs = da.upDateLogActivities();

                for (LogData mr : logs) {
                   if(dataPro.setViewedPromotion(mr.getValue()))
                   {
                       da.deleteLogEntry(mr.getId());
                   }

                }
                da.close();
            } catch (Exception e) {
                Util.logError("Update service error");
            } finally {

            }
        }
        stopSelf();
        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
