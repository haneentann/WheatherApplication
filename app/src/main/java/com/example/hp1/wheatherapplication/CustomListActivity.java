package com.example.hp1.wheatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ArrayList<String> items2;
    CustomAdapter2 adapter;

    ArrayAdapter arrayAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        lvItems = findViewById(R.id.lvItems);

        items = new ArrayList<>();

        items.add(new Item(R.drawable.download,"first"));
        items.add(new Item(R.drawable.download,"second"));
        items.add(new Item(R.drawable.download,"third"));
        items.add(new Item(R.drawable.download,"fourth"));

        adapter = new CustomAdapter2(this, R.layout.custom_row, items);
        lvItems.setAdapter(adapter);

    }
}
