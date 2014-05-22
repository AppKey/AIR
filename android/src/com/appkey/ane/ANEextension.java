package com.appkey.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

/**
 * Boilerplate from Adobe's documentation
 * @author @vitek
 */
public class ANEextension implements FREExtension {

	/*
	 * Creates a new instance of ANEcontext when the context is created
	 * from the actionscript code.
	 */
	@Override
	public FREContext createContext(String extId) {
		Logger.d("ANEextension.createContext() Called"); 
		return new ANEcontext();
	}

	//Called if the extension is unloaded from the process
	@Override
	public void dispose() {
		Logger.d("ANEextension.dispose() Called");
	}

	//Extension initialization
	@Override
	public void initialize() {
		Logger.d("ANEextension.initialize() Called");
	}
}
