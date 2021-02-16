package com.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.example.server.IServiceCallback;
import com.example.server.IServiceInterface;

public class MyService extends Service {

    boolean result;
    {
        try {
            result = this.mServiceInterface.isAvailable();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IServiceCallback.Stub mServiceCallback = new IServiceCallback.Stub() {
        @Override
        public void onServiceStateChanged(int status) throws RemoteException {
            Toast.makeText(getApplicationContext(),
                    "Service State Changed", Toast.LENGTH_LONG).show();
        }
    };

    public IServiceCallback getServiceCallback(){
        return mServiceCallback;
    }

    IServiceInterface mServiceInterface;
    public IServiceInterface.Stub mBinder = new IServiceInterface.Stub() {
        @Override
        public boolean isAvailable() throws RemoteException {
            return result;
        }

        @Override
        public boolean registerCallback(IServiceCallback callback) throws RemoteException {
            return false;
        }

        @Override
        public boolean unregisterCallback(IServiceCallback callback) throws RemoteException {
            return false;
        }
    };

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