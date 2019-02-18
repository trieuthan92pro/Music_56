package com.example.soundcloud.discover;

import com.example.soundcloud.BasePresenter;
import com.example.soundcloud.data.source.SongRepository;

public class DiscoverPresenter implements BasePresenter {
    private SongRepository mRepository;
    public DiscoverPresenter(SongRepository repository) {
        mRepository = repository;
    }

    @Override
    public void start() {

    }

}
