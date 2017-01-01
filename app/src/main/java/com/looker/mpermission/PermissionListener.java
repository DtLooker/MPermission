package com.looker.mpermission;

import java.util.List;

/**
 * Created by looker on 2017/1/1.
 */

public interface PermissionListener {

    void permissionGranted();

    void permissionDenied(List<String> deniedPermissions);
}
