package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.appkey.sdk.AppKeyChecker;

public class openAppKey implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Logger.d("AppKeyANEnative.openAppKey Called");

		ANEcontext aneContext = null;
		AppKeyChecker akc = null;
		try {
			aneContext = (ANEcontext)context;
			akc = aneContext._AppKeyChecker;
			akc.openAppKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
