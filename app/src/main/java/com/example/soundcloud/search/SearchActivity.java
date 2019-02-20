package com.example.soundcloud.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.soundcloud.R;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerSearchResult;
    private RecyclerView mRecyclerSearchHistory;
    private TextView mTextNumberSearchResult;
    private Group mGroupHistorySearch;
    private Group mGroupSearchResult;

    public static Intent getIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mRecyclerSearchResult = findViewById(R.id.recycler_search_result);
        mRecyclerSearchHistory = findViewById(R.id.recycler_view_search_history);
        mTextNumberSearchResult = findViewById(R.id.text_number_search_result);
    }
}
