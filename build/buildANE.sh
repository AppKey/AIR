latest_appkey_sdk="/Users/jv/GoogleDrive/ak/jv/Builds/AppKeySdk-1-6/AppKeySdk-1-6.jar"
adt="/Applications/Adobe Flash Builder 4.7/eclipse/plugins/com.adobe.flash.compiler_4.7.0.349722/AIRSDK/bin/adt"
native_dir="../android"
output_dir="../ANE"

echo "********************************************************************"
echo " - creating ANE package"

rm -rf Android-ARM/*
rm -f AppKey.ane library.swf
mkdir -p Android-ARM

unzip ../actionscript/bin/AppKeyANEactionscript.swc library.swf
cp library.swf Android-ARM
cp "$native_dir"/bin/appkeyanenative.jar Android-ARM
cp -r "$native_dir"/res Android-ARM
cp "$latest_appkey_sdk" Android-ARM

"$adt" -package -target ane "$output_dir"/AppKey.ane AppKeyANEdescriptor.xml -swc ../actionscript/bin/AppKeyANEactionscript.swc -platform Android-ARM -C Android-ARM .
#w/platform.xml - "$adt" -package -target ane "$output_dir"/AppKey.ane AppKeyANEdescriptor.xml -swc ../actionscript/bin/AppKeyANEactionscript.swc -platform Android-ARM -platformoptions platform.xml -C Android-ARM .

#"$adt" -package -storetype PKCS12 -keystore cer.p12 -storepass password -target ane SampleASExtension.ane extension.xml -swc ../ANESample/bin/ANESample.swc -platform Android-ARM -C Android-ARM .