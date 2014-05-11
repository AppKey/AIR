package com.appkey.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;


public class ANEcontext extends FREContext {

	@Override
	public void dispose() {
		//Intentionally left almost blank
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Logger.d("ANEcontext.Map() Called"); 

		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		functionMap.put("init", new init());
		functionMap.put("checkAccess", new checkAccess());
		functionMap.put("promptUser", new promptUser());
		functionMap.put("openAppKey", new openAppKey());
		return functionMap;
	}
}
