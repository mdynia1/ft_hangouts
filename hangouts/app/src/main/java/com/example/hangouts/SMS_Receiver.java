package com.example.hangouts;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.hangouts.MainActivity.*;
import static com.example.hangouts.MainActivity.context;
import static com.example.hangouts.MainActivity.dbHelper;
import static com.example.hangouts.Message.Massages_list;


public class SMS_Receiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    public static String message;
    public static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "this is context ---- "+ context.getApplicationContext() + "");
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            this.context = context;
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                message = sb.toString();
                SMS_Saver (message, sender);



            }
        }
    }

    public static void SMS_Saver(String message, String Phone_Number) {
        final DBHelper dbHelper = new DBHelper(context);
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = dbHelper.getContactRowbyNumber(Phone_Number);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        String savedSMS = "";

        String s = ts.toString().substring(0, 19);

        if (cursor == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Contact.FIRST_NAME, "Unknown");
            contentValues.put(Contact.LAST_NAME, "");
            if (Phone_Number.contains("+")) {
                Log.d(MainActivity.TAG, "---------" +  Country_code.get_Country(Phone_Number));
                contentValues.put(Contact.PHONE_NUMBER, Country_code.get_Country(Phone_Number));
                contentValues.put(Contact.PHONE_NUMBER_FORMATED, Country_code.get_Country_code(Phone_Number));
            }
            else {
                contentValues.put(Contact.PHONE_NUMBER, Phone_Number);
                contentValues.put(Contact.PHONE_NUMBER_FORMATED, "");
            }
            contentValues.put(Contact.SMS_DATA, "/////ReceivedUnknown///" + message + "///" + s);
            contentValues.put(Contact.KEY_IMAGE, "");
            savedSMS = "/////Received///" + message + "///" + s;
            database.insert(Contact.TABLE_NAME, null, contentValues);

        } else {
            Log.d(MainActivity.TAG, "reached here");
            Contact contact = new Contact(cursor);
            Log.d(MainActivity.TAG, "reached here");
            ContentValues contentValues = new ContentValues();
            savedSMS = contact.getSmsData() + "/////Received" + contact.getFirstName() + " " + contact.getLastName() + "///" + message + "///" + s;
            contentValues.put(Contact.SMS_DATA, savedSMS);
            MainActivity.dbHelper.updateItem(database, contentValues, contact.getKeyId());

        }

        Toast.makeText(context, "++++++++++++" + message, Toast.LENGTH_SHORT).show();
        updater();
    }

    private static void updater() {
        try {
            if (isTopActivity(Send_sms.context)) {
                Intent intent = new Intent(context, Send_sms.class);
                context.startActivity(intent);
                Toast.makeText(context, " Send_sms  --------------------", Toast.LENGTH_SHORT).show();
            }
            if (isTopActivity(context)) {
                mAdapter = new MyAdapter(MainActivity.context, dbHelper.getAllItems());
                recyclerView.setAdapter(mAdapter);
            }
        } catch (Exception e) {
            Log.d(TAG, "!!!!!!!!!" + e);
        }
    }

    public static boolean isTopActivity(Context context) {
        String packageName = "com.example.hangouts";
        if (context == null || TextUtils.isEmpty(packageName)) {
            return false;
        }

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo == null || tasksInfo.isEmpty()) {
            return false;
        }
        try {
            return packageName.equals(tasksInfo.get(0).topActivity.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
