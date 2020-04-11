package com.bawei.monthexammoni.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.monthexammoni.contact.IContact;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 网络工具类
 */
public class VolleyUtil {
    //定义
    private RequestQueue queue = Volley.newRequestQueue(MyApplication.getContext());
    //单例模式
    private static final VolleyUtil VOLLEY_UTIL = new VolleyUtil();
    private VolleyUtil() {
    }
    public static VolleyUtil getVolleyUtil() {
        return VOLLEY_UTIL;
    }
    //网络判断方法
    public boolean hasNet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
            return true;
        }
        return false;
    }
    //封装请求方法
    public void VolleyRequest(int method, String url, Map<String,String> params, IContact.IModel iModel){
        switch (method){
            case Request.Method.GET: {
                doGet(url,iModel);
            }break;
            case Request.Method.POST: {
                doPost(url,params,iModel);
            }break;
        }
    }
    //Get请求
    private void doGet(String url, final IContact.IModel iModel){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //反馈
                        Log.i("Tag","请求成功！\n" + response);
                        iModel.requestSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //反馈
                        Log.i("Tag","请求失败！\n" + error.getMessage());
                        iModel.requestError(error.getMessage());
                    }
                }) {
            //乱码处理
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //添加队列
        queue.add(stringRequest);
    }
    //Post请求
    private void doPost(String url, final Map<String,String> params, final IContact.IModel iModel){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //反馈
                        Log.i("Tag","请求成功！\n" + response);
                        iModel.requestSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //反馈
                        Log.i("Tag","请求失败！\n" + error.getMessage());
                        iModel.requestError(error.getMessage());
                    }
                }) {
            //添加参数
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
            //乱码处理
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //添加队列
        queue.add(stringRequest);
    }
}
