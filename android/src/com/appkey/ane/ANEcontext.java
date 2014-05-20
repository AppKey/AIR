package com.appkey.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.appkey.sdk.AppKeyChecker;


public class ANEcontext extends FREContext {

	public AppKeyChecker _AppKeyChecker = null;

	@Override
	public Map<String, FREFunction> getFunctions() {
		Logger.d("ANEcontext.Map() Called"); 

		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		functionMap.put("init", new init());
		functionMap.put("checkAccess", new checkAccess());
		functionMap.put("promptUser", new promptUser());
		functionMap.put("openAppKey", new openAppKey());
		functionMap.put("logcatMessage", new logcatMessage());
		return functionMap;
	}
	@Override
	public void dispose() {
		//Intentionally left almost blank
	}
}
