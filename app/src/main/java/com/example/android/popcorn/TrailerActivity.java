package com.example.android.popcorn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alfredchang on 2017-10-15.
 */

public class TrailerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new TrailerFragment()).commit();
        }
    }
}
