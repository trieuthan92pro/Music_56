package com.example.soundcloud.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.soundcloud.data.source.remote.SongRemoteDataSource;

import java.util.List;

public class DiscoverFragment extends Fragment implements DiscoverContract.View {
    private DiscoverContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private DiscoverVerticalAdapter mVerticalAdapter;
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
        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new DiscoverPresenter(this, new SongRemoteDataSource(),
                getContext().getResources().getStringArray(R.array.array_genre_titles));
        mPresenter.start();
    }

    @Override
    public void showSongList(List<Genre> allSongs) {
        if (!allSongs.isEmpty()) {
            mVerticalAdapter = new DiscoverVerticalAdapter(getContext(), allSongs);
            mRecyclerView.setAdapter(mVerticalAdapter);
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
}
