package com.example.lenovo.congyunlong20170815.view.fragment;

import android.view.View;

import com.example.lenovo.congyunlong20170815.R;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/15 20:52
 */

public class VideoFragment extends BaseFragment{
    private View homeview;

    @Override
    View onCrearteView() {
        homeview = View.inflate(getActivity(), R.layout.videofragment, null);
        return homeview;
    }
    @Override
    void initViewId() {

    }

    @Override
    void initData() {

    }

}
