package com.bawei.monthexammoni.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.monthexammoni.R;
import com.bawei.monthexammoni.bean.GoodsBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器
 */
public class GoodsSearchResultListAdapter extends RecyclerView.Adapter<GoodsSearchResultListAdapter.MyHouler> {
    //定义集合
    private List<GoodsBean> list = new ArrayList<>();
    public List<GoodsBean> getList() {
        return list;
    }
    //定义监听
    private OnGoodsSearchResultListener onGoodsSearchResultListener;
    public void setOnGoodsSearchResultListener(OnGoodsSearchResultListener onGoodsSearchResultListener) {
        this.onGoodsSearchResultListener = onGoodsSearchResultListener;
    }
    //方法实现
    @NonNull
    @Override
    public GoodsSearchResultListAdapter.MyHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content, parent, false);
        return new MyHouler(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull GoodsSearchResultListAdapter.MyHouler holder, int position) {
        //获取数据
        GoodsBean goodsBean = list.get(position);
        //设置文本
        holder.commodityName.setText(goodsBean.getCommodityName());
        holder.price.setText("￥" + goodsBean.getPrice());
        //Glide配置
        RequestOptions requestOptions = new RequestOptions()
                .fallback(R.drawable.zhan_pict)
                .error(R.drawable.error_zhan)
                .placeholder(R.drawable.zhan_pict)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)));
        //Glide加载图片
        Glide.with(holder.masterPic.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(goodsBean.getMasterPic())
                .into(holder.masterPic);
        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断
                if(onGoodsSearchResultListener != null){
                    onGoodsSearchResultListener.goodsSearchResult();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //内部类（寄存器）
    public class MyHouler extends RecyclerView.ViewHolder {
        //定义
        protected ImageView masterPic;
        protected TextView commodityName,price;
        public MyHouler(@NonNull View itemView) {
            super(itemView);
            masterPic = itemView.findViewById(R.id.masterPic);
            commodityName = itemView.findViewById(R.id.commodityName);
            price = itemView.findViewById(R.id.price);
        }
    }
    //声明回调
    public interface OnGoodsSearchResultListener{
        void goodsSearchResult();
    }
}
