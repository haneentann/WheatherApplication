package com.example.hp1.wheatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HorizontalScrolViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrol_view);

        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        for(int i = 0; i< 6 ; i++){
            View view = inflater.inflate(R.layout.hor_item, gallery, false);
            TextView text = view.findViewById(R.id.horText);
            text.setText("Item: "+i);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ic_launcher);

            gallery.addView(view);
        }
    }
}
