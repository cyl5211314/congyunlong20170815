package com.example.lenovo.congyunlong20170815.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.example.lenovo.congyunlong20170815.R;
import com.example.lenovo.congyunlong20170815.view.fragment.HomeFragment;
import com.example.lenovo.congyunlong20170815.view.fragment.MainFragment;
import com.example.lenovo.congyunlong20170815.view.fragment.TinyFragment;
import com.example.lenovo.congyunlong20170815.view.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import io.github.leibnik.wechatradiobar.WeChatRadioGroup;

public class TowActivity extends BaseActivity {
    private ViewPager towViewpager;
    private WeChatRadioGroup towRadiogroup;
    private List<Fragment> list;
    @Override
    int initContenView() {
        return R.layout.activity_tow;
    }
    @Override
    void initViewId() {
        list = new ArrayList<>();
        towViewpager = (ViewPager) findViewById(R.id.tow_viewpager);
        towRadiogroup = (WeChatRadioGroup) findViewById(R.id.tow_radiogroup);
    }
    @Override
    void initData() {
        initDataList();
        towViewpager.setAdapter(new Vpadapter(getSupportFragmentManager()));
        towRadiogroup.setViewPager(towViewpager);
        towRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.tow_home_rad:
                        towViewpager.setCurrentItem(0);
                        break;
                    case R.id.tow_video_rad:
                        towViewpager.setCurrentItem(1);
                        break;
                    case R.id.tow_tiny_rad:
                        towViewpager.setCurrentItem(2);
                        break;
                    case R.id.tow_main_rad:
                        towViewpager.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    private void initDataList() {
        list.add(new HomeFragment());
        list.add(new VideoFragment());
        list.add(new TinyFragment());
        list.add(new MainFragment());
    }

    @Override
    void initNetwork() {

    }
    class Vpadapter extends FragmentPagerAdapter {


        public Vpadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int poition) {
            return list.get(poition);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}