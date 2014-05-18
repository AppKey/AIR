package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;


public class init implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {

		Logger.d("init.call() Called");
		//Retrieve init parameters
		String appId = null;
		boolean analyticsEnabled = false;
		try {
			appId = args[0].getAsString();
			analyticsEnabled = args[1].getAsBool();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Logger.d("appId = "+appId+", analyticsEnabled="+analyticsEnabled);
		
		return null;
	}

}
