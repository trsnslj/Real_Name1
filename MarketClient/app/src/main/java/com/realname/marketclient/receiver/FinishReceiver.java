package com.realname.marketclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class FinishReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //开启一个发送notification的service
      //  Intent intentService = new Intent(context, NotifyService.class);
      //  context.startService(intentService);
    }
}
