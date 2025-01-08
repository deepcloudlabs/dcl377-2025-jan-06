keytool -genkeypair -alias aselsan -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore aselsan.p12 -validity 3650
keytool -genkeypair -alias aselsan -keyalg RSA -keysize 2048 -keystore aselsan.jks -validity 3650
keytool -importkeystore -srckeystore aselsan.jks -destkeystore aselsan.p12 -deststoretype pkcs12