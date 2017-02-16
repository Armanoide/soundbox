package com.android.norbertbilla.soundbox.managers;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norbert on 15/02/2017.
 */

public class PermissionsManager {

    private static final int CODE_REQUEST_ALL_PERMISSION = 1;

    private Activity activity;
    private List<String> permissions = new ArrayList<>();

    public PermissionsManager(Activity activity, boolean ask)
    {
        if (ask) {
            this.activity = activity;
            getPermissions();
        }
    }


    public void getPermissions()
    {
        getPermissionToInternet();
        getPermissionToReceiveSMS();
        getPermissionToReadState();
        getPermissionToSendSMS();
        getPermissionToReadContact();
        if (!permissions.isEmpty())
            activity.requestPermissions(permissions.toArray(new String [0]), CODE_REQUEST_ALL_PERMISSION);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean getPermissionToReceiveSMS() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }
            permissions.add(Manifest.permission.RECEIVE_SMS);
        } else{
            return true;
        }
        return false;
    }



    @TargetApi(Build.VERSION_CODES.M)
    private boolean getPermissionToSendSMS() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            permissions.add(Manifest.permission.RECEIVE_SMS);
        } else{
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean getPermissionToInternet() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }
            permissions.add(Manifest.permission.INTERNET);
        } else{
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean getPermissionToReadState() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            permissions.add(Manifest.permission.READ_PHONE_STATE);
        } else{
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean getPermissionToReadContact() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            permissions.add(Manifest.permission.READ_CONTACTS);
        } else{
            return true;
        }
        return false;
    }

    private static void requestForPermissionAfterTime(final Activity activity)
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new PermissionsManager(activity, true);
            }
        }, 200);
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode,
                                                  @NonNull String permissions[],
                                                  @NonNull int[] grantResults) {

        if(requestCode == CODE_REQUEST_ALL_PERMISSION){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "permission granted", Toast.LENGTH_SHORT).show();
            } else {
                //Make Loop
                Toast.makeText(activity, "RECEIVE_SMS permission denied", Toast.LENGTH_SHORT).show();
                requestForPermissionAfterTime(activity);
            }
        }else{}

    }
}
