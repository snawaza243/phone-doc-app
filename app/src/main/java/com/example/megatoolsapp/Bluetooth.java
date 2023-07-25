package com.example.megatoolsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Bluetooth extends AppCompatActivity {
    private TextView bt1, bt2, bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bt1 = (TextView) findViewById(R.id.x_text_view);
        bt2 = (TextView) findViewById(R.id.y_text_view);
        bt3 = (TextView) findViewById(R.id.z_text_view);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device does not support Bluetooth
            bt1.setText("Module: Bluetooth Not supported");
            bt2.setText("Condition: Not supported");

        } else {
            bluetoothAdapter.isEnabled();
            bt1.setText("Module: Bluetooth Module Supported");
            bt2.setText("Condition: Working");

        }

        if (!bluetoothAdapter.isEnabled()) {
            bt3.setText("Status: Off");
        }
        else {
            bt3.setText("Status: On");
        }
    }

    public void openBluetoothSettings(View view) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
        startActivity(intent);
    }
}
