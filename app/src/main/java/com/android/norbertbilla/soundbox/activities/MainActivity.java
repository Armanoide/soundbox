package com.android.norbertbilla.soundbox.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.norbertbilla.soundbox.R;
import com.android.norbertbilla.soundbox.async.SlackTokenAsync;
import com.android.norbertbilla.soundbox.managers.PermissionsManager;
import com.android.norbertbilla.soundbox.managers.SlackManager;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView telNumber;
    private MediaPlayer mPlayer = null;
    private PermissionsManager permissionsManager = null;
    private int selectedSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionsManager = new PermissionsManager(MainActivity.this, true);

/////////////////////////
        Button Yeay = (Button) findViewById(R.id.Yeay);                                             //yeay
        Yeay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.yeay);
            }
        });

        Button Yugiho = (Button) findViewById(R.id.Yugiho);                                          //yugiho
        Yugiho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.yugiho);
            }
        });

        Button Tequilaheineken = (Button) findViewById(R.id.Tequilaheineken);                       //tequilaheineken
        Tequilaheineken.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.tequilaheineken);
            }
        });

        Button Tagueule = (Button) findViewById(R.id.Tagueule);                                     //tagueule
        Tagueule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.tagueule);
            }
        });

        Button Posezauparcduluxembourg = (Button) findViewById(R.id.Posezauparcduluxembourg);                                       //posezauparcduluxembourg
        Posezauparcduluxembourg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.posezauparcduluxembourg);
            }
        });

        Button GG = (Button) findViewById(R.id.GG);                                                 //GG
        GG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.gg);
            }
        });

        Button Boitdusprite = (Button) findViewById(R.id.Boitdusprite);                             //Boitdusprite
        Boitdusprite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.boitdusprite);
            }
        });

        Button Hein = (Button) findViewById(R.id.Hein);                                             //Hein
        Hein.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.hein);
            }
        });

        Button Jevaisyaller = (Button) findViewById(R.id.Jevaisyaller);                             //Je vais yaller
        Jevaisyaller.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.jevaisyaller);
            }
        });

        Button Jenaimarre = (Button) findViewById(R.id.Jenaimarre);                                 //j'en ai marre
        Jenaimarre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.jenaimarre);
            }
        });

        Button Keskiya = (Button) findViewById(R.id.Keskiya);                                       //Keskiya
        Keskiya.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.keskiya);
            }
        });

        Button Unplan = (Button) findViewById(R.id.Unplan);                                         //j'ai un plan
        Unplan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.unplan);
            }
        });

        Button Moundirsvp = (Button) findViewById(R.id.Moundirsvp);
        Moundirsvp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.moundirsvp);
            }
        });

        Button Situtecoupes = (Button) findViewById(R.id.Situtecoupes);
        Situtecoupes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.situtecoupes);
            }
        });

        Button Steven = (Button) findViewById(R.id.Steven);
        Steven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.steven);
            }
        });

        Button Fermela = (Button) findViewById(R.id.Fermela);
        Fermela.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.fermela);
            }
        });

        Button Vent = (Button) findViewById(R.id.Vent);
        Vent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playSound(R.raw.vent);
            }
        });

        findViewById(R.id.sendSms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        ////////////////////////////////////////////////////

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.onRequestPermissionsResult(MainActivity.this, requestCode, permissions, grantResults);
    }


    void sendMessage()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED )  {

            this.permissionsManager.getPermissions();
            return;
        }

        if (telNumber == null) {
            telNumber = (AutoCompleteTextView) findViewById(R.id.textview_tel);
        }


        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Vous êtes prenium ?");
        dialog.setMessage("L'envoie de sms est réserver au membre Prenium" );
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

        {
            /*
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");

            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address"  , telNumber.getText());
            smsIntent.putExtra("sms_body"  , "music");
            Toast.makeText(this, "MMS Envoyé", Toast.LENGTH_SHORT).show();
             */
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayer = null;
    }

    @Override
    public void onPause(){
        super.onPause();
        try {

            if(mPlayer != null){
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
            }
        } catch (IllegalStateException e) {

        }
    }


    private void playSound(int resId) {
        if (mPlayer != null) {
            selectedSong = resId;
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        mPlayer = MediaPlayer.create(MainActivity.this, resId);
        mPlayer.start();
    }

}
