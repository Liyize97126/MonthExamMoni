package com.bawei.monthexammoni.activity;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.monthexammoni.R;
import com.bawei.monthexammoni.base.BaseActivity;
import com.bawei.monthexammoni.base.BasePresenter;

/**
 * WebView页面展示
 */
public class WebViewActivity extends BaseActivity {
    //定义
    private WebView webv;
    private EditText edit_text;
    private Button submit_edit;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initView() {
        //获取ID
        webv = findViewById(R.id.webv);
        edit_text = findViewById(R.id.edit_text);
        submit_edit = findViewById(R.id.submit_edit);
        //开始加载网页
        webv.loadUrl("file:///android_asset/shop.html");
        //设置Js
        webv.getSettings().setJavaScriptEnabled(true);
        webv.setWebChromeClient(new WebChromeClient());
        //设置本应用显示
        webv.setWebViewClient(new WebViewClient());
        //把本类对象传递给js，在js中名字叫android
        webv.addJavascriptInterface(this,"android");
        //按钮点击
        submit_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本
                String text = edit_text.getText().toString();
                //判断并加载JS
                if(text.isEmpty()){
                    webv.loadUrl("javascript:titleNull()");
                } else {
                    webv.loadUrl("javascript:changeTitle('" + text + "')");
                }
            }
        });
    }
    //Android调用Js（需要加注解）
    @JavascriptInterface
    public void buy(String id){
        Toast.makeText(this,id + "   购买成功！",Toast.LENGTH_LONG).show();
    }
}
