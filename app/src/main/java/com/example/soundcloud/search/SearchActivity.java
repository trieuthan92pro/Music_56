package com.example.soundcloud.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.History;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.source.SearchHistoryDataSource;
import com.example.soundcloud.data.source.SearchHistoryRepository;
import com.example.soundcloud.data.source.SongRepository;
import com.example.soundcloud.data.source.local.HistorySearchDatabaseHelper;
import com.example.soundcloud.data.source.remote.SongRemoteDataSource;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View,
        SearchView.OnQueryTextListener, SearchResultAdapter.OnItemClickListener,
        SearchHistoryAdapter.OnSearchHistoryItemClickListener {
    private static final String MSG_RESULT = " Result(s) for \"";
    private static String sSearchKey;
    private RecyclerView mRecyclerSearchResult;
    private RecyclerView mRecyclerSearchHistory;
    private TextView mTextNumberSearchResult;
    private Group mGroupHistorySearch;
    private Group mGroupSearchResult;
    private SearchHistoryDataSource.LocalDataSource mDatabaseHelper;
    private SearchHistoryRepository mSearchHistoryRepository;
    private SearchContract.Presenter mPresenter;
    private SearchView mSearchView;
    private ProgressBar mProgressSearch;
    private SearchResultAdapter mSearchResultAdapter;
    private SearchHistoryAdapter mSearchHistoryAdapter;

    public static Intent getIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        loadData();
        addAction();
    }

    @Override
    protected void onDestroy() {
        mPresenter.saveRecentSearch();
        super.onDestroy();
    }

    @Override
    public void showProgressBar(boolean isLoading) {
        if (isLoading) {
            mProgressSearch.setVisibility(View.VISIBLE);
        } else {
            mProgressSearch.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSearchHistory(List<History> histories) {
        mSearchHistoryAdapter.setSearchHistories(histories);
        Log.e("SIZE", histories.size() + "");
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSearchResult(List<Song> songs) {
        mTextNumberSearchResult.setText(songs.size() + MSG_RESULT + sSearchKey + "\"");
        mSearchResultAdapter.setSongs(songs);
        mSearchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoreHistory() {
        mSearchHistoryAdapter.setSearchHistories(mPresenter.getSearchHistories());
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        sSearchKey = query;
        mPresenter.loadSearchResult(query);
        mPresenter.addSearchKey(new History(query));
        showProgressBar(true);
        showSearchResultGroup(true);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onSearchHistoryItemClick(int position) {

    }

    private void addAction() {
        mSearchView.setOnQueryTextListener(this);
    }

    private void loadData() {
        mDatabaseHelper = HistorySearchDatabaseHelper.getInstance(this);
        mSearchHistoryRepository = new SearchHistoryRepository(mDatabaseHelper);
        SongRemoteDataSource mRemoteDataSource = new SongRemoteDataSource();
        mPresenter = new SearchPresenter(mSearchHistoryRepository, this,
                new SongRepository(mRemoteDataSource));
        showSearchResultGroup(false);
        mPresenter.start();
    }

    private void initView() {
        mProgressSearch = findViewById(R.id.search_progress_bar);
        mSearchView = findViewById(R.id.search_view);
        mGroupSearchResult = findViewById(R.id.group_search_result);
        mGroupHistorySearch = findViewById(R.id.group_search_history);
        mRecyclerSearchResult = findViewById(R.id.recycler_search_result);
        mRecyclerSearchHistory = findViewById(R.id.recycler_view_search_history);
        RecyclerView.LayoutManager searchResultLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager historyLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerSearchResult.setLayoutManager(searchResultLayoutManager);
        mRecyclerSearchHistory.setLayoutManager(historyLayoutManager);
        mSearchHistoryAdapter = new SearchHistoryAdapter(this, this);
        mSearchResultAdapter = new SearchResultAdapter(this, this);
        mRecyclerSearchHistory.setAdapter(mSearchHistoryAdapter);
        mRecyclerSearchResult.setAdapter(mSearchResultAdapter);
        mTextNumberSearchResult = findViewById(R.id.text_number_search_result);
        showProgressBar(false);
    }

    private void showSearchResultGroup(boolean isShowing) {
        if (isShowing) {
            mGroupHistorySearch.setVisibility(View.GONE);
            mGroupSearchResult.setVisibility(View.VISIBLE);
        } else {
            mGroupHistorySearch.setVisibility(View.VISIBLE);
            mGroupSearchResult.setVisibility(View.GONE);
        }
    }
}
