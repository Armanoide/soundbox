package com.example.manux.soundbox.managers;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by norbert on 15/02/2017.
 */

public class PermissionsManager {

    private static final int CODE_REQUEST_READ_SMS = 1;
    private static final int CODE_REQUEST_RECEIVE_SMS = 2;
    private static final int CODE_REQUEST_RECEIVE_INTERNET = 3;
    private static final int CODE_REQUEST_RECEIVE_READ_STATE = 4;
    private static final int CODE_REQUEST_READ_CONTACT = 5;
    private static final int CODE_REQUEST_WRITE_CONTACT = 6;
    private static final int CODE_REQUEST_SEND_SMS = 7;

    private Activity activity;

    public PermissionsManager(Activity activity, boolean ask)
    {
        if (ask) {
            this.activity = activity;
            getPermissions();
        }
    }


    public void getPermissions()
    {
        getPermissionToReadSMSInbox();
        getPermissionToReceiveSMS();
        getPermissionToInternet();
        getPermissionToReadState();
        getPermissionToReadContact();
        getPermissionToWriteContact();
    }

    // Called when the user is performing an action which requires the app to read the
    // sms inbox
    @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToReadSMSInbox() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            activity.requestPermissions(new String[]{Manifest.permission.READ_SMS},CODE_REQUEST_READ_SMS);
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToReceiveSMS() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            activity.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},CODE_REQUEST_RECEIVE_SMS);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToSendSMS() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            activity.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},CODE_REQUEST_SEND_SMS);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToInternet() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            activity.requestPermissions(new String[]{Manifest.permission.INTERNET},CODE_REQUEST_RECEIVE_INTERNET);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToReadState() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            activity.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},CODE_REQUEST_RECEIVE_READ_STATE);
        }
    }

  @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToReadContact() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            activity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},CODE_REQUEST_READ_CONTACT);
        }
    }

  @TargetApi(Build.VERSION_CODES.M)
    private void getPermissionToWriteContact() {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CONTACTS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            activity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},CODE_REQUEST_WRITE_CONTACT);
        }
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == CODE_REQUEST_READ_SMS) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(activity, "READ_SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(activity, "READ_SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


        if(requestCode == CODE_REQUEST_RECEIVE_SMS){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "RECEIVE_SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "RECEIVE_SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if(requestCode == CODE_REQUEST_RECEIVE_INTERNET){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "INTERNET permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "INTERNET permission denied", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if(requestCode == CODE_REQUEST_RECEIVE_READ_STATE){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "READ_STATE permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "READ_STATE permission denied", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if(requestCode == CODE_REQUEST_READ_CONTACT){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "READ CONTACT permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "READ CONTACT permission denied", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


        if(requestCode == CODE_REQUEST_WRITE_CONTACT){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "WRITE CONTACT permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "WRITE CONTACT permission denied", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if(requestCode == CODE_REQUEST_SEND_SMS){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "SEND SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "SEND SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Make Loop// activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
