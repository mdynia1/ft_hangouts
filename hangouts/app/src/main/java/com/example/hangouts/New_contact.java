package com.example.hangouts;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.sql.Timestamp;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.BitmapFactory.decodeFile;

public class New_contact extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;

    private String image = "";

    private static String  current_code = "+380";
    private CircleImageView avatar;
    public static Bitmap bitmap;


    public static Uri selectedImageUri;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(MainActivity.THEME);
        setContentView(R.layout.new_contact);

        final DBHelper dbHelper = new DBHelper(this);
        ImageView add = (ImageView) findViewById(R.id.buttonAdd);
        avatar = (CircleImageView) findViewById(R.id.imageView_User);
        final EditText Phone_number = (EditText) findViewById(R.id.edittextNumber);
        final EditText First_name = (EditText) findViewById(R.id.edittextName);
        final EditText Email_address = (EditText) findViewById(R.id.edittextEmail);
        final SQLiteDatabase database = MainActivity.dbHelper.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();

        current_code = MainActivity.current_countrycode;
        Phone_number.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Phone_number.setText(current_code);
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (!Email_address.getText().toString().isEmpty() &&
                        (!Email_address.getText().toString().contains("@") ||  !Email_address.getText().toString().contains("."))){
                    Toast.makeText(New_contact.this, "Invalid email address!", Toast.LENGTH_LONG).show();
                }
                else if (!First_name.getText().toString().isEmpty() && !Phone_number.getText().toString().isEmpty()) {
                    contentValues.put(Contact.FIRST_NAME, First_name.getText().toString());
                    contentValues.put(Contact.PHONE_NUMBER, Phone_number.getText().toString());
                    contentValues.put(Contact.EMAIL_ADDRESS, Email_address.getText().toString());
                    contentValues.put(Contact.PHONE_NUMBER_FORMATED, current_code);
                    contentValues.put(Contact.SMS_DATA, "");
                    contentValues.put(Contact.KEY_IMAGE, image);
                    database.insert(Contact.TABLE_NAME, null, contentValues);
                    setResult(RESULT_OK);
                    finish();
                }
                else if (First_name.getText().toString().isEmpty()){
                    Toast.makeText(New_contact.this, "Please fill the field \"First Name\"!", Toast.LENGTH_LONG).show();
                }
                else if (Phone_number.getText().toString().isEmpty()){
                    Toast.makeText(New_contact.this, "Please fill the field \"Phone_number\"!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        if (requestCode == PICK_IMAGE_REQUEST) {
            selectedImageUri = data == null ? null : data.getData();
            try {

                image = selectedImageUri.toString();
                bitmap = Utility.decodeUri(Uri.parse(image), 150, this);

                image = Utility.encodeTobase64(bitmap);
                avatar.setImageBitmap(bitmap);
            }
            catch (Exception e) {
                Log.d(MainActivity.TAG, "Exception");
            }
        }
    }


    public static boolean isNumeric(String str) {

        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }
}
