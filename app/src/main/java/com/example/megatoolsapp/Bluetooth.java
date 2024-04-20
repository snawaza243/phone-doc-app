package com.example.megatoolsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class Bluetooth extends AppCompatActivity {

    TextView bluetoothTextView;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bluetoothTextView = findViewById(R.id.bluetoothText);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            bluetoothTextView.setText("Bluetooth not supported on this device");
        } else {
            // Check if Bluetooth is enabled
            if (!bluetoothAdapter.isEnabled()) {
                // Bluetooth is not enabled, prompt user to enable it
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            } else {
                // Bluetooth is enabled, display Bluetooth information
                try {
                    String bluetoothInfo = getBluetoothInfo();
                    bluetoothTextView.setText(bluetoothInfo);
                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // Check if Bluetooth is enabled after user interaction
            if (resultCode == RESULT_OK) {
                // Bluetooth is enabled, display Bluetooth information
                try {
                    String bluetoothInfo = getBluetoothInfo();
                    bluetoothTextView.setText(bluetoothInfo);
                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                // Bluetooth enabling was cancelled, inform the user
                Toast.makeText(this, "Bluetooth must be enabled to view Bluetooth information", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getBluetoothInfo() throws Exception {
        StringBuilder info = new StringBuilder();
        if (checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            // Request the missing permissions
            requestPermissions(new String[]{Manifest.permission.BLUETOOTH}, 1);
            throw new Exception("Bluetooth permission not granted");
        }
        info.append("Name: ").append(bluetoothAdapter.getName()).append("\n");
        info.append("Address: ").append(bluetoothAdapter.getAddress()).append("\n");
        info.append("State: ");
        if (bluetoothAdapter.isEnabled()) {
            info.append("Enabled");
        } else {
            info.append("Disabled");
        }
        info.append("\n");

        // Additional Bluetooth hardware information
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(bluetoothAdapter.getAddress());
        if (device != null) {
            info.append("Bluetooth Class: ").append(getBluetoothClass(device)).append("\n");
        } else {
            info.append("Bluetooth Class: Unknown\n");
        }

        return info.toString();
    }

    private String getBluetoothClass(BluetoothDevice device) {
        int deviceClass = device.getBluetoothClass().getMajorDeviceClass();
        switch (deviceClass) {
            case BluetoothClass.Device.Major.UNCATEGORIZED:
                return "Uncategorized";
            case BluetoothClass.Device.Major.COMPUTER:
                return "Computer";
            case BluetoothClass.Device.Major.PHONE:
                return "Phone";
            case BluetoothClass.Device.Major.IMAGING:
                return "Imaging";
            case BluetoothClass.Device.Major.NETWORKING:
                return "Networking";
            case BluetoothClass.Device.Major.PERIPHERAL:
                return "Peripheral";
            case BluetoothClass.Device.Major.AUDIO_VIDEO:
                return "Audio/Video";
            case BluetoothClass.Device.Major.WEARABLE:
                return "Wearable";
            case BluetoothClass.Device.Major.TOY:
                return "Toy";
            case BluetoothClass.Device.Major.HEALTH:
                return "Health";
            default:
                return "Unknown";
        }
    }
}
