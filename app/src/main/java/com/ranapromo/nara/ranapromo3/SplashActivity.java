package com.ranapromo.nara.ranapromo3;


import java.io.File;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.view.View;
import android.content.Intent;
import android.app.Activity;

import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.fmk.impl.DownloadFactory;
import com.ranapromo.nara.ranapromo3.fmk.IDownloadHandler;

public class SplashActivity extends AppCompatActivity {
    private LinearLayout ln = null;
    private String factoryStr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ln = (LinearLayout) findViewById(R.id.ll1);
        factoryStr = getResources().getString(R.string.httpDownload);
        new MyTask().execute(factoryStr);
    }

    class MyTask extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            Util.logDebug("onPreExecute executed V333");
            ln.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(String... arg0) {
            Util.logDebug("Doing job here with download handler " + arg0[0]);
            downlaodUpdatedData(arg0[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Util.logDebug("onPostExecute executed");
            ln.setVisibility(View.GONE);
            //if(isFirstTime(SplashActivity.this)){
             //     Util.logDebug("user registed");
            SplashActivity.this.finish();
            Intent openStartPoint = new Intent(SplashActivity.this,Main.class);
            startActivity(openStartPoint);
            	  
            //} else{
            //	Intent openStartPoint = new Intent(SplashActivity.this,SetUpActivity.class);
             //   startActivity(openStartPoint);
            //}
          
            super.onPostExecute(result);
        }

       
		private void downlaodUpdatedData(String factoryStr2) {
            Util.logDebug("Chargement du fichier via la class " + factoryStr2);
            IDownloadHandler handler = DownloadFactory.getFactory(factoryStr2);
            Util.logDebug("Chargement du fichier terminer");
            handler.downLoad(SplashActivity.this);
        }
		
		
		 private boolean isFirstTime(Activity myTask) {
			 File theDir = myTask.getCacheDir();
		     File myFile = new File(theDir, "user.bin");
		     if(myFile.exists()) return true;
			 return false;
		}


    }

}
