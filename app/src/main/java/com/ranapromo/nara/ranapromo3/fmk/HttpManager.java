package com.ranapromo.nara.ranapromo3.fmk;

import com.ranapromo.nara.ranapromo3.comman.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {
  public static String GetData(String arg0) throws Exception {
	  String result = null;
	  BufferedReader read = null;
	  HttpURLConnection httpCon = null;
	  StringBuilder sb = null;
	  try {
		URL url = new URL(arg0);
		httpCon = (HttpURLConnection) url.openConnection();
		sb = new StringBuilder();
		read = new BufferedReader(new InputStreamReader(httpCon.getInputStream(),"UTF-8"));
		String line;
		while((line = read.readLine()) != null){
			sb.append(line+"\n");
		}
		result =sb.toString();
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		  if(read!=null){
			  try {
				  read.close();
			  } catch (IOException e1) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
		  if(httpCon!=null){
			  httpCon.disconnect();
		  }
		  Util.logError("Error retriving data from server with url+"+arg0);
		  Util.logError("Error is "+e.getMessage());
		  throw new Exception("Server access error");
	  }finally
	  {
		  if(read!=null){
		  try {
			read.close();
		  } catch (IOException e) {
			// TODO Auto-generated catch block
		    	e.printStackTrace();
	    	}
		  }
		  if(httpCon!=null){
			  httpCon.disconnect();
		  }
	  }
	  return result;
  }
}
