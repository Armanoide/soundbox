package com.example.manux.soundbox.async;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by norbert on 15/02/2017.
 */

public class SlackTokenAsync extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        InputStream is = null;
        HttpURLConnection connection = null;
        String responseString;
        URL url;
        try
        {
            url = new URL("https://slack.com/api/oauth.access?client_id=142059101747.141427169680&client_secret=9785c4d7b8f8933f9382726a8191fd24&grant_type=code");
            try
            {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setReadTimeout(10000 /* milliseconds */);
                connection.setConnectTimeout(15000 /* milliseconds */);
                // Starts the query
                connection.connect();
                // Get Response
                int statusCode = connection.getResponseCode();
                if (statusCode != 200 && statusCode != 201)
                {
                    is = connection.getErrorStream();
                } else {
                    is = connection.getInputStream();
                }
                responseString = readIt(is);

            } catch (IOException e) {

            } finally {
                if (is != null) {
                    try {is.close(); } catch (IOException exception) {};
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }

        } catch (MalformedURLException e){
            Log.d("SlackTokenAsync", e.getMessage());
        }
        return null;
    }

    private String readIt(InputStream is)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException exception) { }
        return stringBuilder.toString();
    }

}
