package com.valuor.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleFlashLightOnOff;
    private CameraManager cameraManager;
    private String getCameraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleFlashLightOnOff = findViewById(R.id.toggle_flashlight);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toggleFlashLight(View view) {
        if (toggleFlashLightOnOff.isChecked()) {

            try {
                cameraManager.setTorchMode(getCameraID, true);
                Toast.makeText(MainActivity.this, "Lanterna ligada.", Toast.LENGTH_SHORT).show();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        } else {

            try {
                cameraManager.setTorchMode(getCameraID, false);
                Toast.makeText(MainActivity.this, "Lanterna desligada.", Toast.LENGTH_SHORT).show();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }
    }

}