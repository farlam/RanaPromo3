package com.ranapromo.nara.ranapromo3.fmk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {
  public static String GetData(String arg0) {
	  BufferedReader read = null;
	  HttpURLConnection httpCon = null;
	  try {
		URL url = new URL(arg0);
		httpCon = (HttpURLConnection) url.openConnection();
		StringBuilder sb = new StringBuilder();
		read = new BufferedReader(new InputStreamReader(httpCon.getInputStream(),"UTF-8"));
		String line;
		while((line = read.readLine()) != null){
			sb.append(line+"\n");
		}
		return sb.toString();
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
	  }
	  return null;
  }
}
