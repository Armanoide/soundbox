package com.example.manux.soundbox.managers;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.manux.soundbox.async.MailSendAsync;

/**
 * Created by norbert on 15/02/2017.
 */

public class SmsReceiverManager extends  BroadcastReceiver {

    private static String getContactName(Context context, String phoneNumber) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
            Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
            if (cursor == null) {
                return null;
            }
            String contactName = null;
            if(cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }

            if(cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

            return contactName;
        }

        return null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++){
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                TelephonyManager tMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                String mPhoneNumber = tMgr.getLine1Number();

                for (SmsMessage message : messages){

                    String strMessageFrom = message.getDisplayOriginatingAddress();
                    String strMessageBody = message.getDisplayMessageBody();

                    new MailSendAsync(mPhoneNumber, strMessageFrom, SmsReceiverManager.getContactName(context,mPhoneNumber),  strMessageBody).execute();
                }
            }
        }

    }
}