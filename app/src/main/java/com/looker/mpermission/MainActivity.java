package com.looker.mpermission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity {

    String[] permissions = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void getPermissions(View view) {
        requestRuntimePermission(permissions, new PermissionListener() {
            @Override
            public void permissionGranted() {
                Toast.makeText(MainActivity.this, "所有权限均已授权！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void permissionDenied(List<String> deniedPermissions) {
                if (!deniedPermissions.isEmpty()){
                    for (String deniedPermission : deniedPermissions) {
                        Toast.makeText(MainActivity.this, "被拒绝的权限为： " + deniedPermission, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
