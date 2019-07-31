package com.example.ciss_simkey;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainProcessActivity extends CustomTitleBarActivity {
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private List<Integer> mTitleIdList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    centerTitle(R.string.title_home);
                    return true;
                case R.id.navigation_scan:
                    mViewPager.setCurrentItem(1);
                    centerTitle(R.string.title_scan);

                    return true;
                case R.id.navigation_feedback:
                    mViewPager.setCurrentItem(2);
                    centerTitle(R.string.title_feedback);

                    return true;
                case R.id.navigation_about:
                    mViewPager.setCurrentItem(3);
                    centerTitle(R.string.title_about);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_process);
        centerTitle(R.string.title_home);

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MainPageFragment());
        mFragmentList.add(new TestPageFragment());
        mFragmentList.add(new TestPageFragment());
        mFragmentList.add(new TestPageFragment());

        mTitleIdList = new ArrayList<>();
        mTitleIdList.add(R.string.title_home);
        mTitleIdList.add(R.string.title_scan);
        mTitleIdList.add(R.string.title_feedback);
        mTitleIdList.add(R.string.title_about);

        mViewPager = findViewById(R.id.process_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                navView.getMenu().getItem(position).setChecked(true);
                centerTitle(mTitleIdList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
