package com.example.hangouts;

import android.Manifest.permission;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Send_sms extends AppCompatActivity {
    public static Context context;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    CircleImageView send;
    EditText textSms;
    static String id;
    public static List<Message> Massages_list = new ArrayList<Message>();
    private ListView listView;
    private Adapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(MainActivity.THEME);
        setContentView(R.layout.send_sms);

        textSms = (EditText) findViewById(R.id.editTextSMS);
        send = (CircleImageView) findViewById(R.id.buttonSend);
        RecyclerView.LayoutManager layoutManager;

        final SQLiteDatabase db;
        final DBHelper dbHelper = new DBHelper(this);
        if (getIntent().hasExtra("clicked_contact")) {
            id = getIntent().getStringExtra("clicked_contact");
        }
        Log.d(MainActivity.TAG, "11111");
        db = dbHelper.getWritableDatabase();
        final Cursor cursor = dbHelper.getContactRow(id);

        final Contact contact = new Contact(cursor);
        context = Send_sms.this;
        listView = (ListView) findViewById(R.id.recycler_view);
        Massages_list =  Message.SMS_List (this, contact.getSmsData());
        String image = contact.getAvatarLink();
        Bitmap bitmap = null;
        if (!image.isEmpty()) {
            try {
                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            } catch (Exception e) {
                Log.d(MainActivity.TAG, e + "");
            }
        }
        mAdapter = new SMS_Adapter(this, R.id.recycler_view, Massages_list, bitmap);
        listView.setAdapter((ListAdapter) mAdapter);
        listView.setSelection(Massages_list.size());
        if(checkPermission(permission.SEND_SMS)) {
            send.setEnabled(true);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {permission.SEND_SMS},
                    SEND_SMS_PERMISSION_REQUEST_CODE);
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to use this one ---------------------------
//                try {
//
//                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                    final String message = textSms.getText().toString();
//                    String phoneNo = contact.getPhoneNumber();
//                    textSms.setText("");
//                    Save_textSMS(message, id);
//
////                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
                //to use this one ---------------------------
                    final String message = textSms.getText().toString();
                    String phoneNo = contact.getPhoneNumber();
                        try {

                            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                            String SENT = "sent";
                            String DELIVERED = "delivered";

                            Intent sentIntent = new Intent(SENT);
                            /*Create Pending Intents*/
                            PendingIntent sentPI = PendingIntent.getBroadcast(
                                    getApplicationContext(), 0, sentIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT);

                            Intent deliveryIntent = new Intent(DELIVERED);

                            PendingIntent deliverPI = PendingIntent.getBroadcast(
                                    getApplicationContext(), 0, deliveryIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT);
                            /* Register for SMS send action */
                            registerReceiver(new BroadcastReceiver() {

                                @Override
                                public void onReceive(Context context, Intent intent) {
                                    String result = "";

                                    textSms.setText("");
                                    Save_textSMS(message, id);

                                    switch (getResultCode()) {

                                        case Activity.RESULT_OK:
                                            result = "Transmission successful";
                                            break;
                                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                            result = "Transmission failed";
                                            break;
                                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                                            result = "Radio off";
                                            break;
                                        case SmsManager.RESULT_ERROR_NULL_PDU:
                                            result = "No PDU defined";
                                            break;
                                        case SmsManager.RESULT_ERROR_NO_SERVICE:
                                            result = "No service";
                                            break;
                                    }

                                    Toast.makeText(getApplicationContext(), result,
                                            Toast.LENGTH_LONG).show();
                                }

                            }, new IntentFilter(SENT));
                            /* Register for Delivery event */
                            registerReceiver(new BroadcastReceiver() {

                                @Override
                                public void onReceive(Context context, Intent intent) {
                                    Toast.makeText(getApplicationContext(), "Delivered",
                                            Toast.LENGTH_LONG).show();

//                                    textSms.setText("");
//                                    Save_textSMS(message, id);

                                }

                            }, new IntentFilter(DELIVERED));

                            /*Send SMS*/
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNo, null, message, sentPI,
                                    deliverPI);
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(),
                                    ex.getMessage().toString(), Toast.LENGTH_LONG)
                                    .show();
                            ex.printStackTrace();
                        }
                    }
                });

            }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    send.setEnabled(true);
                }
                return;
            }
        }
    }


    private void Save_textSMS(String message, String position) {
        final SQLiteDatabase db;
        final DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        final Cursor cursor = dbHelper.getContactRow(position);

        Contact contact = new Contact(cursor);
        final ContentValues contentValues = new ContentValues();
        Timestamp ts=new Timestamp(System.currentTimeMillis());
        Date date=ts;
        String s = ts.toString().substring(0, 19);
        String savedSMS = contact.getSmsData() + "/////Delivered///" + message + "///" + s;
        contentValues.put(Contact.SMS_DATA, savedSMS);
        dbHelper.updateItem(db, contentValues, contact.getKeyId());
        Massages_list = Message.SMS_List(this, savedSMS);
        String image = contact.getAvatarLink();
        Bitmap bitmap = null;
        if (!image.isEmpty()) {
            try {
                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            } catch (Exception e) {
                Log.d(MainActivity.TAG, e + "");
            }
        }
        mAdapter = new SMS_Adapter(this, R.id.recycler_view, Massages_list, bitmap);
        listView.setAdapter((ListAdapter) mAdapter);
        listView.setSelection(Massages_list.size());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Send_sms.this, Contact_field.class);
        intent.putExtra("clicked_contact", id);
        super.onBackPressed();
        finish();
    }

}