package com.example.hangouts;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import static com.example.hangouts.New_contact.isNumeric;
import static com.example.hangouts.New_contact.selectedImageUri;

public class Edit_contact extends AppCompatActivity {

    // Contact contact = new Contact("", "", "", "", "", "", "");
    String id  = null;
    private int PICK_IMAGE_REQUEST = 1;
    public static Bitmap bitmap;
    private  String image = "";
    private int Flag = 1;
    private static ImageView avatar;
    private String current_code = "+380";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(MainActivity.THEME);
        setContentView(R.layout.new_contact);

        ImageView edit = (ImageView) findViewById(R.id.buttonAdd);
        final EditText Phone_number = (EditText) findViewById(R.id.edittextNumber);
        final EditText First_name = (EditText) findViewById(R.id.edittextName);
        final EditText Email_address = (EditText) findViewById(R.id.edittextEmail);
        avatar = (ImageView)findViewById(R.id.imageView_User);

        if (getIntent().hasExtra("clicked_contact")) {
            id = getIntent().getStringExtra("clicked_contact");
        }
        final SQLiteDatabase db;
        final DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        final Cursor cursor = dbHelper.getContactRow(id);
        final Contact contact = new Contact(cursor);
        First_name.setText(contact.getFirstName());
        Phone_number.setText(contact.getPhoneNumber());
        Email_address.setText(contact.getEmailAddress());
        current_code = cursor.getString(7);
        setResult(RESULT_OK);

        image = Flag == 0 ? image : contact.getAvatarLink();
        if (!image.isEmpty()) {
            try {
                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                avatar.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.d(MainActivity.TAG, e + "");
            }
        }
        final ContentValues contentValues = new ContentValues();
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                if (!Email_address.getText().toString().isEmpty() &&
                        (!Email_address.getText().toString().contains("@") ||  !Email_address.getText().toString().contains("."))){
                    Toast.makeText(Edit_contact.this, "Invalid email address!", Toast.LENGTH_LONG).show();
                }
                else if (!First_name.getText().toString().isEmpty() && !Phone_number.getText().toString().isEmpty()
                        && isNumeric(Phone_number.getText().toString())) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    contentValues.put(Contact.FIRST_NAME, First_name.getText().toString());
                    contentValues.put(Contact.PHONE_NUMBER, Phone_number.getText().toString());
                    contentValues.put(Contact.PHONE_NUMBER_FORMATED, current_code);
                    contentValues.put(Contact.EMAIL_ADDRESS, Email_address.getText().toString());
                    contentValues.put(Contact.KEY_IMAGE, image);
                    DBHelper.updateItem(db, contentValues, cursor.getString(0));
                    Intent intent = new Intent(Edit_contact.this, Contact_field.class);
                    intent.putExtra("clicked_contact", id);
                    startActivity(intent);
                    finish();
                }
                else if (First_name.getText().toString().isEmpty()){
                    Toast.makeText(Edit_contact.this, "Please fill the field \"First Name\"", Toast.LENGTH_LONG).show();
                }
                else if (Phone_number.getText().toString().isEmpty() || !isNumeric(Phone_number.getText().toString())){
                    Toast.makeText(Edit_contact.this, "Invalid phone number", Toast.LENGTH_LONG).show();
                }
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
                Flag = 0;
            }
            catch (Exception e) {
                Log.d(MainActivity.TAG, "Exception");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Edit_contact.this, Contact_field.class);
        intent.putExtra("clicked_contact", id);
        super.onBackPressed();
        finish();
    }

}