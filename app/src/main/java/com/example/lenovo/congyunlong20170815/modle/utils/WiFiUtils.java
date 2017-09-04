package com.example.lenovo.congyunlong20170815.modle.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/5 15:59
 */

public class WiFiUtils {
       public static boolean isNetworkAvailable(Context context) {
           ConnectivityManager connectivity = (ConnectivityManager) context
                   .getSystemService(Context.CONNECTIVITY_SERVICE);
           if (connectivity != null) {
               NetworkInfo info = connectivity.getActiveNetworkInfo();
               if (info != null && info.isConnected())
               {
                   // 当前网络是连接的
                   if (info.getState() == NetworkInfo.State.CONNECTED)
                   {
                       // 当前所连接的网络可用
                       return true;
                   }
               }
           }
           return false;
       }
}
