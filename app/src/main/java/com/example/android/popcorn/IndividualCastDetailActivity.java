package com.example.android.popcorn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.popcorn.fragment.IndividualCastDetailFragment;

/**
 * Created by alfredchang on 2017-10-08.
 */

public class IndividualCastDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_cast_member_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new IndividualCastDetailFragment()).commit();
        }
    }
}
