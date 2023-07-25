package com.example.megatoolsapp;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensProx extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private TextView proximityTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sens_prox);

        // Get a reference to the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get a reference to the proximity sensor
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Get a reference to the proximity text view
        proximityTextView = (TextView) findViewById(R.id.x_text_view);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register the sensor listener
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Unregister the sensor listener
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the proximity value from the sensor event
        float proximity = event.values[0];

        // Update the UI with the new value
        proximityTextView.setText("Proximity: " + proximity);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}