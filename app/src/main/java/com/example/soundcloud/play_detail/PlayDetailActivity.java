package com.example.soundcloud.play_detail;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.discover.DiscoverFragment;

public class PlayDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static String EXTRA_SELECTED_ITEM_POSITION = "EXTRA_SELECTED_ITEM_POSITION";
    private static String EXTRA_SELECTED_GENRE = "EXTRA_SELECTED_GENRE";
    private ImageView mImageArtwork;
    private ImageView mImageFavorite;
    private ImageView mImageMoreOption;
    private TextView mTextSongInfo;
    private TextView mTextCurrentPosion;
    private TextView mTextTotalDuration;
    private TextView mTextIndicator;
    private ImageButton mImageButtonShuffle;
    private ImageButton mImageButtonSkipToPrevious;
    private ImageButton mImageButtonSkipToNext;
    private ImageButton mImageButtonPlayPause;
    private ImageButton mImageButtonRepeat;
    private SeekBar mSeekBar;

    private Genre mGenre;
    private int mSelectedPosition;

    public static Intent getIntent(Context context, Genre genre, int position) {
        Intent intent = new Intent(context, PlayDetailActivity.class);
        intent.putExtra(EXTRA_SELECTED_ITEM_POSITION, position);
        intent.putExtra(EXTRA_SELECTED_GENRE, genre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_detail);
        initView();
        addAction();
        mGenre = getIntent().getParcelableExtra(EXTRA_SELECTED_GENRE);
        mSelectedPosition = getIntent().getIntExtra(EXTRA_SELECTED_ITEM_POSITION, 0);
        if(savedInstanceState != null){
            mSelectedPosition = savedInstanceState.getInt(EXTRA_SELECTED_ITEM_POSITION);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //TODO: do it next time
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //TODO: do it next time
    }

    @Override
    public void onClick(View v) {
        String message = mSelectedPosition + ", " + mGenre.getTitle();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.img_favorite_icon:
                //TODO: handler event for this action
                break;
        }
    }

    private void addAction() {
        mImageFavorite.setOnClickListener(this);
        mImageMoreOption.setOnClickListener(this);
        mImageButtonPlayPause.setOnClickListener(this);
        mImageButtonRepeat.setOnClickListener(this);
        mImageButtonShuffle.setOnClickListener(this);
        mImageButtonSkipToNext.setOnClickListener(this);
        mImageButtonSkipToPrevious.setOnClickListener(this);
    }

    private void initView() {
        mImageArtwork = findViewById(R.id.img_artwork);
        mImageFavorite = findViewById(R.id.img_favorite_icon);
        mImageMoreOption = findViewById(R.id.img_option_icon);
        mTextCurrentPosion = findViewById(R.id.text_current_position);
        mTextIndicator = findViewById(R.id.text_indicator);
        mTextSongInfo = findViewById(R.id.text_song_info);
        mTextTotalDuration = findViewById(R.id.text_total_duration);
        mImageButtonPlayPause = findViewById(R.id.img_button_play_pause);
        mImageButtonRepeat = findViewById(R.id.img_button_replay);
        mImageButtonShuffle = findViewById(R.id.img_button_shuft);
        mImageButtonSkipToNext = findViewById(R.id.img_button_next);
        mImageButtonSkipToPrevious = findViewById(R.id.img_button_prev);
        findViewById(R.id.image_button_back).setOnClickListener(this);;
        mSeekBar = findViewById(R.id.seek_bar);
    }
}
