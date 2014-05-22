package com.appkey.ane;

import android.app.Activity;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.appkey.sdk.AppKeyChecker;

/**
 * Initialize the AppKeyChecker. Requires an appId & value for analyticsEnabled.
 * @author @vitek
 */
public class init implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {

		//Grab the parameters
		String appId = null;
		boolean analyticsEnabled = false;
		try {
			appId = args[0].getAsString();
			analyticsEnabled = args[1].getAsBool();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Logger.d("AppKeyANEnative.init Called. appId = "+appId+", analyticsEnabled="+analyticsEnabled);

		//instantiate AppKeyChecker
		ANEcontext aneContext = (ANEcontext)context;
		Activity aneActivity = context.getActivity();
		try {
			aneContext._AppKeyChecker = new AppKeyChecker(aneActivity, appId, analyticsEnabled);
			context.dispatchStatusEventAsync(ANEevents.INIT_SUCCESSFUL, "");
		} catch (Exception e) {
			aneContext._AppKeyChecker = null;
			context.dispatchStatusEventAsync(ANEevents.INIT_FAILED, e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

}
