package com.example.lenovo.congyunlong20170815.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/15 20:56
 */

public abstract class BaseFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = onCrearteView();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewId();
        initData();
    }

    abstract View onCrearteView();
    //初始化控件
    abstract void initViewId();
    // 初始化数据
    abstract void initData();
}
