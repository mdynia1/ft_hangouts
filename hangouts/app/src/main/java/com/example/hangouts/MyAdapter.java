package com.example.hangouts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static java.lang.Integer.parseInt;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Cursor myCursor;
    private Context context;

    public MyAdapter(Context context, Cursor cursor) {
        this.myCursor = cursor;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_of_elements, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (!myCursor.moveToPosition(position)) {
            return;
        }

        Contact contact = new Contact(myCursor);
        final String name = contact.getFirstName();
        final String number = contact.getPhoneNumberFormated();
        viewHolder.textViewName.setText(name);
        viewHolder.textViewNumber.setText(number);
        final int position2 = position;


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = dbHelper.getAllItems();
                cursor.moveToFirst();
                cursor.move(position2);
                Contact contact = new Contact(cursor);
                ClickedContact(contact.getKeyId());

            }
        });
    }

    private void ClickedContact(String position)
    {
        int requestcode = 1;
        Intent intent = new Intent(context, Contact_field.class);
        intent.putExtra("clicked_contact", position);
        ((Activity) context).startActivityForResult(intent, requestcode);
    }

    @Override
    public int getItemCount() {
        return myCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewName;
        public TextView textViewNumber;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.First_name);
            textViewNumber = (TextView) itemView.findViewById(R.id.Phone_number);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, Contact_field.class);
                        DBHelper dbHelper = new DBHelper(context);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        Cursor cursor = dbHelper.getAllItems();
                        cursor.moveToFirst();
                        cursor.move(pos);
                        final Contact contact = new Contact(cursor);
                        ClickedContact(contact.getKeyId());
                    }
                }
            });
        }
    }

}
