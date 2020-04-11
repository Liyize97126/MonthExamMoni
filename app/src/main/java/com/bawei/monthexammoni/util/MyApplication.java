package com.bawei.monthexammoni.util;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

/**
 * 全局Context和Gson
 */
public class MyApplication extends Application {
    private static Context context;
    private static Gson gson;
    public static Context getContext() {
        return context;
    }
    public static Gson getGson() {
        return gson;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        gson = new Gson();
        //注册自定义异常捕获
        Thread.setDefaultUncaughtExceptionHandler(new MyException());
    }
}
