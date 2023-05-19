package com.example.break_reminder;

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

  /**
   * The onBind function is used to bind the service to an activity. This function returns a binder
   * object that allows the activity to access public methods in this service. The binder object is
   * created by instantiating a class that extends Binder and overriding its onBind method.
   *
   * @param intent Pass data to the service
   * @return An instance of the ibinder class
   */
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

  /**
   * The onStartCommand function is called when the service is started. It schedules a repeating
   * alarm to send a broadcast every 30 minutes.
   *
   * @param intent Pass data to the service
   * @param flags Indicate how the service should be started
   * @param startId Identify the start request
   * @return Start_sticky, which means that the service will be restarted if it is killed
   */
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.i(TAG, "Service started");
    // Schedule the broadcast to be sent every 30 minutes
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    Intent broadcastIntent = new Intent(this, receiver.getClass());
    PendingIntent pendingIntent =
        PendingIntent.getBroadcast(
            this,
            0,
            broadcastIntent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), INTERVAL, pendingIntent);
    return START_STICKY;
  }

  /**
   * The onDestroy function is called when the service is no longer used and is being destroyed. It
   * stops the scheduled broadcast, which in turn stops the alarm from going off.
   */
  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(TAG, "Service stopped");
    // Cancel the scheduled broadcast
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    Intent broadcastIntent = new Intent(this, receiver.getClass());
    PendingIntent pendingIntent =
        PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_IMMUTABLE);
    alarmManager.cancel(pendingIntent);
  }

  public class LocalBinder extends android.os.Binder {
    /**
     * Gets service.
     *
     * @return the service
     */
    BreakReminderService getService() {
      return BreakReminderService.this;
    }
  }
}
