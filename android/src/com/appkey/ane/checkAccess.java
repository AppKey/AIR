package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.appkey.sdk.AppKeyChecker;
import com.appkey.sdk.AppKeyCheckerCallback;

/**
 * Call AppKeySdk to determine AppKey status. Result returned to flash via events.
 * @author jv
 */
public class checkAccess implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		
		Logger.d("AppKeyANEnative.checkAccess Called (java side)");

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
			//reason code ignored because it is on it's way to deprecation soon
			Logger.d("AppKeyANEnative.checkAccess dontAllow/Disabled callback Called (java side)");
			_context.dispatchStatusEventAsync(ANEevents.APPKEY_DISABLED, "");
		}
	}
}
