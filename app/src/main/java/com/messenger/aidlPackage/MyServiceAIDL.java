package com.messenger.aidlPackage;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import aidl.MyAIDLService;

/**
 * author:  cc
 * TODO
 * date:   On  2019/6/25
 */
public class MyServiceAIDL extends Service {

    MyAIDLService myAIDLService = new MyAIDLService.Stub() {
        @Override
        public String getString() throws RemoteException {
            return "方法获取的值 返回给客户端";
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myAIDLService.asBinder();
    }


}
