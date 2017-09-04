package com.example.lenovo.congyunlong20170815.modle.net;

import java.util.Map;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/17 11:00
 */

public class QuarantineOkhttputils implements NetApi{
    private NetApi netApi;
    public void init(NetApi netApi) {
        this.netApi = netApi;
    }
    private static QuarantineOkhttputils quarantineOkhttputils;
    public static QuarantineOkhttputils getInstance(){
        if(quarantineOkhttputils == null ){
            synchronized (QuarantineOkhttputils.class){
                if (quarantineOkhttputils == null ){
                    quarantineOkhttputils = new QuarantineOkhttputils();
                }
            }
        }
        return quarantineOkhttputils;
    }

    @Override
    public void doGet(String url, MainCallback callback) {
        netApi.doGet(url,callback);
    }

    @Override
    public void doPost(String url, Map<String, String> map, MainCallback callback) {
        netApi.doPost(url,map,callback);
    }
}
