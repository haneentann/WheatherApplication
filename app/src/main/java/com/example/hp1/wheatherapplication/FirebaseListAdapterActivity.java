package com.example.hp1.wheatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseListAdapterActivity extends AppCompatActivity {

    ListView lvFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_list_adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://wheatherapplication-76824.firebaseio.com/Users");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference
        ) {

            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };
        lvFirebase.setAdapter(firebaseListAdapter);
    }
}