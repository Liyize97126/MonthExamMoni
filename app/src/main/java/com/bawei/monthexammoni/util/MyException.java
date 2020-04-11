package com.bawei.monthexammoni.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义异常类
 */
public class MyException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        //获取日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date(System.currentTimeMillis()));
        //获取系统版本号
        String osVersion = "API " + Build.VERSION.SDK_INT;
        //获取应用版本号
        int packageCode = packageCode(MyApplication.getContext());
        //获取应用版本名
        String packageName = packageName(MyApplication.getContext());
        //获取错误信息
        String message = e.toString();
        //Log日志
        Log.e("Tag","应用捕获了异常！\n" +
                "应用版本号：" + packageCode + "\n" +
                "应用版本名：" + packageName + "\n" +
                "系统版本号：" + osVersion + "\n" +
                "错误发生时间：" + time + "\n" +
                "错误信息：" + message + "\n" +
                "错误详情：\n");
        e.printStackTrace();
        System.exit(1);
    }
    //获取应用版本号
    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
    //获取应用版本名
    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "读取异常！";
        }
    }
}
