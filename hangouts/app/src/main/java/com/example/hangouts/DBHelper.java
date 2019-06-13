package com.example.hangouts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactDB";

    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contact.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Contact.TABLE_NAME);
        onCreate(db);
    }

    public void deleteItem(SQLiteDatabase db, String id){
        db.delete(Contact.TABLE_NAME,"_id=?", new String[] { id});
    }

    public static void updateItem(SQLiteDatabase db, ContentValues contentValues, String id){
        db.update(Contact.TABLE_NAME, contentValues,"_id=?", new String[] { id});
    }


    public Cursor getContactRow(String id) {
        Cursor cur = getAllItems();
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            if (cur.getString(0).equals(id)){
                return cur;
            }
            cur.moveToNext();
        }
        return cur;
    }

    public Cursor getContactRowbyNumber(String Phone_number) {
        Cursor cur = getAllItems();
        if (cur.moveToFirst()) {
            while (cur.isAfterLast() == false) {
                if (cur.getString(3).equals(Phone_number)
                        || (cur.getString(7) + cur.getString(3)).equals(Phone_number)) {
                    return cur;
                }
                cur.moveToNext();
            }
        }
        return cur = null;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                Contact.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                "LOWER(firstName), firstName, LOWER(lastName), lastName"
        );
    }

    public List<Contact> search(String keyword) {
        List<Contact> contacts = new LinkedList<>();
        try {
            Cursor cursor = this.getAllItems();
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact(cursor);
                    if (contact.getWhole_row().contains(keyword)){
                        contacts.add(contact);
                    }
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(MainActivity.TAG, "exception");
            contacts = null;
        }
        return contacts;
    }

}
