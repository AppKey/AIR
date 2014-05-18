package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class ANEextension implements FREExtension {

	/*
	 * Creates a new instance of ANEcontext when the context is created
	 * from the actionscript code.
	 */
	@Override
	public FREContext createContext(String extId) {
		Logger.d("ANEextension.createContext() Called"); 
		// TODO Auto-generated method stub
		return new ANEcontext();
	}

	//Called if the extension is unloaded from the process
	@Override
	public void dispose() {
		Logger.d("ANEextension.dispose() Called");
		// TODO Auto-generated method stub
	}

	//Extension initialization
	@Override
	public void initialize() {
		Logger.d("ANEextension.initialize() Called");
		// TODO Auto-generated method stub
	}
}
