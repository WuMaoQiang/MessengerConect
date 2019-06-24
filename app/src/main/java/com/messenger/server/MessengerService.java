package com.messenger.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

/**
 * author:  cc
 * TODO
 * date:   On  2019/6/24
 */
public class MessengerService extends Service {

    Messenger messenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}