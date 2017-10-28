package com.example.android.popcorn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.popcorn.R;
import com.example.android.popcorn.fragment.parsing.ReviewFragment;

/**
 * Created by alfredchang on 2017-10-28.
 */

public class ReviewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new ReviewFragment()).commit();
        }
    }
}
