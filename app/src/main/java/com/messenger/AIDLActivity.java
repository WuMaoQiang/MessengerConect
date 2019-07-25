package com.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.messenger.roomtest.RoomTestActivity;

import aidl.MyAIDLService;

/**
 * author:  cc
 * TODO
 * date:   On  2019/6/25
 */
public class AIDLActivity extends Activity {
    private MyAIDLService myAIDLService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                String str = myAIDLService.getString();
                mTvData.setText(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAIDLService = null;
        }
    };
    private TextView mTvData;
    private Button mButton;
    private Button mUnbind;
    private Button mButton2;
    private Button button10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidl);
        initView();
    }

    private void initView() {
        mTvData = (TextView) findViewById(R.id.tv_data);
        mButton = (Button) findViewById(R.id.button);
        mUnbind = (Button) findViewById(R.id.unbind);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.messenger.aidlPackage.MyServiceAIDL");
                //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
                intent.setPackage("com.messenger");
                bindService(intent, connection, BIND_AUTO_CREATE);

            }
        });
        mUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.canvassdk.action.MAIN");
                intent.setClassName("com.messenger","com.messenger.roomtest.RoomTestActivity");
                startActivity(intent);
            }
        });
    }
}
