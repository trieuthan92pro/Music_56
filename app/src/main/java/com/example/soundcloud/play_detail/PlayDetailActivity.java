package com.example.soundcloud.play_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.soundcloud.R;

public class PlayDetailActivity extends AppCompatActivity {
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
    private ImageButton mImageButtonBack;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_detail);

        initView();
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
        mImageButtonBack = findViewById(R.id.image_button_back);
        mSeekBar = findViewById(R.id.seek_bar);
    }
}
