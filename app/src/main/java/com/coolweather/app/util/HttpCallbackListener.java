package com.coolweather.app.util;

/**
 * Created by d on 2015/12/2.
 */
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
