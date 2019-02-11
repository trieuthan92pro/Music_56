package com.example.soundcloud.splash_screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soundcloud.R;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    private TextView mTextViewAppName;
    private ImageView mImageViewAppIcon;
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        mPresenter = new SplashPresenter(this);
        start();
    }

    @Override
    public void showSoundCloudIcon(int iconId) {
        mImageViewAppIcon.setImageResource(iconId);
    }

    @Override
    public void showAppName(int appName) {
        mTextViewAppName.setText(getResources().getString(appName));
    }

    private void initView() {
        mImageViewAppIcon = findViewById(R.id.img_icon);
        mTextViewAppName = findViewById(R.id.text_app_name);
    }

    private void start() {
        mPresenter.start();
    }
}
