package com.example.android.popcorn;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popcorn.fragment.CurrentFragment;
import com.example.android.popcorn.fragment.FavouriteFragment;
import com.example.android.popcorn.fragment.PopularFragment;
import com.example.android.popcorn.fragment.TopFragment;
import com.example.android.popcorn.ui.MovieCollectionPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use Butterknife to bind views.
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        MovieCollectionPagerAdapter mPagerAdapter = new MovieCollectionPagerAdapter(getSupportFragmentManager());
        addFragments(mPagerAdapter);
        addFragmentTitles(mPagerAdapter);
        viewPager.setAdapter(mPagerAdapter);
    }

    private void addFragments(MovieCollectionPagerAdapter pagerAdapter) {
        pagerAdapter.addFragment(new PopularFragment());
        pagerAdapter.addFragment(new TopFragment());
        pagerAdapter.addFragment(new CurrentFragment());
        pagerAdapter.addFragment(new FavouriteFragment());
    }

    private void addFragmentTitles(MovieCollectionPagerAdapter pagerAdapter) {
        pagerAdapter.addFragmentTitle(MovieKeywords.POPULAR_MOVIES);
        pagerAdapter.addFragmentTitle(MovieKeywords.TOP_MOVIES);
        pagerAdapter.addFragmentTitle(MovieKeywords.CURRENT_MOVIES);
        pagerAdapter.addFragmentTitle(MovieKeywords.FAVOURITE_MOVIES);
    }
}
