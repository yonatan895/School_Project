package com.example.school_app;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class BreakReminderReceiver extends BroadcastReceiver {
  /**
   * @param context The Context in which the receiver is running.
   * @param intent The Intent being received.
   */
  @Override
  public void onReceive(Context context, Intent intent) {
    // Create a notification to remind the user to take a break
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder builder =
        new NotificationCompat.Builder(context, "default")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Time to take a break!")
            .setContentText("You've been working for a while. Take a break and stretch your legs!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true);
    notificationManager.notify(0, builder.build());
  }
}
