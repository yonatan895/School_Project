package com.example.school_app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BreakReminderService extends Service {
    private static final long INTERVAL = 30 * 60 * 1000; // 30 minutes in milliseconds
    private static final String TAG = "BreakReminderService";

    private final IBinder binder = new LocalBinder();
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new BreakReminderReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service started");
        // Schedule the broadcast to be sent every 30 minutes
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent broadcastIntent = new Intent(this, receiver.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), INTERVAL, pendingIntent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service stopped");
        // Cancel the scheduled broadcast
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent broadcastIntent = new Intent(this, receiver.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public class LocalBinder extends android.os.Binder {
         BreakReminderService getService() {
            return BreakReminderService.this;
        }
}
}