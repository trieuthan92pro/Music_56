package com.example.soundcloud.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.SearchHistory;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SearchHistoryDataSource;
import com.example.soundcloud.data.source.SearchHistoryRepository;
import com.example.soundcloud.data.source.SongRepository;
import com.example.soundcloud.data.source.local.HistorySearchDatabaseHelper;
import com.example.soundcloud.data.source.local.HistorySearchLocalDataSource;
import com.example.soundcloud.data.source.remote.SongRemoteDataSource;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private RecyclerView mRecyclerSearchResult;
    private RecyclerView mRecyclerSearchHistory;
    private TextView mTextNumberSearchResult;
    private Group mGroupHistorySearch;
    private Group mGroupSearchResult;
    private HistorySearchDatabaseHelper mDatabaseHelper;
    private SearchHistoryRepository mSearchHistoryRepository;
    private SearchContract.Presenter mPresenter;
    private SearchHistoryDataSource.LocalDataSource mSearchLocalDataSource;

    public static Intent getIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        loadData();
    }

    private void loadData() {
        mDatabaseHelper = HistorySearchDatabaseHelper.getInstance(this);
        mSearchLocalDataSource = new HistorySearchLocalDataSource(mDatabaseHelper);
        mSearchHistoryRepository = new SearchHistoryRepository(mSearchLocalDataSource);
        SongRemoteDataSource mRemoteDataSource = new SongRemoteDataSource();
        mPresenter = new SearchPresenter(mSearchHistoryRepository, this,
                new SongRepository(mRemoteDataSource));
        mPresenter.start();
    }

    private void initView() {
        mRecyclerSearchResult = findViewById(R.id.recycler_search_result);
        mRecyclerSearchHistory = findViewById(R.id.recycler_view_search_history);
        mTextNumberSearchResult = findViewById(R.id.text_number_search_result);
    }

    @Override
    public void showProgressBar(boolean isLoading) {

    }

    @Override
    public void showSearchHistory(List<SearchHistory> histories) {

    }

    @Override
    public void showSearchResult(List<Song> songs) {

    }

    @Override
    public void showError(String errMsg) {

    }

    @Override
    public void showSuccess(String msg) {

    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }
}
