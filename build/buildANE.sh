adt="/Applications/Adobe Flash Builder 4.7/eclipse/plugins/com.adobe.flash.compiler_4.7.0.349722/AIRSDK/bin/adt"
native_dir="../android"
output_dir="../ANE"

echo "********************************************************************"
echo " - creating ANE package"

rm -rf Android-ARM/*
rm -f SampleASExtension.ane library.swf
mkdir -p Android-ARM

unzip ../actionscript/bin/AppKeyANEactionscript.swc library.swf
cp library.swf Android-ARM
cp "$native_dir"/bin/AppKeyANEnative.jar Android-ARM
cp -r "$native_dir"/res Android-ARM

"$adt" -package -target ane "$output_dir"/AppKeyExtension.ane AppKeyANEdescriptor.xml -swc ../actionscript/bin/AppKeyANEactionscript.swc -platform Android-ARM -C Android-ARM .

#"$adt" -package -storetype PKCS12 -keystore cer.p12 -storepass password -target ane SampleASExtension.ane extension.xml -swc ../ANESample/bin/ANESample.swc -platform Android-ARM -C Android-ARM .