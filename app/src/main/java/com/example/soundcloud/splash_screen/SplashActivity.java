package com.example.soundcloud.splash_screen;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.home_screen.HomeActivity;

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
        goToHomeScreen();
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

    private void goToHomeScreen() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            finish();
        }, 3000);
    }
}
