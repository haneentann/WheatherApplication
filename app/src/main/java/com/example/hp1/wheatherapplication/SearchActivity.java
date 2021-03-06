package com.example.hp1.wheatherapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView searchList;
    private ArrayAdapter<String> arrayAdapter;
    private String to="", from="";
    private EditText etFrom, etTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchList = findViewById(R.id.lvList);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        searchList.setAdapter(arrayAdapter);


        etFrom = findViewById(R.id.etFrom);
        etFrom.setOnClickListener(this);
        from = getIntent().getStringExtra("from");
        if(from != null)
            etFrom.setText(from);


        etTo = findViewById(R.id.etTo);
        etTo.setOnClickListener(this);
        to = getIntent().getStringExtra("to");

        if(to != null)
            etTo.setText(to);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem menuItem =  menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, LocationsActivity.class);
        i.putExtra("from", etFrom.getText().toString());
        i.putExtra("to",etTo.getText().toString());

        if(etTo == view)
            i.putExtra("source", "to");
        else
            i.putExtra("source", "from");
        startActivity(i);
    }
}
