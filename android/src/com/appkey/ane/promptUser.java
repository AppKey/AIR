package com.appkey.ane;

import android.app.Activity;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.appkey.sdk.AppKeyChecker;

/**
 * Call promptUser on the AppKeySdk. Requires a benefit string to prompt the user with.
 * 
 * @author @vitek
 */
public class promptUser implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {

		//Grab the parameters
		String benefit = null;
		try {
			benefit = args[0].getAsString();
		} catch (Exception e) {
			Logger.d("AppKeyANEnative.promptUser Called, but there is no benefit");
			e.printStackTrace();
			return null;
		}
		Logger.d("AppKeyANEnative.promptUser Called. benefit = "+benefit);

		ANEcontext aneContext = null;
		Activity aneActivity = context.getActivity();
		AppKeyChecker akc = null;
		try {
			aneContext = (ANEcontext)context;
			akc = aneContext._AppKeyChecker;
			akc.promptUser(aneActivity, benefit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}