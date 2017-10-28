package com.example.android.popcorn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.popcorn.R;
import com.example.android.popcorn.fragment.IndividualReviewFragment;

/**
 * Created by alfredchang on 2017-10-18.
 */

public class IndividualReviewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new IndividualReviewFragment()).commit();
        }
    }
}
