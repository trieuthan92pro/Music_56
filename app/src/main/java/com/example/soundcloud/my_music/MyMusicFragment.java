package com.example.soundcloud.my_music;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.source.SongRepository;
import com.example.soundcloud.play_detail.PlayDetailActivity;

public class MyMusicFragment extends Fragment implements MyMusicContract.View,
        LocalSongAdapter.OnLocalSongItemListener, View.OnClickListener {
    private static final int REQUEST_READ_EXTERNAL = 900;
    private SongRepository mRepository;
    private MyMusicContract.Presenter mPresenter;
    private TextView mTextErrMessage;
    private RecyclerView mRecyclerLocalSongs;
    private ProgressBar mProgressBar;
    private LocalSongAdapter mLocalSongAdapter;
    private TextView mTextDownloaded;
    private ImageView mImageFolder;

    public MyMusicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        addAction();
        if(isPermissionGranted()) {
            start();
        } else {
            showFolderImage(true);
            showErrorView(false);
            showRecyclerView(false);
        }
        showLoading(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showLoading(true);
                    start();
                } else {
                    showErrorView(true);
                    showError(getContext().getString(R.string.text_permission));
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void putDataOnView(Genre genre) {
        showErrorView(false);
        mLocalSongAdapter.setGenre(genre);
    }

    @Override
    public void showError(String message) {
        showErrorView(true);
        mTextErrMessage.setText(message);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(MyMusicContract.Presenter presenter) {

    }

    @Override
    public void onSongItemClick(int position) {
        getContext().startActivity(PlayDetailActivity.getIntent(getContext(), mPresenter.getGenre(), position));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_folder:
            case R.id.text_downloaded_song:
                checkPermission();
                break;
        }
    }

    private void addAction() {
        mTextDownloaded.setOnClickListener(this);
        mImageFolder.setOnClickListener(this);
    }

    @Override
    public void showRecyclerView(boolean isShowing) {
        if (isShowing) {
            mRecyclerLocalSongs.setVisibility(View.VISIBLE);
        } else {
            mRecyclerLocalSongs.setVisibility(View.GONE);
        }
    }

    @Override
    public void showFolderImage(boolean isShowing) {
        if (isShowing) {
            mImageFolder.setVisibility(View.VISIBLE);
            mTextDownloaded.setVisibility(View.VISIBLE);
        } else {
            mImageFolder.setVisibility(View.GONE);
            mTextDownloaded.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {
        mTextDownloaded = view.findViewById(R.id.text_downloaded_song);
        mTextErrMessage = view.findViewById(R.id.text_error);
        mImageFolder = view.findViewById(R.id.image_folder);
        mProgressBar = view.findViewById(R.id.progress_bar_local);
        mRecyclerLocalSongs = view.findViewById(R.id.recycler_local_song);
        mLocalSongAdapter = new LocalSongAdapter(getContext(), this);
        mRecyclerLocalSongs.setAdapter(mLocalSongAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerLocalSongs.setLayoutManager(layoutManager);
        ContentResolver contentResolver = getContext().getContentResolver();
        mRepository = SongRepository.getInstance();
        mPresenter = new MyMusicPresenter(this, mRepository);
    }

    private void showErrorView(boolean isShowing) {
        if (isShowing) {
            mTextErrMessage.setVisibility(View.VISIBLE);
        } else {
            mTextErrMessage.setVisibility(View.GONE);
        }
    }

    private void checkPermission() {
        if (!isPermissionGranted()) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL);
        } else {
            start();
        }
    }

    private boolean isPermissionGranted() {
        boolean isGranted = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        return isGranted;
    }

    private void start() {
        showLoading(true);
        showFolderImage(false);
        mPresenter.start();
    }
}
