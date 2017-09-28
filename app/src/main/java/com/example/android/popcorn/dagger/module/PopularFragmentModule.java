package com.example.android.popcorn.dagger.module;

import com.example.android.popcorn.fragment.PopularFragment;
import com.example.android.popcorn.ui.PosterRecyclerViewAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alfredchang on 2017-09-27.
 */

@Module
public class PopularFragmentModule {

    @Provides
    public PosterRecyclerViewAdapter provideRecyclerAdapter() {
        return new PosterRecyclerViewAdapter(null);
    }

    @Provides
    public PopularFragment providePopularFragment() {
        return new PopularFragment();
    }
}
