package com.example.soundcloud.discover;

import com.example.soundcloud.data.source.SongRepository;

public class DiscoverPresenter {
    private SongRepository mRepository;

    public DiscoverPresenter(SongRepository repository) {
        mRepository = repository;
    }

}
