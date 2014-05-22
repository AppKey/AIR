package com.appkey.ane
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.desktop.NativeApplication;
	
	public class AppKey extends EventDispatcher
	{
		/**
		 * Returns whether or not AppKey is currently enabled. Provided for simplicity even
		 * though it may be innaccurate for a few milliseconds on app start & resume. Good for
		 * use in loops that periodically check this value.
		 */
		public var isAppKeyEnabled : Boolean = false;
		
		private static const extensionId : String = "com.appkey.ane";
		private static const contextType : String = "AppKey";
		private static const debugLogging : Boolean = false;

		protected var extContext:ExtensionContext = null;

		/**
		 * @param appId AppKey.com assigned AppID of the calling application
		 * @param analyticsEnabled if true, and if the calling app has INTERNET permission, the SDK will send user interactions
		 *        events along the installation funnel for the purpose of measuring & optimizing conversion. Events are tracked 
		 *        using an anonymous UDID.
		 */
		public function AppKey(appId:String, analyticsEnabled:Boolean) {
			if (!extContext) {
				extContext = ExtensionContext.createExtensionContext(extensionId, contextType);
				if (extContext) {
					extContext.addEventListener(StatusEvent.STATUS, onStatus);
					extContext.call("init", appId, analyticsEnabled);
					NativeApplication.nativeApplication.addEventListener(Event.ACTIVATE, onActivate);
				} else {
					isAppKeyEnabled = false;
					aneLogcatMessage("Failed to get ANE context");
				}
			}
		}
		
		/**
		 * Convienence function to write a message to the android logcat log.
		 * @param message Message to write to the android device log
		 */
		public function aneLogcatMessage(message : String):void {
			if (debugLogging && extContext && (message != null)) {
				extContext.call("logcatMessage", message);
			}
		}
		
		/**
		 * Call checkAccess() on the AppKeySdk. Result returned in two ways:
		 * 1. isAppKeyEnabled boolean updated
		 * 2. onStatus will be called with the appropriate event and will dispatch an event
		 * back to calling application.
		 */
		public function checkAccess():void {
			//extContext.call("checkAccess");
			extContext.call("checkAccess");
			aneLogcatMessage("AppKey.as.CheckAccess Called");
		}

		/**
		 * Prompt the user to install/enable AppKey. User will be prompted via AlertDialog if
		 * AppKey is not installed, or sent to the Instructions screen in AppKey if it is installed.
		 * @param benefit Description of what will be unlocked for AopKey users
		 */
		public function promptUser(benefit : String):void {
			aneLogcatMessage("AppKey.as.promptUser Called. benefit == "+benefit);
			extContext.call("promptUser", benefit);
		}
		
		/**
		 * Open the AppKey app
		 */
		public function openAppKey():void {
			extContext.call("openAppKey");
		}
		
		/**
		 * Recheck access whenever user starts or resumes this app. This is setup in the
		 * constructor and is designed to keep an accurate status of whether or not
		 * AppKey is enabled.
		 */
		protected function onActivate(event:flash.events.Event):void {
			checkAccess();
		}
		
		/**
		 * Handle all events coming from the ANE native side
		 */
		protected function onStatus(event:StatusEvent):void {
			var e:AppKeyEvent = null;
			
			switch (event.code) {
				case AppKeyEvent.APPKEY_ENABLED:
					isAppKeyEnabled = true;
					aneLogcatMessage("Event received: APPKEY_ENABLED");
					e = new AppKeyEvent(AppKeyEvent.APPKEY_ENABLED);
					break;
				case AppKeyEvent.APPKEY_DISABLED:
					isAppKeyEnabled = false;
					aneLogcatMessage("Event received: APPKEY_DISABLED");
					e = new AppKeyEvent(AppKeyEvent.APPKEY_DISABLED);
					break;
				case AppKeyEvent.INIT_SUCCESSFUL:
					aneLogcatMessage("Event received: INIT_SUCCESSFUL");
					e = new AppKeyEvent(AppKeyEvent.INIT_SUCCESSFUL);
					break;
				case AppKeyEvent.INIT_FAILED:
					aneLogcatMessage("Event received: INIT_FAILED");
					e = new AppKeyEvent(AppKeyEvent.INIT_FAILED);
					break;
				case AppKeyEvent.CHECKACCESS_FAILED:
					aneLogcatMessage("Event received: CHECKACCESS_FAILED");
					e = new AppKeyEvent(AppKeyEvent.CHECKACCESS_FAILED);
					break;
			}
			
			if (e != null) {
				this.dispatchEvent(e);
			} else {
				aneLogcatMessage("AppKey.as.onStatus Unrecognized event received");
			}
		}
	}
}
