#!/usr/bin/env bash
keytool -genkey -alias kubeek-server -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650