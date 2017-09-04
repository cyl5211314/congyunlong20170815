package com.example.lenovo.congyunlong20170815.presenter.HomeFragmentPresenter;

import android.content.Context;

import com.example.lenovo.congyunlong20170815.modle.net.MainCallback;
import com.example.lenovo.congyunlong20170815.modle.net.QuarantineOkhttputils;
import com.example.lenovo.congyunlong20170815.modle.utils.WiFiUtils;

/**
 * 类描述：首页Fragment的P层
 * 创建人：lenovo
 * 创建时间：2017/8/28 14:24
 */

public class HomeFragmentPresenter {
    private HomeFragemtview fragemtview;
    private int page = 1;
    private Context context;

    public void setFragemtview(HomeFragemtview fragemtview) {
        this.fragemtview = fragemtview;
    }

    public HomeFragmentPresenter(Context context) {
        this.context = context;
    }

    public void OnWifi(HomeView homeView){
        if(WiFiUtils.isNetworkAvailable(context)){
            homeView.OnTrue();
        }else{
            homeView.OnFales();
        }
    }
    //网络请求方法
    public void  OnNetGet (String url){
        QuarantineOkhttputils.getInstance().doGet(url, new MainCallback() {
            @Override
            public void onResponse(String str) {
                fragemtview.OnTrue(str);
            }
            @Override
            public void onFailure(int code) {
                fragemtview.OnFalse(code);
            }
        });
    }
}
