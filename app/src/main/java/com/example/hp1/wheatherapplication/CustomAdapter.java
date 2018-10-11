package com.example.hp1.wheatherapplication;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sarah on 9/20/2017.
 * this page contains the custom galley that extends
 */

public class CustomAdapter extends ArrayAdapter<Item> {

    Context context;
    int resouce;
    ArrayList<Item> items;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Item> items) {
        super(context,resource);
        this.context=context;
        this.resouce=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {

            LayoutInflater ImageInflater = LayoutInflater.from(context);
            View customView = ImageInflater.inflate(resouce, null);

        }
        Item item = getItem(position);

        TextView textView=(TextView)v.findViewById(R.id.tvName);
        textView.setText(item.getName());

    /*    ImageView imageView=(ImageView)customView.findViewById(R.id.imageViewList);
        imageView.setImageResource(item.getImage());

        Button btEdit=customView.findViewById(R.id.btEdit);
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });
        Button btDelete=customView.findViewById(R.id.btDelte);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(item);
            }
        });
*/
        return v;
    }
}