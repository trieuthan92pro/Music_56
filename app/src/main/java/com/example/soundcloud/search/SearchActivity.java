package com.example.soundcloud.search;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.example.soundcloud.play_detail.PlayDetailActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View,
        SearchView.OnQueryTextListener, SearchResultAdapter.OnItemClickListener,
        SearchHistoryAdapter.OnSearchHistoryItemClickListener, View.OnClickListener {
    private static final String MSG_RESULT = " Result(s) for \"";
    private static final String LIMIT = "50";
    private static final int SPAN_EXPAND = 4;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 999;
    private static final String MSG = "YOU MUST ACCEPT THIS PERMISSION TO SAVE DOWNLOAD SONGS";
    private RecyclerView mRecyclerSearchResult;
    private RecyclerView mRecyclerHistory;
    private TextView mTextNumberSearchResult;
    private Group mGroupHistorySearch;
    private Group mGroupSearchResult;
    private SearchHistoryDataSource.LocalDataSource mDatabaseHelper;
    private SearchHistoryRepository mSearchHistoryRepository;
    private SearchContract.Presenter mPresenter;
    private SearchView mSearchView;
    private ProgressBar mProgressSearch;
    private SearchResultAdapter mSearchResultAdapter;
    private SearchHistoryAdapter mHistoryAdapter;
    private boolean isUpdateHistory;

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
        if (isUpdateHistory) {
            updateHistory(histories);
        } else {
            mHistoryAdapter.setData(histories);
            mHistoryAdapter.notifyItemRangeChanged(0, histories.size());
        }
    }

    @Override
    public void showSearchResult(List<Song> songs) {
        mTextNumberSearchResult.setText(songs.size() + MSG_RESULT + mPresenter.getSearchKey() + "\"");
        mSearchResultAdapter.setData(songs);
        mSearchResultAdapter.notifyItemRangeChanged(0, songs.size());
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
        mPresenter.loadHistorySearch(LIMIT);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.onQueryTextSubmit(query);
        showSearchResultGroup(true);
        hideViewMore();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.setAddSearchKey(true);
        return false;
    }

    @Override
    public void onItemClick(int position) {
        startActivity(PlayDetailActivity.getIntent(this, mPresenter.getGenre(), position));
    }

    @Override
    public void onSearchHistoryItemClick(int position) {
        String searchKey = mPresenter.getSearchHistories().get(position).getSearchKey();
        mPresenter.setAddSearchKey(false);
        mSearchView.setQuery(searchKey, true);
    }

    @Override
    public void download(int position) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            //TODO: implement download feature here
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //TODO: call method to download selected song
        } else {
            Toast.makeText(this, MSG, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_search_button_back:
                finish();
                break;

            case R.id.text_action_clear:
                mPresenter.clearSearchHistory();
                break;

            case R.id.image_more_hostory:
            case R.id.text_view_more_history:
                isUpdateHistory = true;
                mPresenter.loadHistorySearch(LIMIT);
                break;

            default:
                break;
        }
    }

    private void updateHistory(List<History> histories) {
        mHistoryAdapter.setData(histories);
        mHistoryAdapter.notifyItemRangeChanged(0, histories.size());
        hideViewMore();
    }

    private void hideViewMore() {
        findViewById(R.id.text_view_more_history).setVisibility(View.GONE);
        findViewById(R.id.image_more_hostory).setVisibility(View.GONE);
    }

    private void addAction() {
        mSearchView.setOnQueryTextListener(this);
        findViewById(R.id.image_search_button_back).setOnClickListener(this);
        findViewById(R.id.text_action_clear).setOnClickListener(this);
        findViewById(R.id.text_view_more_history).setOnClickListener(this);
        findViewById(R.id.image_more_hostory).setOnClickListener(this);
    }

    private void loadData() {
        mDatabaseHelper = HistorySearchDatabaseHelper.getInstance(this);
        mSearchHistoryRepository = new SearchHistoryRepository(mDatabaseHelper);
        SongRemoteDataSource mRemoteDataSource = new SongRemoteDataSource();
        mPresenter = new SearchPresenter(mSearchHistoryRepository, this,
                new SongRepository(mRemoteDataSource));
        showSearchResultGroup(false);
        isUpdateHistory = false;
        mPresenter.start();
    }

    private void initView() {
        mProgressSearch = findViewById(R.id.search_progress_bar);
        mSearchView = findViewById(R.id.search_view);
        mGroupSearchResult = findViewById(R.id.group_search_result);
        mGroupHistorySearch = findViewById(R.id.group_search_history);
        mRecyclerSearchResult = findViewById(R.id.recycler_search_result);
        mRecyclerHistory = findViewById(R.id.recycler_view_search_history);
        RecyclerView.LayoutManager searchResultLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager historyLayoutManager =
                new StaggeredGridLayoutManager(SPAN_EXPAND, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerSearchResult.setLayoutManager(searchResultLayoutManager);
        mRecyclerHistory.setLayoutManager(historyLayoutManager);
        mHistoryAdapter = new SearchHistoryAdapter(this, this);
        mSearchResultAdapter = new SearchResultAdapter(this, this);
        mRecyclerHistory.setAdapter(mHistoryAdapter);
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
