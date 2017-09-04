package com.example.lenovo.congyunlong20170815.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/15 11:04
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//此FLAG可使状态栏透明，且当前视图在绘制时，从屏幕顶端开始即top = 0开始绘制，这也是实现沉浸效果的基础
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//可不加
        }
        setContentView(initContenView());
        initViewId();
        initData();
        initNetwork();
    }
    //初始化视图
    abstract int initContenView();
    //初始化控件
    abstract void initViewId();
    // 初始化数据
    abstract void initData();
    //初始化网络请求
    abstract void initNetwork();
}
