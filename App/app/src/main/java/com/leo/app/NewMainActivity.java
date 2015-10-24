package com.leo.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.leo.app.fragment.NewsFragment;
import com.leo.app.fragment.PicFragment;
import com.leo.app.fragment.VadioFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LT on 2015/10/14 0014.
 */
public class NewMainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        initTab();
        initToolBar();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void initTab() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("新闻"));
        tabLayout.addTab(tabLayout.newTab().setText("图片"));
        tabLayout.addTab(tabLayout.newTab().setText("视频"));
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        int i = tabLayout.getTabCount();
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyPageAdapter extends FragmentStatePagerAdapter {
        private String[] titles = new String[]{
                "新闻","图片","视频"
        };
        private Map<Integer, Fragment> map = new HashMap<>();
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = map.get(position);
            if (fragment == null) {
                switch (position) {
                    case 0:
                        fragment = new NewsFragment();
                        break;
                    case 1:
                        fragment = new PicFragment();
                        break;
                    case 2:
                        fragment = new VadioFragment();
                        break;
                    default:
                        fragment = new NewsFragment();
                        break;
                }
                map.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
