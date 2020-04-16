package com.bawei.monthexammoni.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity页面基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义
    private BasePresenter basePresenter;
    public BasePresenter getBasePresenter() {
        return basePresenter;
    }
    private ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //视图设置
        setContentView(getLayoutId());
        //页面效果
        actionBar = getSupportActionBar();
        actionBar.hide();
        //Presenter实例化
        basePresenter = initPresenter();
        //处理视图数据
        initView();
    }
    //方法封装
    protected abstract int getLayoutId();
    protected abstract BasePresenter initPresenter();
    protected abstract void initView();
    //页面销毁方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if(basePresenter != null){
            basePresenter.destroy();
            basePresenter = null;
        }
    }
}
