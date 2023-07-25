package com.example.megatoolsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Airplane extends AppCompatActivity {

    private TextView x, y, z;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout
                .layout.activity_airplane);

        x = findViewById(R.id.x_text_view);
        x.setText("Module: ");

        y = findViewById(R.id.y_text_view);
        y.setText("Condition: ");

        z = findViewById(R.id.z_text_view);
        z.setText("Airplane Mode: ");

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the flight mode settings
                startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));


            }
        });

//        check airplane mode is turn off or on
        boolean isAirplaneModeOn = Settings.System.getInt(getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        if (isAirplaneModeOn) {
//                    Toast.makeText(this, "Airplane mode is turned ON", Toast.LENGTH_SHORT).show();
            z.setText("Airplane mode: ON");
        } else {
//                    Toast.makeText(this, "Airplane mode is turned OFF", Toast.LENGTH_SHORT).show();
            z.setText("Airplane mode: OFF");
        }


    }

    private String moduleCondition() {
        String a = "";
        String b = "";
        // Check if airplane mode is supported
        boolean isAirplaneModeSupported = Settings.System.getInt(getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 1 || Settings.System.getInt(getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) != 1;


        if (isAirplaneModeSupported) {
            Toast.makeText(this, "Airplane mode is supported on this device", Toast.LENGTH_SHORT).show();
//            x.setText("Module: Supported");
//            y.setText("Condition: Working");
            a = "Module: Supported";
            b = "Condition: Working";
            String c = a + "\n" + b;

            return c;


        } else {
            Toast.makeText(this, "Airplane mode is not supported on this device", Toast.LENGTH_SHORT).show();
//            x.setText("Module: Not Supported");
//            y.setText("Condition: Not Working");
            a = "Module: Not Supported";
            b = "Condition: Not Working";
            String c = a + "\n" + b;

            return c;
        }
    }
}