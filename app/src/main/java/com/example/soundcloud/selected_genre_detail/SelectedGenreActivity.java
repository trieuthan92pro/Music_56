package com.example.soundcloud.selected_genre_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.play_detail.PlayDetailActivity;
import com.example.soundcloud.search.SearchActivity;

public class SelectedGenreActivity extends AppCompatActivity
        implements SelectedGenreContract.View, SelectedGenreAdapter.OnItemClickListener, View.OnClickListener {
    private static final String EXTRA_GENERE = "EXTRA_GENERE";
    private TextView mTextGenreTitle;
    private ImageButton mButtonSearch;
    private RecyclerView mRecyclerGenres;
    private Genre mGenre;
    private SelectedGenreAdapter mGenreAdapter;
    private SelectedGenreContract.Presenter mPresenter;

    public static Intent getIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, SelectedGenreActivity.class);
        intent.putExtra(SelectedGenreActivity.EXTRA_GENERE, genre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_genre);
        initView();
        getData();
        addAction();
        mPresenter = new SelectedGenrePresenter(this);
        mPresenter.start();
    }

    @Override
    public void showData() {
        mTextGenreTitle.setText(mGenre.getTitle());
        mGenreAdapter.setGenre(mGenre);
        mGenreAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        startActivity(PlayDetailActivity.getIntent(this, mGenre, position));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_selected_genre_back:
                finish();
                break;

            case R.id.button_search_selected_genre:
                startActivity(SearchActivity.getIntent(this));
                break;

            default:
                break;
        }
    }

    private void addAction() {
        findViewById(R.id.image_selected_genre_back).setOnClickListener(this);
        findViewById(R.id.button_search_selected_genre).setOnClickListener(this);
    }

    private void getData() {
        mGenre = getIntent().getParcelableExtra(EXTRA_GENERE);
    }

    private void initView() {
        mTextGenreTitle = findViewById(R.id.text_selected_genre_title);
        mButtonSearch = findViewById(R.id.button_search_selected_genre);
        mRecyclerGenres = findViewById(R.id.recycler_selected_genre);
        mGenreAdapter = new SelectedGenreAdapter(this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerGenres.setLayoutManager(layoutManager);
        mRecyclerGenres.setAdapter(mGenreAdapter);
    }
}
