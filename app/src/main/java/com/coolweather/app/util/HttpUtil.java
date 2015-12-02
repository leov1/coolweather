package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by d on 2015/12/2.
 */
public class HttpUtil {
	public static  void sendHttpRequest(final String address,final HttpCallbackListener listener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection conn = null;
				try {
					URL url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);
					InputStream inputStream =conn.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					StringBuilder builder = new StringBuilder();
					String line = null;
					while ((line=reader.readLine())!= null){
						builder.append(line);
					}
					if (listener!=null){
						listener.onFinish(builder.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (listener!=null){
						listener.onError(e);
					}
				}finally {
					if (conn!=null){
						conn.disconnect();
					}
				}
			}
		}).start();
	}

}
