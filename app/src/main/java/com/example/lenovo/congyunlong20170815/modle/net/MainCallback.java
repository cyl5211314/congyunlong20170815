package com.example.lenovo.congyunlong20170815.modle.net;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/17 11:00
 */

public interface MainCallback {
    void onResponse(String str);
    void onFailure(int code);
}
