package com.example.soundcloud.selected_genre_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;

public class SelectedGenreActivity extends AppCompatActivity {
    private static final String EXTRA_GENERE = "EXTRA_GENERE";
    private TextView mTextViewGenreTitle;
    private ImageButton mImageButtonSearch;
    private RecyclerView mRecyclerView;

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
    }

    private void initView() {
        mTextViewGenreTitle = findViewById(R.id.text_selected_genre_title);
        mImageButtonSearch = findViewById(R.id.button_search_selected_genre);
        mRecyclerView = findViewById(R.id.recycler_view_selected_genre);
    }
}
