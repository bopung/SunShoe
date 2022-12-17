package com.example.sunshoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.webkit.WebView;

public class aboutus extends FragmentActivity {

    private static final int NUMBER_PAGES = 2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        viewPager2 = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePageAdapter extends FragmentStateAdapter {
        public ScreenSlidePageAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new AboutUsWeb();
            } else if (position == 1) {
                return new AboutUsDetail();

            }
            return null;
        }

        @Override
        public int getItemCount(){
            return NUMBER_PAGES;
        }
    }
}