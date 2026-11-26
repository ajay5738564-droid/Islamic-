# APK Build Notes

This commit builds the online-only Deenova Android app.

- Firebase SDK dependencies, Firebase App Check, and the Google Services Gradle plugin are removed.
- The app requires a validated internet connection before its main features can be used.
- Unity Ads integration remains in the app; ad delivery still depends on valid Unity Dashboard configuration and available inventory.
- Build the debug APK with: `gradle :app:assembleDebug`.
