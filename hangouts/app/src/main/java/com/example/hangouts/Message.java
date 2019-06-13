package com.example.hangouts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Message {

    private String status = "me";
    private String message = "";
    private String time = "today";
    public static List<Message> Massages_list = new ArrayList<Message>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public Message(String status, String message, String time){
        this.message = message;
        this.status = status;
        this.time = time;

    }

    public String getStatus(){
        return this.status;
    }
    public String getMessage(){
        return this.message;
    }
    public String getTime(){
        return this.time;
    }

    public static  List<Message>  SMS_List(Context context, String sms_text){
        Massages_list.clear();
        if (sms_text != null) {
            for (String retval : sms_text.split("/////")) {
                if (!retval.isEmpty()) {
                    String cut[] = retval.split("///");
                    if (cut[0].contains("Received")) {
                        int len = cut[0].length();
                        cut[0] = cut[0].substring(8, len);
                    }
                    Massages_list.add(new Message(cut[0], cut[1], cut[2]));
                }
            }
        }
        return  (Massages_list);

    }
}
