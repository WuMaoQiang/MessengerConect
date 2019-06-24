package com.messenger.client;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.messenger.BuildConfig;

/**
 * author:  cc
 * TODO
 * date:   On  2019/6/24
 */
public class ClientMHandler extends Handler {

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.what == 1){
            String str = msg.getData().getString("reply");
            if (BuildConfig.DEBUG) Log.e("ClientMHandler", str);
        }
    }
}
