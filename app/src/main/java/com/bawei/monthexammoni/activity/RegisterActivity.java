package com.bawei.monthexammoni.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.bawei.monthexammoni.R;
import com.bawei.monthexammoni.base.BaseActivity;
import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.bean.RegisterBean;
import com.bawei.monthexammoni.contact.IContact;
import com.bawei.monthexammoni.presenter.RegisterPresenter;
import com.bawei.monthexammoni.util.MyApplication;
import com.bawei.monthexammoni.util.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {
    private EditText edit_phone,edit_pass;
    private Button register_do,login_do;
    private Map<String,String> params;
    private String phone,pwd;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
    @Override
    protected BasePresenter getPresenter() {
        return new RegisterPresenter(new IContact.IView() {
            @Override
            public void requestSuccess(String json) {
                RegisterBean registerBean = MyApplication.getGson().fromJson(json, RegisterBean.class);
                Toast.makeText(RegisterActivity.this, registerBean.getMessage(), Toast.LENGTH_LONG).show();
                //判断标识
                if(registerBean.getStatus().equals("0000")){
                    Intent intentfan = new Intent();
                    intentfan.putExtra("phone",phone);
                    intentfan.putExtra("pwd",pwd);
                    setResult(200,intentfan);
                    finish();
                }
            }
            @Override
            public void requestError(String error) {
                Toast.makeText(RegisterActivity.this, "参数错误！", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void initView() {
        //获取id
        edit_phone = findViewById(R.id.edit_phone);
        edit_pass = findViewById(R.id.edit_pass);
        register_do = findViewById(R.id.register_do);
        login_do = findViewById(R.id.login_do);
        //点击事件
        register_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断网络
                if(VolleyUtil.getVolleyUtil().hasNet()){
                    //获取文本框内容
                    phone = edit_phone.getText().toString();
                    pwd = edit_pass.getText().toString();
                    //判断是否非空
                    if(phone.isEmpty() || pwd.isEmpty()){
                        Toast.makeText(RegisterActivity.this, "请输入合法的用户名和密码！", Toast.LENGTH_LONG).show();
                    } else {
                        //设置Map集合
                        params = new HashMap<>();
                        params.put("phone",phone);
                        params.put("pwd",pwd);
                        //发起请求
                        getPresenter().request(Request.Method.POST,params);
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "找不到网络 (ㄒoㄒ)", Toast.LENGTH_LONG).show();
                }
            }
        });
        login_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断网络
                if(VolleyUtil.getVolleyUtil().hasNet()){
                    //获取文本框内容
                    phone = edit_phone.getText().toString();
                    pwd = edit_pass.getText().toString();
                    //判断是否非空
                    if(phone.isEmpty() || pwd.isEmpty()){
                        Toast.makeText(RegisterActivity.this, "请输入合法的用户名和密码！", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "请完成注册操作后再登录！", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "找不到网络 (ㄒoㄒ)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
