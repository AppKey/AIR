#License
This project is provided under a MIT open-source license. See LICENSE.md

#Structure
Finished ANE ready for download: https://github.com/AppKey/AIR/blob/master/ANE/AppKey-1-6.ane

The approach to the AppKey ANE roughly follows the Adobe Android ANE tutorial.
Setting up a proper FlashBuilder environment is as tricky as building the ANE itself.
Instructions for a basic ANE & build environment setup can be found here:
http://www.adobe.com/devnet/air/articles/ane-android-devices.html

/actionscript - ANE interface definition in actionscript<br />
/android - Native side of the ANE<br />
/build - ANE build scripts. Run buildANE.sh to rebuild the ANE<br />
/ANE - Finished ANE<br />
/demo - Flex based demo project that uses the ANE<br />

#Demo project
Typically the fastest way to become familiar with the AppKey AIR integration is
to download and run the sample flex project in /demo. The demo app automatically
checks AppKey status when run, and every time the user switches back to the app.

https://github.com/AppKey/AIR/blob/master/demo/AppKeyANEdemo.apk

Open the project in Flash Builder to observe the (rather simple) integration.

#Integration
1) Add AppKey-x-x.ane to your AIR project<br />
2) Instantiate & Initialize AppKey upon creationComplete()<br />
```actionscript
import com.appkey.ane.AppKey;
import com.appkey.ane.AppKeyEvent;
...
private static const AppKeyAppId : String = "0";  <<<---- NOTE: Replace with AppId from AppKey.com
private static const AppKeyAnalyticsEnabled : Boolean = true;
private var mAppKey:AppKey = null;
...
private function handleCreationComplete():void
{
	mAppKey = new AppKey(AppKeyAppId, AppKeyAnalyticsEnabled);
	mAppKey.addEventListener(AppKeyEvent.APPKEY_ENABLED, this.onEvent);
	mAppKey.addEventListener(AppKeyEvent.APPKEY_DISABLED, this.onEvent);
	mAppKey.checkAccess();
}
```
3) Create event handlers that switch on/off AppKey features<br />
```actionscript
public function onEvent(akEvent:AppKeyEvent):void {
	switch (akEvent.type) {
		case AppKeyEvent.APPKEY_ENABLED:
			//TODO: Insert code here to ENABLE features for AppKey users
			break;
		case AppKeyEvent.APPKEY_DISABLED:
			//TODO: Insert code here to DISABLE features for AppKey users
			break;
	}
}
```
4) Prompt the user to install and/or enable AppKey.  (Note - AppKey handles all
the UI, your app simply needs to call promptUser())<br />
```actionscript
private static const AppKeyBenefit : String = "feature";  <<<--- Replace with feature to be unlocked
...
public function button_promptUser_clickHandler(event:MouseEvent):void
{
	mAppKey.promptUser(AppKeyBenefit);
}
```

#Feedback
We would love your feedback and/or improvements to this project - contact@appkey.com
