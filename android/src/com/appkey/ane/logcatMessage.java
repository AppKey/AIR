package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * AIR uses funky trace statements that require the app to be launched in debug mode. This utility api enables debugging
 * in context with the native code.
 * @author @vitek
 */
public class logcatMessage implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		
		String message = null;
		try {
			message = args[0].getAsString();
		} catch (Exception e) {
			e.printStackTrace();
			message = null;
		}
		if (message != null) {
			Logger.d("AppKeyANE.logcatMessage: " + message);
		}
		return null;
	}
}
