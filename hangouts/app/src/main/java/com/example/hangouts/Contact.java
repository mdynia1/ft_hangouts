package com.example.hangouts;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.*;

public class Contact {
    public static final String KEY_ID = "_id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String PHONE_NUMBER_FORMATED = "formatedphnoenumber";
    public static final String SMS_DATA = "savedSMS";
    public static final String EMAIL_ADDRESS = "email";
    public static final String KEY_IMAGE = "image";
    public static final String TIMESTEMP = "timestemp";
    public static final String TABLE_NAME = "Contacts";


    private String FirstName;
    private String Whole_row;
    private String LastName;
    private String PhoneNumber;
    private String SMSText;
    private String Avatar;
    private String Formatedphnoenumber;
    private String TimeStemp;
    private String id;
    private String EmailAddress;


    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            KEY_ID + " integer primary key autoincrement not null," + FIRST_NAME
            + " text," + LAST_NAME + " text," + PHONE_NUMBER
            + " text," + SMS_DATA + " text," + TIMESTEMP + " text," + KEY_IMAGE
            + " text," + PHONE_NUMBER_FORMATED + " text," + EMAIL_ADDRESS + " text"  + ")";

    public Contact() {
    }

    public Contact(Cursor c) {
        this.id = c.getString(0);
        this.FirstName = c.getString(1);
        this.LastName = c.getString(2);
        this.PhoneNumber = c.getString(3);
        this.SMSText = c.getString(4);
        this.TimeStemp = c.getString(5);
        this.Avatar = c.getString(6);
        this.Formatedphnoenumber = c.getString(7);
        this.EmailAddress = c.getString(8);
        this.Whole_row = this.FirstName + " " + this.LastName + " " + this.Formatedphnoenumber;
        this.Whole_row = this.Whole_row.toLowerCase();
    }

    public String getFirstName() {
        return (this.FirstName);
    }

    public String getLastName() {
        return (this.LastName);
    }

    public String getPhoneNumber() {
        return (this.PhoneNumber);
    }

    public String getSmsData() {
        return (this.SMSText);
    }

     public String getAvatarLink() {
        return (this.Avatar);
     }

    public String getKeyId() {
        return (this.id);
    }

    public String getWhole_row() {
        return (this.Whole_row);
    }

    public String getEmailAddress() {
        return (this.EmailAddress);
    }

    public String getTimeStemp() {
        return (this.TimeStemp);
    }

    public String getPhoneNumberFormated() {return (this.Formatedphnoenumber + this.getPhoneNumber());}
}
