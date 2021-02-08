package com.example.mobileplustoyapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "태그입니다";
    IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    public int getAns(int a, int b){
        return a + b;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "서비스 onBind에서 시작");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "서비스 시작");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}