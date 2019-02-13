package com.example.soundcloud.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.TabInfo;

public class HomeActivity extends AppCompatActivity implements HomeScreenContract.View {
    private SearchView mSearchView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private HomeScreenContract.Presenter mPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolBar();
        addTabLayout();
        mPresenter = new HomeScreenPresenter(this);
        mPresenter.start();
    }

    private void setToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void addTabLayout() {
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
        mSearchView = findViewById(R.id.searchView);
        HomeFragmentPagerAdapter adapter =
                new HomeFragmentPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setScrollPosition(1, 0f, true);
    }

    public void setTabIcon(TabInfo[] tabInfos){
        for (int i = 0; i < tabInfos.length; i++) {
            mTabLayout.getTabAt(i).setIcon(tabInfos[i].getImageResource());
        }
    }

}
