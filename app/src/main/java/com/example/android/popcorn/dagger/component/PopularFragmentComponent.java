package com.example.android.popcorn.dagger.component;

import com.example.android.popcorn.dagger.module.PopularFragmentModule;
import com.example.android.popcorn.model.Movie;
import com.example.android.popcorn.ui.PosterRecyclerViewAdapter;

import java.util.List;

import dagger.Component;

/**
 * Created by alfredchang on 2017-09-27.
 */

@Component(modules = {PopularFragmentModule.class})
public interface PopularFragmentComponent {

    PosterRecyclerViewAdapter provideRecyclerAdapter(List<Movie> movies);

}
