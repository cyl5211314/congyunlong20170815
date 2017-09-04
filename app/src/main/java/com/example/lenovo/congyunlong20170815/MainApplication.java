package com.example.lenovo.congyunlong20170815;

import android.app.Application;

import com.example.lenovo.congyunlong20170815.modle.net.NetClient;
import com.example.lenovo.congyunlong20170815.modle.net.QuarantineOkhttputils;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/17 15:28
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
       QuarantineOkhttputils.getInstance().init(NetClient.getInstance());
    }

}
