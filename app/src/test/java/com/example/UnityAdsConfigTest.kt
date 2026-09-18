package com.example

import com.example.ads.unity.UnityAdsConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class UnityAdsConfigTest {

    @Test
    fun testUnityAdsConfiguration() {
        assertEquals("800361482", UnityAdsConfig.GAME_ID)
        assertNotNull(UnityAdsConfig.BANNER_PLACEMENT_ID)
        assertNotNull(UnityAdsConfig.INTERSTITIAL_PLACEMENT_ID)
        assertNotNull(UnityAdsConfig.REWARDED_PLACEMENT_ID)
        assertTrue(UnityAdsConfig.MIN_INTERSTITIAL_INTERVAL_MS >= 30_000L)
    }
}
