package com.example.lenovo.congyunlong20170815.modle.net;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/17 10:47
 */

public class NetClient implements NetApi{
    private static NetClient netClient;
    private NetClient(){
        client =getClient();
    }
    public static NetClient getInstance(){
        if (netClient == null){
            synchronized (NetClient.class){
                if(netClient == null){
                    netClient = new NetClient();
                }
            }
        }
        return netClient;
    }

    private final OkHttpClient client;

    private OkHttpClient getClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)//获取数据超时
                .connectTimeout(3000,TimeUnit.MILLISECONDS)//网络连接超时
                .build();
   return client;
    }

    @Override
    public void doGet(String url,final MainCallback callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call.hashCode());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               // if (response.code()==1){
                    String string = response.body().string();
                    callback.onResponse(string);
              /*  }else{
                    callback.onFailure(response.code());
                }*/
            }
        });


    }

    @Override
    public void doPost(String url, Map<String, String> map, final MainCallback callback) {
        FormBody.Builder builder = new FormBody.Builder();
           for (Map.Entry<String,String>  entry : map.entrySet()) {
               builder.add(entry.getKey(),entry.getValue());
                   }
          RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call.hashCode());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    String string = response.body().string();
                    callback.onResponse(string);
                }else{
                    callback.onFailure(response.code());
                }
            }
        });

    }

}
