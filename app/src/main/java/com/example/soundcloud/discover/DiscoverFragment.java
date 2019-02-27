package com.example.soundcloud.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.source.SongDataSource;
import com.example.soundcloud.data.source.SongRepository;
import com.example.soundcloud.data.source.local.SongLocalDataSource;
import com.example.soundcloud.data.source.remote.SongRemoteDataSource;
import com.example.soundcloud.play_detail.PlayDetailActivity;
import com.example.soundcloud.selected_genre_detail.SelectedGenreActivity;

import java.util.List;

public class DiscoverFragment extends Fragment
        implements DiscoverContract.View, DiscoverVerticalAdapter.OnVerticalItemClickListener,
        DiscoverHorizontalAdapter.OnHorizontalItemClickListener {
    private DiscoverContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private DiscoverVerticalAdapter mDiscoverAdapter;
    private ProgressBar mProgressBar;

    public DiscoverFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        mProgressBar = view.findViewById(R.id.discover_progress_bar);
        mRecyclerView = view.findViewById(R.id.discover_vertical_recyclerview);
        RecyclerView.LayoutManager linerLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linerLayoutManager);
        mDiscoverAdapter = new DiscoverVerticalAdapter(getContext(), this, this);
        mRecyclerView.setAdapter(mDiscoverAdapter);
        mRecyclerView.setHasFixedSize(true);
        mPresenter = new DiscoverPresenter(this, SongRepository.getInstance(),
                getContext().getResources().getStringArray(R.array.array_genre_titles));
        mPresenter.start();
        return view;
    }

    @Override
    public void showSongList(List<Genre> genres) {
        if (!genres.isEmpty()) {
            mDiscoverAdapter.setGenres(genres);
            mDiscoverAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErr(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressbar(boolean isShowLoading) {
        if (isShowLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHorizontalItemClick(int position, Genre genre) {
        startActivity(PlayDetailActivity.getIntent(getContext(), genre, position));
    }

    @Override
    public void onClick(Genre genre) {
        startActivity(SelectedGenreActivity.getIntent(getContext(), genre));
    }
}
