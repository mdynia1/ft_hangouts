package com.example.hangouts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.ViewHolder> {
    private Context context;
    private String id;
    List<Contact> contacts = new LinkedList<>();

    public Search_Adapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public Search_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_of_elements, viewGroup, false);
        return new Search_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Search_Adapter.ViewHolder viewHolder, final int position) {
        if (contacts.size() < position) {
            return;
        }
        final Contact contact = contacts.get(position);
        final String name = contact.getFirstName() + " " + contact.getLastName();
        final String number = contact.getPhoneNumberFormated();
        viewHolder.textViewName.setText(name);
        viewHolder.textViewNumber.setText(number);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int requestcode = 1;
                Intent intent = new Intent(context, Contact_field.class);
                intent.putExtra("clicked_contact", contact.getKeyId());
                ((Activity) context).startActivityForResult(intent, requestcode);

            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
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
                        final Contact contact = contacts.get(pos);
                        int requestcode = 1;
                        Intent intent = new Intent(context, Contact_field.class);
                        intent.putExtra("clicked_contact", contact.getKeyId());
                        ((Activity) context).startActivityForResult(intent, requestcode);
                    }
                }
            });
        }
    }

}
