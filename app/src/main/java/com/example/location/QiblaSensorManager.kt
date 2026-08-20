package com.example.location

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs

class QiblaSensorManager(context: Context) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    private val rotationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    private val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnetometer = sensorManager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private val _azimuthFlow = MutableStateFlow(0f)
    val azimuthFlow: StateFlow<Float> = _azimuthFlow.asStateFlow()

    private val _hasSensor = MutableStateFlow(sensorManager != null)
    val hasSensor: StateFlow<Boolean> = _hasSensor.asStateFlow()

    private var gravityValues = FloatArray(3)
    private var geoMagneticValues = FloatArray(3)
    private var hasGravity = false
    private var hasGeomagnetic = false

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    fun startListening() {
        if (rotationSensor != null) {
            sensorManager?.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            accelerometer?.let { sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
            magnetometer?.let { sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        }
    }

    fun stopListening() {
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            SensorManager.getOrientation(rotationMatrix, orientationAngles)
            var azimuthDegrees = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()
            azimuthDegrees = (azimuthDegrees + 360f) % 360f
            updateSmoothAzimuth(azimuthDegrees)
        } else if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, gravityValues, 0, 3)
            hasGravity = true
            processCombined()
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, geoMagneticValues, 0, 3)
            hasGeomagnetic = true
            processCombined()
        }
    }

    private fun processCombined() {
        if (hasGravity && hasGeomagnetic) {
            val success = SensorManager.getRotationMatrix(rotationMatrix, null, gravityValues, geoMagneticValues)
            if (success) {
                SensorManager.getOrientation(rotationMatrix, orientationAngles)
                var azimuthDegrees = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()
                azimuthDegrees = (azimuthDegrees + 360f) % 360f
                updateSmoothAzimuth(azimuthDegrees)
            }
        }
    }

    private fun updateSmoothAzimuth(newDegrees: Float) {
        val current = _azimuthFlow.value
        var diff = newDegrees - current
        // Handle wrap-around
        while (diff < -180f) diff += 360f
        while (diff > 180f) diff -= 360f
        val smoothed = (current + diff * 0.25f + 360f) % 360f
        _azimuthFlow.value = smoothed
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
