package com.example.davidson.deenews;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.davidson.deenews.fragments.Bbc;
import com.example.davidson.deenews.fragments.Cnn;
import com.example.davidson.deenews.fragments.Talksports;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabs = findViewById(R.id.tabs);

       tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               tab.setIcon(R.drawable.icons);
               tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#212121"));
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {
               tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
             tab.setIcon(null);
           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {
               tab.setIcon(R.drawable.icons);
               tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#212121"));
           }
       });

        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#212121"));
        tabs.setSelectedTabIndicatorHeight((int) (1* getResources().getDisplayMetrics().density));
        tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#212121"));



    }



    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new Talksports(), "Talk Sports");
        adapter.addFragment(new Bbc(), "BBC");
        adapter.addFragment(new Cnn(), "CNN");
        viewPager.setAdapter(adapter);
    }



    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
