package com.example.hancheng.nerdlauncher;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanch on 2017/5/23.
 */

public class Utility {

    public static final int REQ_PERMISSION = 1;

    public static boolean AndroidMReqPermission(Activity activity, String[] reqList) {
        List<String> permNotPassed = new ArrayList<>();
        for(String req : reqList) {
            int permRes = ContextCompat.checkSelfPermission(activity, req);
            if(permRes != PackageManager.PERMISSION_GRANTED) {
                permNotPassed.add(req);
            }
        }
        if(permNotPassed.isEmpty()) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    permNotPassed.toArray(new String[permNotPassed.size()]), REQ_PERMISSION);
            return false;
        }
    }

    public static void AndroidMOnPermResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION:
                if(grantResults.length > 0) {
                    AndroidMReqPermission(activity, permissions);
                }
                break;
            default:
                break;
        }
    }
}
