package com.example.hp1.wheatherapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> locations;
    private ArrayAdapter arrayAdapter;
    private ListView lvLocations;
    private String from="", to="", source="to";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        locations = new ArrayList<>();
        locations.add("Haifa");
        locations.add("Tel Aviv");

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,locations);
        lvLocations = findViewById(R.id.lvLocations);
        lvLocations.setAdapter(arrayAdapter);
        lvLocations.setOnItemClickListener(this);

        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        source = getIntent().getStringExtra("source");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(source.equals("to"))
            to=locations.get(i);
        else
            from=locations.get(i);

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("to", to);
        startActivity(intent);
//        getIntent().putExtra("to", to);

    }
}
