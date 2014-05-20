latest_appkey_sdk="/Users/jv/GoogleDrive/ak/jv/Builds/AppKeySdk-1-6/AppKeySdk-1-6.jar"
adt="/Applications/Adobe Flash Builder 4.7/eclipse/plugins/com.adobe.flash.compiler_4.7.0.349722/AIRSDK/bin/adt"
native_dir="../android"
output_dir="../ANE"

echo "********************************************************************"
echo " - staging files"

rm -rf Android-ARM/*
rm -f AppKey.ane library.swf
mkdir -p Android-ARM
unzip ../actionscript/bin/AppKeyANEactionscript.swc library.swf
cp library.swf Android-ARM
cp -r "$native_dir"/res Android-ARM

echo "********************************************************************"
echo " - Merging ANE native (java) & AppKeySdk into a single jar"
rm -rf jarguts/*
mkdir -p jarguts
(cd jarguts; jar -xf "$latest_appkey_sdk")
(cd jarguts; jar -xf ../"$native_dir"/bin/appkeyanenative.jar)
jar cvf Android-ARM/appkeyanenative.jar -C jarguts .
rm -rf jarguts/*
rmdir jarguts

echo "********************************************************************"
echo " - creating ANE package"
"$adt" -package -target ane "$output_dir"/AppKey.ane AppKeyANEdescriptor.xml -swc ../actionscript/bin/AppKeyANEactionscript.swc -platform Android-ARM -C Android-ARM .

#"$adt" -package -storetype PKCS12 -keystore cer.p12 -storepass password -target ane SampleASExtension.ane extension.xml -swc ../ANESample/bin/ANESample.swc -platform Android-ARM -C Android-ARM .