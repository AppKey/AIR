package com.appkey.ane
{
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;
	
	public class AppKeyExtension 
	{
		private static var extContext:ExtensionContext = null;
		private static const extensionId : String = "com.appkey.ane";
		private static const contextType : String = "AppKeyExtension";

		public static function isSupported():Boolean {
			return ((Capabilities.manufacturer.indexOf("Android") != -1) && (extContext != null));
		}
		
		//TODO: Add constructor parameters for appId & analyticsEnabled
		private var appId : String = "9";
		private var analyticsEnabled : Boolean = true;
		
		public function AppKeyExtension() {
			trace("AppKeyExtension: Constructor called");
			if (!extContext) {
				trace("AppKeyExtension: Creating extension context");
				extContext = ExtensionContext.createExtensionContext(extensionId, contextType);
				if (extContext) {
					//TODO: Finish: extContext.call("init", appId, analyticsEnabled);
					trace("AppKeyExtension: Extension context created, calling init");
					extContext.call("init");
					trace("AppKeyExtension: Back from init");
				} else {
					trace("AppKeyExtension: Failed to create extension context");
				}
			}
		}
		
		public function checkAccess():void {
			extContext.call("checkAccess");
		}
		
		public function promptUser(benefit : String):void {
			extContext.call("promptUser", benefit);
		}
		
		public function openAppKey():void {
			extContext.call("openAppKey");
		}
	}
}
