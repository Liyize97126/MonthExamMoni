package com.bawei.monthexammoni.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.monthexammoni.R;

/**
 * 流式布局（搜索历史）
 */
public class SearchHistoryShowView extends FrameLayout {
    //定义接口回调
    private OnSearchHistoryViewListener onSearchHistoryViewListener;
    public void setOnSearchHistoryViewListener(OnSearchHistoryViewListener onSearchHistoryViewListener) {
        this.onSearchHistoryViewListener = onSearchHistoryViewListener;
    }
    //三个构造
    public SearchHistoryShowView(@NonNull Context context) {
        super(context);
    }
    public SearchHistoryShowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public SearchHistoryShowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //位置方法
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //定义以下参数
        int width = getWidth();
        int sumWidth = 0;
        int lines = 0;
        //开始循环布局
        for (int i = 0; i < getChildCount(); i++) {
            //获取到子布局
            View view = getChildAt(i);
            //预估一下：总宽度加上子控件的宽度是否大于父控件宽度
            if(sumWidth + view.getWidth() > width){
                //重置参数（换行）
                lines++;
                sumWidth = 0;
            }
            //完成布局
            view.layout(sumWidth,lines*view.getHeight(),sumWidth+view.getWidth(),lines*view.getHeight()+view.getHeight());
            sumWidth += view.getWidth();
        }
    }
    //文本添加方法
    public void addText(String text){
        //定义控件
        TextView textView = (TextView) View.inflate(getContext(), R.layout.text_item, null);
        //设置文本
        textView.setText(text);
        //发送值
        textView.setTag(text);
        //点击事件
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本
                String tag = (String) v.getTag();
                if(onSearchHistoryViewListener != null && !tag.isEmpty()){
                    onSearchHistoryViewListener.searchHistoryView(tag);
                }
            }
        });
        //添加视图
        addView(textView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    //监听接口
    public interface OnSearchHistoryViewListener{
        void searchHistoryView(String searchText);
    }
}
