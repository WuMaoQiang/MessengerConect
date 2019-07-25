package com.messenger.roomtest;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class RoomTestActivity extends Activity implements View.OnClickListener {
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private UserDao mUserDao;
    private static final String TAG = "RoomTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomtest);

        initView();
        //得到AppDatabase 对象
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "roomDemo-database")
                //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                //他可能造成主线程lock以及anr
                //所以我们的操作都是在新线程完成的
                // .allowMainThreadQueries()
                .build();
        //得到userDao对象
        mUserDao = db.userDao();
    }

    private void initView() {
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                insertOne();
                break;
            case R.id.button4:
                insertSome();
                break;
            case R.id.button5:
//                updataOne();
                break;
            case R.id.button6:
//                deleteOne();
                break;
            case R.id.button7:
//                findOne();
                break;
            case R.id.button8:
                findAll();
                break;
            case R.id.button9:
                deleteAll();
                break;
        }
    }

    private void deleteAll() {

        //防止UI线程阻塞以及ANR,所有的数据库操作，推荐开启新的线程访问。
        new Thread(new Runnable() {
            @Override
            public void run() {
                //返回的是插入元素的primary key index
                int i = mUserDao.deleteAll(mUserDao.getAll());

                String msg = "i======= " + i;
                Log.i(TAG, msg);
            }
        }).start();
    }

    private void findAll() {
        //防止UI线程阻塞以及ANR,所有的数据库操作，推荐开启新的线程访问。
        new Thread(new Runnable() {
            @Override
            public void run() {
                //返回的是插入元素的primary key index
                List<User> users = mUserDao.getAll();
                if (users.size() > 0) {

                    for (User u : users) {
                        String msg = "getall " + u.getUid();
                            Log.i(TAG, msg);
                    }


                } else {
                    String msg = "insert one fail ";
                    Log.i(TAG, msg);
                }
            }
        }).start();
    }

    private void insertOne() {
        //防止UI线程阻塞以及ANR,所有的数据库操作，推荐开启新的线程访问。
        final User user = new User(888888);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //返回的是插入元素的primary key index
                Long aLong = mUserDao.insert(user);
                if (aLong > 0) {
                    String msg = "insert one success, index is " + aLong.toString();
                    Log.i(TAG, msg);
                } else {
                    String msg = "insert one fail ";
                    Log.i(TAG, msg);
                }
            }
        }).start();
    }

    private void insertSome() {
        //防止UI线程阻塞以及ANR,所有的数据库操作，推荐开启新的线程访问。
        final List<User> list = new ArrayList<>();
        list.add(new User(4546));
        list.add(new User(789789));
        new Thread(new Runnable() {
            @Override
            public void run() {
                //返回的是插入元素的primary key index
                List<Long> longs = mUserDao.insertAll(list);
                if (longs.size() > 0) {
                    String msg = "insert one success, index is " + longs.get(0);
                    Log.i(TAG, msg);
                } else {
                    String msg = "insert one fail ";
                    Log.i(TAG, msg);
                }
            }
        }).start();
    }

}
