package com.example.lenovo.congyunlong20170815.view.adapter.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.congyunlong20170815.R;
import com.example.lenovo.congyunlong20170815.modle.bean.HomeFragmentBean;

import java.util.List;

import static com.example.lenovo.congyunlong20170815.R.id.home_recycler_mimage;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/28 16:27
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> implements View.OnClickListener {
    private HomeAdapterView homeAdapterView;
    private Context context;
    private List<HomeFragmentBean.DataBean> list;

    public void setHomeAdapterView(HomeAdapterView homeAdapterView) {
        this.homeAdapterView = homeAdapterView;
    }

    public HomeRecyclerAdapter(Context context, List<HomeFragmentBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycleritem, parent, false);
        view.setOnClickListener(this);
        ViewHolder viewHolder =  new ViewHolder(view,viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      RequestOptions requestOptions = RequestOptions.circleCropTransform();
      Glide.with(context).load(list.get(position).getUserImg()).apply(requestOptions).into(holder.homeRecyclerMimage);
      holder.homeRecyclerMname.setText(list.get(position).getUserName());
      holder.homeRecyclerMage.setText(list.get(position).getUserAge()+"");
      holder.homeRecyclerMprofession.setText(list.get(position).getOccupation());
      holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (homeAdapterView!=null){
            homeAdapterView.OnHomeRecyclerLink((Integer) view.getTag());
        }

    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView homeRecyclerMimage;
        private  TextView homeRecyclerMname;
        private TextView homeRecyclerMage;
        private TextView homeRecyclerMprofession;
        public ViewHolder(View itemView,int type) {
            super(itemView);
            homeRecyclerMimage = itemView.findViewById(home_recycler_mimage);
            homeRecyclerMname = itemView.findViewById(R.id.home_recycler_mname);
            homeRecyclerMage = itemView.findViewById(R.id.home_recycler_mage);
            homeRecyclerMprofession = itemView.findViewById(R.id.home_recycler_mprofession);
        }

    }

}
