package com.example.lenovo.congyunlong20170815.view.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.congyunlong20170815.R;
import com.example.lenovo.congyunlong20170815.modle.bean.HomeFragmentBean;
import com.example.lenovo.congyunlong20170815.modle.utils.ToastUtils;
import com.example.lenovo.congyunlong20170815.modle.utils.Url;
import com.example.lenovo.congyunlong20170815.presenter.HomeFragmentPresenter.HomeFragemtview;
import com.example.lenovo.congyunlong20170815.presenter.HomeFragmentPresenter.HomeFragmentPresenter;
import com.example.lenovo.congyunlong20170815.presenter.HomeFragmentPresenter.HomeView;
import com.example.lenovo.congyunlong20170815.view.adapter.homeadapter.HomeAdapterView;
import com.example.lenovo.congyunlong20170815.view.adapter.homeadapter.HomeRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.homhomlib.view2.DivergeView;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/15 20:52
 */

public class HomeFragment extends BaseFragment implements HomeView, HomeFragemtview {
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.home_fragment_recycler)
    XRecyclerView homeFragmentRecycler;
    @BindView(R.id.home_cardview_name)
    TextView homeCardviewName;
    @BindView(R.id.home_cardview_age)
    TextView homeCardviewAge;
    private ImageView homeCardviewImage;
    private TextView homeCardviewMprofession;
    private TextView homeCardviewIntroduce;
    private CardView homeTypeCardview;
    private int page = 1;
    private View homeview;
    private ImageView homeTypeImage;
    private ImageView homeAddImage;
    private HomeRecyclerAdapter recyclerAdapter;
    private HomeFragmentPresenter homeFragmentPresenter;
    private List<HomeFragmentBean.DataBean> list;
    private int po = 0;
    /**
     * 点赞
     */
    private DivergeView mDivergeView;
    private ImageView mImageView;
    private ArrayList<Bitmap> mList;
    private int mIndex = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String str = (String) msg.obj;
                Gson gson = new Gson();
                HomeFragmentBean homeFragmentBean = gson.fromJson(str, HomeFragmentBean.class);
                list.addAll(homeFragmentBean.getData());
                //设置初始值数据
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(getActivity()).load(list.get(po).getUserImg()).apply(requestOptions).into(homeTypeImage);
                Glide.with(getActivity()).load(list.get(po).getUserImg()).apply(requestOptions).into(homeCardviewImage);
                collapsingToolbarLayout.setTitle("详情:" + list.get(po).getUserName());
                homeCardviewName.setText(list.get(po).getUserName());
                homeCardviewAge.setText(list.get(po).getUserAge()+"");
                homeCardviewMprofession.setText(list.get(po).getOccupation());
                homeCardviewIntroduce.setText(list.get(po).getIntroduction());
                recyclerAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    View onCrearteView() {
        homeview = View.inflate(getActivity(), R.layout.homefragment, null);
        return homeview;
    }

    @Override
    void initViewId() {
        //初始化ButterKnife
        ButterKnife.bind(getActivity());
        collapsingToolbarLayout = homeview.findViewById(R.id.collapsing_toolbar_layout);
        homeFragmentRecycler = homeview.findViewById(R.id.home_fragment_recycler);
    }

    @Override
    void initData() {
        mList = new ArrayList<>();
        list = new ArrayList<>();
        //P层对象
        homeFragmentPresenter = new HomeFragmentPresenter(getActivity());
        homeFragmentPresenter.OnWifi(this);
        homeFragmentPresenter.setFragemtview(this);
        homeTypeImage = homeview.findViewById(R.id.home_type_image);
        homeAddImage = homeview.findViewById(R.id.home_add_image);
        homeTypeCardview = homeview.findViewById(R.id.home_type_cardview);
        homeCardviewImage = homeview.findViewById(R.id.home_cardview_image);
        homeCardviewMprofession = homeview.findViewById(R.id.home_cardview_mprofession);
        homeCardviewAge = homeview.findViewById(R.id.home_cardview_age);
        homeCardviewName = homeview.findViewById(R.id.home_cardview_name);
        homeCardviewIntroduce = homeview.findViewById(R.id.home_cardview_introduce);

        //点赞id
        mImageView = homeview.findViewById(R.id.iv_start);
        mDivergeView = homeview.findViewById(R.id.divergeView);
        //通过CollapsingToolbarLayout修改字体颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        //设置还没收缩时状态下字体颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //设置收缩后Toolbar上字体的颜色
        initDataDianZan();
        OninitDataImage();
        OnRecyclerView();
    }

    //点赞方法
    private void initDataDianZan() {
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.dz1, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(),R.drawable.dz2,null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(),R.drawable.dz3,null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(),R.drawable.dz4,null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(),R.drawable.dz5,null)).getBitmap());
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIndex == 5){
                    mIndex = 0 ;
                }
                mDivergeView.startDiverges(mIndex);
                mIndex++;
            }
        });
        mDivergeView.post(new Runnable() {
            @Override
            public void run() {
                mDivergeView.setEndPoint(new PointF(mDivergeView.getMeasuredWidth()/2,0));
                mDivergeView.setDivergeViewProvider(new Provider());
            }
        });
    }
//点击详情加号显示详情信息
    private void OninitDataImage() {
        homeAddImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                homeTypeImage.setVisibility(View.GONE);
                homeTypeCardview.setVisibility(View.VISIBLE);
                return false;
            }
        });
        //点击详情头像显示详情信息
        homeTypeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeTypeImage.setVisibility(View.GONE);
                homeTypeCardview.setVisibility(View.VISIBLE);
            }
        });
        homeTypeCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             homeTypeImage.setVisibility(View.VISIBLE);
             homeTypeCardview.setVisibility(View.GONE);
              }
            });

    }
    //RecyclerView初始化数据
    public void OnRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        homeFragmentRecycler.setLayoutManager(manager);
        recyclerAdapter = new HomeRecyclerAdapter(getActivity(), list);
        recyclerAdapter.setHomeAdapterView(new HomeAdapterView() {
            @Override
            public void OnHomeRecyclerLink(int pos) {
                po = pos;
                //设置圆形图片
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(getActivity())
                        .load(list.get(pos).getUserImg())
                        .apply(requestOptions)
                        //动画渐变加载
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(homeTypeImage);
                Glide.with(getActivity()).load(list.get(pos).getUserImg())
                        .apply(requestOptions)
                        //动画渐变加载
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(homeCardviewImage);
                collapsingToolbarLayout.setTitle("详情:" + list.get(pos).getUserName());
                homeCardviewName.setText(list.get(pos).getUserName());
                homeCardviewAge.setText(list.get(pos).getUserAge()+"");
                homeCardviewMprofession.setText(list.get(pos).getOccupation());
                homeCardviewIntroduce.setText(list.get(pos).getIntroduction());
            }
        });
        //添加上拉刷新下拉加载头尾布局
       // homeFragmentRecycler.addHeaderView(View.inflate(getActivity(),R.layout.recychome ,null), 50);
        homeFragmentRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                OnTrue();
                homeFragmentRecycler.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                OnTrue();
                homeFragmentRecycler.loadMoreComplete();
            }
        });

        //设置适配器
        homeFragmentRecycler.setAdapter(recyclerAdapter);
        //设置触摸时隐藏头部详情信息0
        homeFragmentRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                homeTypeImage.setVisibility(View.VISIBLE);
                homeTypeCardview.setVisibility(View.GONE);
                return false;
            }
        });
    }

    //判断网络成功回调
    @Override
    public void OnTrue() {
        homeFragmentPresenter.OnNetGet(Url.SHOM + page);
    }
   //判断网络失败回调
    @Override
    public void OnFales() {
        ToastUtils.getToas(getActivity(), "暂无网络");
    }

    @Override
    public void OnTrue(String str) {
        Message message = handler.obtainMessage();
        message.what = 0;
        message.obj = str;
        handler.sendMessage(message);
    }

    @Override
    public void OnFalse(int cod) {
        ToastUtils.getToas(getActivity(), cod + "");
    }

    //销毁生命周期清空动画集合长度
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mList != null){
            mList.clear();
            mList = null;
        }
    }
    //点赞动画方法
    class Provider implements DivergeView.DivergeViewProvider{

        @Override
        public Bitmap getBitmap(Object obj) {
            return mList == null ? null : mList.get((int)obj);
        }
    }
}
