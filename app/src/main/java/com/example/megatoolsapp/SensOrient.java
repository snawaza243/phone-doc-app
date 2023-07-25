package com.example.megatoolsapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SensOrient extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor orientationSensor;
    private TextView xTextView, yTextView, zTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sens_orient);

        // Get references to the UI elements
        xTextView = (TextView) findViewById(R.id.x_text_view);
        yTextView = (TextView) findViewById(R.id.y_text_view);
        zTextView = (TextView) findViewById(R.id.z_text_view);

        // Get a reference to the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get a reference to the orientation sensor
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        // Keep the screen on while the app is running
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register the sensor listener
        sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Unregister the sensor listener
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the orientation values from the sensor event
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Update the UI with the new values
        xTextView.setText("X: " + x);
        yTextView.setText("Y: " + y);
        zTextView.setText("Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}