#About
AppKey ANE for Adobe AIR projects (see AppKey.com for an AppKey overview)

#License
This project is provided under a MIT open-source license. See LICENSE.md

#Structure
Finished ANE ready for download: github.com/appkey/AIR/ANE/AppKey-x-x.ane

The approach to the AppKey ANE roughly follows the Adobe Android ANE tutorial.
Setting up a proper FlashBuilder environment is as tricky as building the ANE itself.
Instructions for a basic ANE & build environment setup can be found here:
http://www.adobe.com/devnet/air/articles/ane-android-devices.html

/actionscript - ANE interface definition in actionscript
/android - Native side of the ANE
/build - ANE build scripts. Run buildANE.sh to rebuild the ANE
/ANE - Finished ANE
/demo - Flex based demo project that uses the ANE

#Demo project
Typically the fastest way to become familiar with the AppKey AIR integration is
to download and run the sample flex project in /demo. The demo app automatically
checks AppKey status when run, and every time the user switches back to the app.

Open the project in Flash Builder to observe the (rather simple) integration.

#Integration
1. Add AppKey-x-x.ane to your AIR project
2. Instantiate & Initialize AppKey upon creationComplete() and s
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
3. Create event handlers that switch on/off AppKey features
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
4. Prompt the user to install and/or enable AppKey.  (Note - AppKey handles all
the UI, your app simply needs to call promptUser())
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
