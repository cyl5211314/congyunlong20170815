package com.example.lenovo.congyunlong20170815.view.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.congyunlong20170815.R;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/15 20:52
 */

public class TinyFragment extends BaseFragment implements View.OnClickListener {

    private View homeview;

    @Override
    View onCrearteView() {
        homeview = View.inflate(getActivity(), R.layout.tinyfragment, null);
        return homeview;
    }

    @Override
    void initViewId() {
        Button wei = homeview.findViewById(R.id.weixin);
        Button qq = homeview.findViewById(R.id.qq);
        Button weipy = homeview.findViewById(R.id.weixinpengyou);
        wei.setOnClickListener(this);
        qq.setOnClickListener(this);
        weipy.setOnClickListener(this);
    }

    @Override
    void initData() {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){//View点击事件选择使用
            case R.id.weixin:
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
                intent.setComponent(componentName);
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/*");
                intent.putExtra(Intent.EXTRA_TEXT, "这是分享内容");
                intent.putExtra(Intent.EXTRA_STREAM, "http://www.weixin.com");
                startActivity(intent);
                break;
            case R.id.weixinpengyou:
                Intent intent3 = new Intent();
                ComponentName componentName3 = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                intent3.setComponent(componentName3);

                intent3.setAction(Intent.ACTION_SEND);
                //intent3.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));

                // intent.setAction(android.content.Intent.ACTION_SEND_MULTIPLE);
                // ArrayList<Uri> uris = new ArrayList<Uri>();
                // for (int i = 0; i < images.size(); i++) {
                // Uri data = Uri.fromFile(new File(thumbPaths.get(i)));
                // uris.add(data);
                // }
                // intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

                intent3.setType("image/*");

                startActivity(intent3);
                break;
            case R.id.qq:
                Intent intent2 = new Intent();
                ComponentName componentName2 = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
                intent2.setComponent(componentName2);
                intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/*");
                intent2.putExtra(Intent.EXTRA_TEXT, "这是分享内容");
                startActivity(intent2);
                break;
            default :
            break;
        }
    }
}
