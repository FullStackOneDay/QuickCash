package com.example.quick_cash_grp13;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

public class CreateNotificationChannel extends ContextWrapper {

    private static CharSequence name;
    private static String id;
    private static int importance;

    public CreateNotificationChannel(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createJobPostingResponseChannel();
        }
    }

    // Create a notification channel for when an employer receives response from potential employee
    @TargetApi(Build.VERSION_CODES.O)
    private void createJobPostingResponseChannel() {
        name = getString(R.string.job_posting_response);
        id = getString(R.string.job_posting_response_id);
        importance = NotificationManager.IMPORTANCE_HIGH; // Assume that these are important notifications for employers

        // Create a NotificationChannel
        NotificationChannel jobResponseChannel = new NotificationChannel(id, name, importance);

        // Create a NotificationManager and register the channel with the system
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(jobResponseChannel);
    }

    public static CharSequence getNotificationName() {
        return name;
    }

    public static String getNotificationId() {
        return id;
    }

    public static int getNotificationImportance() {
        return importance;
    }
}
