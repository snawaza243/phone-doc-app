package com.example.megatoolsapp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensTemp extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor ambientTemperatureSensor;
    private TextView temperatureTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sens_temp);

        // Get a reference to the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get a reference to the ambient temperature sensor
        ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        // Get a reference to the temperature text view
        temperatureTextView = (TextView) findViewById(R.id.x_text_view);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register the sensor listener
        sensorManager.registerListener(this, ambientTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Unregister the sensor listener
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the temperature value from the sensor event
        float temperature = event.values[0];

        // Update the UI with the new value
        temperatureTextView.setText("Temperature: " + temperature + " °C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}