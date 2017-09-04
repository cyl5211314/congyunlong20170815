package com.example.lenovo.congyunlong20170815.modle.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/4 16:47
 */

public class ToastUtils {
    public static void getToas(Context context, String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
