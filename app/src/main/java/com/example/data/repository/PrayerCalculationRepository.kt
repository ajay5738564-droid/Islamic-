package com.example.data.repository

import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.data.model.CityLocation
import com.example.data.model.DailyPrayerSchedule
import com.example.data.model.PrayerName
import com.example.data.model.PrayerTimeSlot
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.*

object PrayerCalculationRepository {

    // Kaaba Coordinates in Makkah al-Mukarramah
    const val KAABA_LATITUDE = 21.422487
    const val KAABA_LONGITUDE = 39.826206

    val DEFAULT_CITIES = listOf(
        CityLocation("New Delhi", "India", 28.6139, 77.2090, 5.5),
        CityLocation("Mumbai", "India", 19.0760, 72.8777, 5.5),
        CityLocation("Bengaluru", "India", 12.9716, 77.5946, 5.5),
        CityLocation("Hyderabad", "India", 17.3850, 78.4867, 5.5),
        CityLocation("Kolkata", "India", 22.5726, 88.3639, 5.5),
        CityLocation("Lucknow", "India", 26.8467, 80.9462, 5.5),
        CityLocation("Makkah", "Saudi Arabia", 21.4225, 39.8262, 3.0),
        CityLocation("Madinah", "Saudi Arabia", 24.5247, 39.5692, 3.0),
        CityLocation("Dubai", "UAE", 25.2048, 55.2708, 4.0),
        CityLocation("Cairo", "Egypt", 30.0444, 31.2357, 2.0),
        CityLocation("Istanbul", "Turkey", 41.0082, 28.9784, 3.0),
        CityLocation("London", "United Kingdom", 51.5074, -0.1278, 1.0),
        CityLocation("New York", "United States", 40.7128, -74.0060, -4.0),
        CityLocation("Karachi", "Pakistan", 24.8607, 67.0011, 5.0),
        CityLocation("Lahore", "Pakistan", 31.5204, 74.3587, 5.0),
        CityLocation("Dhaka", "Bangladesh", 23.8103, 90.4125, 6.0),
        CityLocation("Jakarta", "Indonesia", -6.2088, 106.8456, 7.0),
        CityLocation("Kuala Lumpur", "Malaysia", 3.1390, 101.6869, 8.0)
    )

    val POPULAR_CITIES = DEFAULT_CITIES

    fun calculateQiblaDirection(latitude: Double, longitude: Double): Double {
        val lat1Rad = Math.toRadians(latitude)
        val lat2Rad = Math.toRadians(KAABA_LATITUDE)
        val deltaLonRad = Math.toRadians(KAABA_LONGITUDE - longitude)

        val y = sin(deltaLonRad)
        val x = cos(lat1Rad) * tan(lat2Rad) - sin(lat1Rad) * cos(deltaLonRad)
        var qiblaBearing = Math.toDegrees(atan2(y, x))
        return (qiblaBearing + 360.0) % 360.0
    }

    fun calculateDistanceToKaabaKm(latitude: Double, longitude: Double): Double {
        val earthRadiusKm = 6371.0
        val dLat = Math.toRadians(KAABA_LATITUDE - latitude)
        val dLon = Math.toRadians(KAABA_LONGITUDE - longitude)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(latitude)) * cos(Math.toRadians(KAABA_LATITUDE)) *
                sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadiusKm * c
    }

    fun getDailySchedule(
        date: Calendar,
        latitude: Double,
        longitude: Double,
        timeZoneOffsetHours: Double,
        cityName: String,
        method: CalculationMethod = CalculationMethod.KARACHI,
        asrMadhab: AsrJuristic = AsrJuristic.HANAFI
    ): DailyPrayerSchedule {
        val rawTimes = computeTimes(date, latitude, longitude, timeZoneOffsetHours, method, asrMadhab)

        val calendarNow = Calendar.getInstance()
        val currentHour = calendarNow.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendarNow.get(Calendar.MINUTE)
        val currentTotalMinutes = currentHour * 60 + currentMinute

        var nextSlot: PrayerTimeSlot? = null
        var minDiffMinutes = Int.MAX_VALUE

        val prayerSlots = rawTimes.map { (name, totalMinutes) ->
            val slotHour = totalMinutes / 60
            val slotMinute = totalMinutes % 60
            val formatted = formatTime(slotHour, slotMinute)

            val diff = totalMinutes - currentTotalMinutes
            val isPassed = totalMinutes < currentTotalMinutes

            PrayerTimeSlot(
                name = name,
                timeFormatted = formatted,
                hour = slotHour,
                minute = slotMinute,
                isPassed = isPassed
            )
        }

        // Determine current and next prayer
        var activeCurrentIndex = -1
        for (i in prayerSlots.indices) {
            val slot = prayerSlots[i]
            val slotTotal = slot.hour * 60 + slot.minute
            if (currentTotalMinutes >= slotTotal) {
                activeCurrentIndex = i
            } else if (nextSlot == null) {
                nextSlot = slot
            }
        }

        if (nextSlot == null && prayerSlots.isNotEmpty()) {
            // Next is Fajr tomorrow
            nextSlot = prayerSlots.first()
        }

        val updatedSlots = prayerSlots.mapIndexed { index, slot ->
            slot.copy(
                isCurrent = index == activeCurrentIndex,
                isNext = slot.name == nextSlot?.name
            )
        }

        // Compute countdown
        val targetMinutes = if (nextSlot != null) {
            val slotMin = nextSlot.hour * 60 + nextSlot.minute
            if (slotMin >= currentTotalMinutes) slotMin - currentTotalMinutes else (1440 - currentTotalMinutes + slotMin)
        } else 0

        val countdownSeconds = (targetMinutes * 60L) - calendarNow.get(Calendar.SECOND)
        val hoursRemaining = targetMinutes / 60
        val minsRemaining = targetMinutes % 60
        val timeUntilNext = if (hoursRemaining > 0) "${hoursRemaining}h ${minsRemaining}m" else "${minsRemaining}m"

        val sdfGregorian = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        val dateGregorianStr = sdfGregorian.format(date.time)
        val dateHijriStr = HijriCalendarRepository.getFormattedHijriDate(date)

        val progressFraction = if (activeCurrentIndex >= 0 && activeCurrentIndex < updatedSlots.size - 1) {
            val startTotal = updatedSlots[activeCurrentIndex].hour * 60 + updatedSlots[activeCurrentIndex].minute
            val endTotal = updatedSlots[activeCurrentIndex + 1].hour * 60 + updatedSlots[activeCurrentIndex + 1].minute
            val span = (endTotal - startTotal).coerceAtLeast(1)
            val elapsed = (currentTotalMinutes - startTotal).coerceAtLeast(0)
            (elapsed.toFloat() / span).coerceIn(0f, 1f)
        } else 0.5f

        return DailyPrayerSchedule(
            dateGregorian = dateGregorianStr,
            dateHijri = dateHijriStr,
            locationName = cityName,
            prayers = updatedSlots,
            nextPrayer = nextSlot,
            timeUntilNextFormatted = timeUntilNext,
            countdownSeconds = countdownSeconds.coerceAtLeast(0),
            progressFraction = progressFraction
        )
    }

    private fun computeTimes(
        calendar: Calendar,
        latitude: Double,
        longitude: Double,
        timeZoneHours: Double,
        method: CalculationMethod,
        asrMadhab: AsrJuristic
    ): List<Pair<PrayerName, Int>> {
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)

        // Julian date
        val d = julianDate(year, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)) - 2451545.0

        // Sun's mean anomaly & longitude
        val g = fixAngle(357.529 + 0.98560028 * d)
        val q = fixAngle(280.459 + 0.98564736 * d)
        val l = fixAngle(q + 1.915 * sin(Math.toRadians(g)) + 0.020 * sin(Math.toRadians(2 * g)))

        // Earth's obliquity
        val e = 23.439 - 0.00000036 * d
        val sunDeclination = Math.toDegrees(asin(sin(Math.toRadians(e)) * sin(Math.toRadians(l))))
        val rightAscension = Math.toDegrees(atan2(cos(Math.toRadians(e)) * sin(Math.toRadians(l)), cos(Math.toRadians(l)))) / 15.0
        val eqOfTime = (q / 15.0) - fixHour(rightAscension)

        // Solar Noon in hours
        val noon = fixHour(12.0 + timeZoneHours - (longitude / 15.0) - eqOfTime)

        // Method angles
        val (fajrAngle, ishaAngle, ishaIntervalMins) = when (method) {
            CalculationMethod.MWL -> Triple(18.0, 17.0, 0)
            CalculationMethod.ISNA -> Triple(15.0, 15.0, 0)
            CalculationMethod.EGYPT -> Triple(19.5, 17.5, 0)
            CalculationMethod.MAKKAH -> Triple(18.5, 0.0, 90)
            CalculationMethod.KARACHI -> Triple(18.0, 18.0, 0)
            CalculationMethod.DUBAI -> Triple(18.2, 18.2, 0)
        }

        val fajrDiff = hourAngleDiff(latitude, sunDeclination, -fajrAngle)
        val sunriseDiff = hourAngleDiff(latitude, sunDeclination, -0.833)
        val sunsetDiff = hourAngleDiff(latitude, sunDeclination, -0.833)

        val asrShadowFactor = if (asrMadhab == AsrJuristic.HANAFI) 2.0 else 1.0
        val asrAngle = -Math.toDegrees(atan(1.0 / (asrShadowFactor + tan(Math.toRadians(abs(latitude - sunDeclination))))))
        val asrDiff = hourAngleDiff(latitude, sunDeclination, asrAngle)

        val ishaDiff = if (ishaIntervalMins == 0) hourAngleDiff(latitude, sunDeclination, -ishaAngle) else 0.0

        val fajrHours = noon - fajrDiff
        val sunriseHours = noon - sunriseDiff
        val dhuhrHours = noon + (2.0 / 60.0) // small buffer after solar noon
        val asrHours = noon + asrDiff
        val maghribHours = noon + sunsetDiff
        val ishaHours = if (ishaIntervalMins > 0) maghribHours + (ishaIntervalMins / 60.0) else noon + ishaDiff

        return listOf(
            PrayerName.FAJR to (fajrHours * 60).toInt().coerceIn(0, 1439),
            PrayerName.SUNRISE to (sunriseHours * 60).toInt().coerceIn(0, 1439),
            PrayerName.DHUHR to (dhuhrHours * 60).toInt().coerceIn(0, 1439),
            PrayerName.ASR to (asrHours * 60).toInt().coerceIn(0, 1439),
            PrayerName.MAGHRIB to (maghribHours * 60).toInt().coerceIn(0, 1439),
            PrayerName.ISHA to (ishaHours * 60).toInt().coerceIn(0, 1439)
        )
    }

    private fun hourAngleDiff(latitude: Double, declination: Double, angle: Double): Double {
        val latRad = Math.toRadians(latitude)
        val decRad = Math.toRadians(declination)
        val angleRad = Math.toRadians(angle)
        val cosHA = (sin(angleRad) - sin(latRad) * sin(decRad)) / (cos(latRad) * cos(decRad))
        val clampedCosHA = cosHA.coerceIn(-1.0, 1.0)
        return Math.toDegrees(acos(clampedCosHA)) / 15.0
    }

    private fun julianDate(year: Int, month: Int, day: Int): Double {
        var y = year
        var m = month
        if (m <= 2) {
            y -= 1
            m += 12
        }
        val a = floor(y / 100.0)
        val b = 2 - a + floor(a / 4.0)
        return floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + day + b - 1524.5
    }

    private fun fixAngle(a: Double): Double {
        var angle = a - 360.0 * floor(a / 360.0)
        if (angle < 0) angle += 360.0
        return angle
    }

    private fun fixHour(h: Double): Double {
        var hour = h - 24.0 * floor(h / 24.0)
        if (hour < 0) hour += 24.0
        return hour
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val period = if (hour < 12) "AM" else "PM"
        val h12 = if (hour % 12 == 0) 12 else hour % 12
        return String.format(Locale.getDefault(), "%02d:%02d %s", h12, minute, period)
    }
}
