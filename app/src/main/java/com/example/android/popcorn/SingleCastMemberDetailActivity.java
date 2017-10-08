package com.example.android.popcorn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.popcorn.fragment.parsing.SingleCastDetailFragment;

/**
 * Created by alfredchang on 2017-10-08.
 */

public class SingleCastMemberDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new SingleCastDetailFragment()).commit();
        }
    }
}
