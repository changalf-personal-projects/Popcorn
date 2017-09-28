package com.example.android.popcorn.dagger.component;

import com.example.android.popcorn.dagger.module.FragmentModule;
import com.example.android.popcorn.model.Movie;

import java.util.List;

import dagger.Component;

/**
 * Created by alfredchang on 2017-09-27.
 */

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {

    void injectAdapter(List<Movie> movies);

}
