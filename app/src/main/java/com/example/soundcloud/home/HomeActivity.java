package com.example.soundcloud.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.SearchView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.TabInfo;
import com.example.soundcloud.search.SearchActivity;

public class HomeActivity extends AppCompatActivity
        implements HomeScreenContract.View, TabLayout.BaseOnTabSelectedListener, View.OnClickListener {
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
        addAction();
    }

    @Override
    public void setTabIcon(TabInfo[] tabInfos) {
        int selectedPosition = mTabLayout.getSelectedTabPosition();
        for (int i = 0; i < tabInfos.length; i++) {
            if (i == selectedPosition) {
                mTabLayout.getTabAt(i).setIcon(tabInfos[i].getImageResourceActive());
            } else {
                mTabLayout.getTabAt(i).setIcon(tabInfos[i].getImageResource());
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabIndex = tab.getPosition();
        TabInfo[] tabInfos = mPresenter.getTabsInfo();
        TabLayout.Tab selectedTab = mTabLayout.getTabAt(tabIndex);
        selectedTab.setIcon(tabInfos[tabIndex].getImageResourceActive());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int tabIndex = tab.getPosition();
        TabInfo[] tabInfos = mPresenter.getTabsInfo();
        TabLayout.Tab unSelectedTab = mTabLayout.getTabAt(tabIndex);
        unSelectedTab.setIcon(tabInfos[tabIndex].getImageResource());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        startActivity(SearchActivity.getIntent(this));
    }

    private void setToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void addAction() {
        mTabLayout.addOnTabSelectedListener(this);
        mSearchView.setOnSearchClickListener(this);
    }

    private void addTabLayout() {
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
        mSearchView = findViewById(R.id.searchView);
        mSearchView.setClickable(true);
        mSearchView.setEnabled(false);
        mSearchView.setInputType(InputType.TYPE_NULL);
        mPresenter = new HomeScreenPresenter(this);
        TabInfo[] tabInfos = mPresenter.getTabsInfo();
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragmentPagerAdapter adapter =
                new HomeFragmentPagerAdapter(this, fragmentManager, tabInfos);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setScrollPosition(1, 0f, true);
        mPresenter.start();
    }
}
