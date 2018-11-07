package com.example.hp1.wheatherapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.*;

import java.util.Map;


public class FireActivity extends AppCompatActivity {

    //this is the second step of firebase
    private Button btSave;
    private EditText etEmail, etValue;
    private TextView tvName, tvProfession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        tvName = findViewById(R.id.tvName);
        tvProfession = findViewById(R.id.tvProfession);

        //this is the connection between the project and the database
        // Write a message to the database including refrence to location
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");

        //listener is attached
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //map key of type string to value of type string
                Map<String,String> map = (Map<String, String>) dataSnapshot.getValue();
                Log.v("E_VALUE","Data: "+ dataSnapshot.getValue());

                String name = map.get("Name");
                String profession = map.get("Profession");
                tvName.setText(name);
                tvProfession.setText(profession);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        etEmail = findViewById(R.id.etEmail);
        etValue = findViewById(R.id.etValue);

        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String value = etValue.getText().toString();

                myRef.child("Name").setValue(email);
                myRef.child("Profession").setValue(value);

                //overrides the previous value
                //   myRef.child("Name").setValue(email);

                //the push method will add a new child in case it didn't exist
                //myRef.child("Name").push().setValue(value);

            }
        });

    }

}
//read one value from DB
//String value  = dataSnapshot.getValue(String.class);
//tvDBValue.setText(value);