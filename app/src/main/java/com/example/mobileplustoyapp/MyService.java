package com.example.mobileplustoyapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {


    IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    public int ADD(int num1, int num2){
        return num1 + num2;
    }

    public int SUB(int num1, int num2){
        return num1 - num2;
    }

    public int MUL(int num1, int num2){
        return num1 * num2;
    }

    public float DIV(int num1, int num2) {
        float ans = 0;
        try {
            ans = (float)num1 / num2;
        }catch (ArithmeticException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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