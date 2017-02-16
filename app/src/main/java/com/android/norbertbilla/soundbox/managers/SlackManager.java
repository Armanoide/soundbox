package com.android.norbertbilla.soundbox.managers;

import android.os.AsyncTask;
import com.fasterxml.jackson.databind.JsonNode;
import allbegray.slack.SlackClientFactory;
import allbegray.slack.rtm.Event;
import allbegray.slack.rtm.EventListener;
import allbegray.slack.rtm.SlackRealTimeMessagingClient;
import allbegray.slack.type.Channel;
import allbegray.slack.webapi.SlackWebApiClient;

/**
 * Created by norbert on 15/02/2017.
 */

public class SlackManager extends AsyncTask<Void, Void, Void> {

    private  SlackWebApiClient mWebApiClient;
    private String message = null;
    private String channelName = "suprise";

    private void initConnectionSlack()
    {
        final SlackRealTimeMessagingClient mRtmClient;
        final String slackToken = "xoxp-142059101747-142059101907-141466574320-0a8cbcb2f30d66e4af4c51161aba8651";

        if (mWebApiClient == null) {
            mWebApiClient = SlackClientFactory.createWebApiClient(slackToken);
            String webSocketUrl = mWebApiClient.startRealTimeMessagingApi().findPath("url").asText();

            mRtmClient = new SlackRealTimeMessagingClient(webSocketUrl);
            mRtmClient.connect();
            mRtmClient.addListener(Event.HELLO, new EventListener() {
                @Override
                public void onMessage(JsonNode jsonNode) {
                    Channel channel = mWebApiClient.joinChannel(channelName);
                    if (channel != null && message != null) {
                        mWebApiClient.postMessage(channel.getId(), message);
                        mRtmClient.close();
                    }
                }
            });
        }
    }

    @Override
    protected Void doInBackground(Void... params) {

        initConnectionSlack();
        return null;
    }

    public SlackManager(String  message)
    {
        this.message = message;
    }

}
