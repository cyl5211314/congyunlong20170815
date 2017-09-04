package com.example.lenovo.congyunlong20170815.modle.net;

import java.util.Map;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/17 11:44
 */

public interface NetApi {
    void doGet(String url,MainCallback callback);
    void doPost(String url, Map<String,String> map,MainCallback myCllBack);
}
