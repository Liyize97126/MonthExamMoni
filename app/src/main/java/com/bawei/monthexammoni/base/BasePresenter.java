package com.bawei.monthexammoni.base;

import android.util.Log;

import com.android.volley.Request;
import com.bawei.monthexammoni.contact.IContact;
import com.bawei.monthexammoni.util.VolleyUtil;

import java.util.Map;

/**
 * Presenter基类
 */
public abstract class BasePresenter {
    //定义接口
    private IContact.IView iView;
    //构造
    public BasePresenter(IContact.IView iView) {
        this.iView = iView;
    }
    //提供请求方法
    public void request(int method, Map<String,String> params){
        if(method == Request.Method.GET){
            Log.i("Tag","执行了GET请求！");
        } else {
            Log.i("Tag","执行了POST请求！");
        }
        VolleyUtil.getVolleyUtil().VolleyRequest(method, getUrl(), params, new IContact.IModel() {
            @Override
            public void requestSuccess(String json) {
                iView.requestSuccess(json);
            }
            @Override
            public void requestError(String error) {
                iView.requestError(error);
            }
        });
    }
    //方法封装
    protected abstract String getUrl();
    //释放资源
    public void destroy(){
        if(iView != null){
            iView = null;
        }
    }
}
