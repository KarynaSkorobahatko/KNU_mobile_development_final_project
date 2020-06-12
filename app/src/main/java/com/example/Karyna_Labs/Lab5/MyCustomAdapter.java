package com.example.Karyna_Labs.Lab5;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.Karyna_Labs.R;

import java.util.List;

public class MyCustomAdapter extends ArrayAdapter {

    private List contactsInfoList;
    private Context context;

    public MyCustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.contactsInfoList = objects;
        this.context = context;
    }

    private class ViewHolder {
        TextView displayName;
        TextView phoneNumber;
        TextView email;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.contact_info, null);

            holder = new ViewHolder();
            holder.displayName = ((TextView) convertView.findViewById(R.id.displayName));
            holder.displayName.setTextColor(Color.WHITE);
            holder.displayName.setTextSize(18);
            holder.phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);
            holder.phoneNumber.setTextColor(Color.WHITE);
            holder.phoneNumber.setTextSize(18);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.email.setTextColor(Color.WHITE);
            holder.email.setTextSize(18);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContactsInfo contactsInfo = (ContactsInfo) contactsInfoList.get(position);
        holder.displayName.setText(contactsInfo.getDisplayName());
        holder.phoneNumber.setText(contactsInfo.getPhoneNumber());
        holder.email.setText(contactsInfo.getEmail());

        return convertView;
    }
}