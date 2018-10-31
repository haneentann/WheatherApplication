package com.example.hp1.wheatherapplication;

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
    private String from="", to="";

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
    }
    @Override
    public void onBackPressed() {
        getIntent().putExtra("from", from);
        getIntent().putExtra("to", to);
        this.setResult(RESULT_OK, getIntent());
        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        from=locations.get(i);
        onBackPressed();
    }
}
