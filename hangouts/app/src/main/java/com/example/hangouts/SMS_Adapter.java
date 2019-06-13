package com.example.hangouts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SMS_Adapter extends ArrayAdapter<Message> {

    private List<Message>  Massages_list;
    private Context context;
    private int resourceLayout;
    private Bitmap bitmap;
    private int pos = 0;

    public SMS_Adapter(Context context, int resource, List<Message> Massages_list, Bitmap bitmap) {
        super(context, resource, Massages_list);
        this.Massages_list = Massages_list;
        this.context = context;
        this.bitmap = bitmap;
        this.resourceLayout = resource;
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

         TextView textViewName;
         TextView textViewText;
         TextView textViewTime;
        CircleImageView imageView;
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = Massages_list.get(position);

        if (message.getStatus().equals("Delivered")) {
            convertView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.my_message, viewGroup, false);
        }
        else {
            convertView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.received_sms, viewGroup, false);
            if (this.bitmap != null) {
                imageView = (CircleImageView) convertView.findViewById(R.id.avatar_image);
                imageView.setImageBitmap(this.bitmap);
            }
        }
       textViewName = (TextView) convertView.findViewById(R.id.text_message_name);
       textViewText = (TextView) convertView.findViewById(R.id.text_message_body);
       textViewTime = (TextView) convertView.findViewById(R.id.text_message_time);
       textViewName.setText(message.getStatus());
       textViewText.setText(message.getMessage());
       textViewTime.setText(message.getTime());
        return convertView;
    }


}
