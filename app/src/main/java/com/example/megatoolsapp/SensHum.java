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

public class SensHum extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor humiditySensor;
    private TextView humidityTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sens_hum);

        // Get a reference to the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get a reference to the humidity sensor
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        // Get a reference to the humidity text view
        humidityTextView = (TextView) findViewById(R.id.x_text_view);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register the sensor listener
        sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Unregister the sensor listener
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the humidity value from the sensor event
        float humidity = event.values[0];

        // Update the UI with the new value
        humidityTextView.setText("Humidity: " + humidity + " %");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}