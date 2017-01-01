package com.looker.mpermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private PermissionListener mListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollector.removeActivity(this);
    }

    public void requestRuntimePermission(String[] permissions, PermissionListener listener){
        mListener = listener;

        Activity topActivity = ActivityCollector.getTopActivity();

        List<String> permissionList = new ArrayList<>();
        /**判断是否已经授权**/
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }
        }

        /**未授权就申请授权**/
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), 0);
        }else {
            mListener.permissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:
              if (grantResults.length > 0){
                  List<String> deniedPermissions = new ArrayList<>();
                  for (int i = 0; i < grantResults.length; i++) {
                      int grantResult = grantResults[i];
                      String permission = permissions[i];
                      if (grantResult != PackageManager.PERMISSION_GRANTED){
                          deniedPermissions.add(permission);
                      }
                  }
                  if (deniedPermissions.isEmpty()){
                      mListener.permissionGranted();
                  }else {
                      mListener.permissionDenied(deniedPermissions);
                  }
              }
                break;
            default:
                break;

        }
    }
}
