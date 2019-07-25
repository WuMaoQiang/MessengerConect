package com.messenger.roomtest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //RoomDatabase提供直接访问底层数据库实现，我们通过定义抽象方法返回具体Dao
//然后进行数据库增删该查的实现。
    public abstract UserDao userDao();
}