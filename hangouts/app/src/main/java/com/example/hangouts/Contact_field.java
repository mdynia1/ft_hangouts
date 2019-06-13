package com.example.hangouts;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

public class Contact_field extends AppCompatActivity {
    public static String position = "-1";
    private final static int REQUEST_CALL = 1;
    private TextView Phone_number;
    private ImageView avatar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(MainActivity.THEME);
        setContentView(R.layout.contact_field);
        final DBHelper dbHelper = new DBHelper(this);

        if (getIntent().hasExtra("clicked_contact")) {
            position = getIntent().getStringExtra("clicked_contact");
        }
        final Cursor cursor = dbHelper.getContactRow(position);
        final Contact contact = new Contact(cursor);
        ImageView delete = (ImageView) findViewById(R.id.buttonDelete);
        ImageView edit = (ImageView) findViewById(R.id.buttonEdit);
        ImageView sms = (ImageView) findViewById(R.id.buttonSMS);
        //ImageView email = (ImageView) findViewById(R.id.buttonEmail);
        ImageView phone_call = (ImageView) findViewById(R.id.phone_call);
        avatar = (ImageView) findViewById(R.id.avatar);
        Phone_number = (TextView) findViewById(R.id.textViewPhoneNumber);
        final TextView First_name = (TextView) findViewById(R.id.textViewFirstName);
        //final TextView Email_address = (TextView) findViewById(R.id.textViewEmail);
        Phone_number.setText(contact.getPhoneNumberFormated());
        //Email_address.setText(contact.getEmailAddress());
//        if (contact.getEmailAddress() != null && !contact.getEmailAddress().isEmpty()) {
//            email.setVisibility(View.VISIBLE);
//        }
        First_name.setText(contact.getFirstName().isEmpty() ? "" : contact.getFirstName());
        String image = contact.getAvatarLink();

        if (!image.isEmpty()) {
            try {
                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                avatar.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.d(MainActivity.TAG, e + "");
            }
        }

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                dbHelper.deleteItem(db1, contact.getKeyId());
                setResult(RESULT_OK);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Contact_field.this, Edit_contact.class);
                intent.putExtra("clicked_contact", contact.getKeyId());
                startActivity(intent);
                setResult(RESULT_OK);
                finish();
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Contact_field.this, Send_sms.class);
                intent.putExtra("clicked_contact", contact.getKeyId());
                startActivity(intent);
            }
        });

        phone_call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                makePhoneCall();
            }
        });

//        Email_address.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{contact.getEmailAddress()});
//                try {
//                    startActivity(Intent.createChooser(i, "Send mail..."));
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(Contact_field.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void makePhoneCall()
    {
        String number = Phone_number.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Contact_field.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contact_field.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        }
        else{
            Toast.makeText(Contact_field.this, "bla bla vka fds", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(Contact_field.this, "Permission DENIED", Toast.LENGTH_LONG).show();
            }
        }
    }

}
