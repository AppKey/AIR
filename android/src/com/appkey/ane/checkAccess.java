package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.appkey.sdk.AppKeyChecker;
import com.appkey.sdk.AppKeyCheckerCallback;

public class checkAccess implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		
		//TODO: REMOVE this temporary event
		Logger.d("AppKeyANEnative.checkAccess Called (java side)");
		//context.dispatchStatusEventAsync("APPKEY_ENABLED", "");

		ANEcontext aneContext = null;
		AppKeyChecker akc = null;
		try {
			aneContext = (ANEcontext)context;
			akc = aneContext._AppKeyChecker;
			akc.checkAccess(new AppKeyCallback(context));
		} catch (Exception e) {
			context.dispatchStatusEventAsync(ANEevents.CHECKACCESS_FAILED, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	class AppKeyCallback implements AppKeyCheckerCallback {
		private FREContext _context;

		public AppKeyCallback(FREContext context) {
			_context = context;
		}

		@Override
		public void allow() {
			Logger.d("AppKeyANEnative.checkAccess allow/enabled callback Called (java side)");
			_context.dispatchStatusEventAsync(ANEevents.APPKEY_ENABLED, "");
		}

		@Override
		public void dontAllow(int reason) {
			//reason is ignored because it is on it's way to deprecation
			Logger.d("AppKeyANEnative.checkAccess dontAllow/Disabled callback Called (java side)");
			_context.dispatchStatusEventAsync(ANEevents.APPKEY_DISABLED, "");
		}
	}
}
