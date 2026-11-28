# APK Build Notes

This project builds the **Islamic App**.

- Firebase SDK dependencies, Firebase App Check, and the Google Services Gradle plugin are removed.
- The app requires a validated internet connection before its main screens can be used.
- Automatic background preloading of Quran Juz data for offline reading has been removed.
- Prayer alarms are recalculated after device reboot, date changes, and time/time-zone changes; the receiver also queues the next set after an alarm fires.
- Unity Ads integration remains enabled in live mode. Delivery depends on the Unity Dashboard Game ID, placement IDs, consent/disclosure requirements, and available inventory.
- The Android application ID remains `com.aistudio.deenova.appqvr` so this build can update the existing installation.
- The next release is version name `1.0.1`, version code `2`.

## Build

- Debug validation build: `gradle :app:assembleDebug`
- Release APK: `gradle :app:assembleRelease`
- Release output: `app/build/outputs/apk/release/app-release.apk`

## Release signing requirement

The release build must be signed with the **same upload/signing keystore used for the existing published APK**. Configure `KEYSTORE_PATH`, `STORE_PASSWORD`, and `KEY_PASSWORD` in the build environment. The configured key alias is `upload`. Do not commit keystore files or passwords to the repository.

A successful source update or debug build does not by itself verify a signed release APK or guarantee acceptance by Uptodown.
