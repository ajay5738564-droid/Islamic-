# APK Build Notes

This commit triggers the Android debug APK workflow.

- Unity Ads SDK dependency removed from the app module.
- Unity Ads initialization and interstitial display removed from MainActivity.
- Unity banner component removed from the Home screen.
- Unity Ads implementation and configuration source files removed.
- Unity Ads version-catalog entries removed.

The app is intended to build without Unity Ads integration.
