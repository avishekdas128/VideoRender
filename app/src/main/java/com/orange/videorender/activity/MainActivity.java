package com.orange.videorender.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import com.orange.videorender.R;

public class MainActivity extends Activity implements OnClickListener {

    @Override public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_gallery).setOnClickListener(this);
        findViewById(R.id.button_camera).setOnClickListener(this);
    }

    @Override public void onClick(final View v) {
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PermissionChecker.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA },
                    v.getId());
        } else {
            startActivity(v.getId());
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                     int[] grantResults) {
        if (grantResults.length != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(requestCode);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void startActivity(int id) {
        switch (id) {
            case R.id.button_gallery:
                startActivity(new Intent(this, GalleryActivity.class));
                break;
            case R.id.button_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;

            default:
                break;
        }
    }
}

