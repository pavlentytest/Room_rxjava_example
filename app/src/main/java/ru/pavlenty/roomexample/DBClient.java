package ru.pavlenty.roomexample;

import android.content.Context;

import androidx.room.Room;

public class DBClient {

    private Context mCtx;
    private static DBClient mInstance;

    private AppDB appDatabase;

    private DBClient(Context mCtx) {
        this.mCtx = mCtx;


        //Создание БД - MyToDos
        appDatabase = Room.databaseBuilder(mCtx, AppDB.class, "MyToDos").build();
    }

    public static synchronized DBClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DBClient(mCtx);
        }
        return mInstance;
    }

    public AppDB getAppDatabase() {
        return appDatabase;
    }
}