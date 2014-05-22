package com.appkey.ane
{
	import flash.events.Event;

	/**
	 * AIR side definition of AppKey events. Keep in sync with ANEevents on the
	 * native android side.
	 */
	public class AppKeyEvent extends Event
	{
		public static const APPKEY_ENABLED:String = "APPKEY_ENABLED";
		public static const APPKEY_DISABLED:String = "APPKEY_DISABLED";
		public static const INIT_SUCCESSFUL:String = "INIT_SUCCESSFUL";
		public static const INIT_FAILED:String = "INIT_FAILED";
		public static const CHECKACCESS_FAILED:String = "CHECKACCESS_FAILED";
		
		public var _data:String;

		public function AppKeyEvent(type:String, data:String = null)
		{
			super(type, false, false);
			_data = data;
		}
	}
}