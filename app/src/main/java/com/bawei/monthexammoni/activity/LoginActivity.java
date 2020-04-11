package com.bawei.monthexammoni.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.bawei.monthexammoni.R;
import com.bawei.monthexammoni.base.BaseActivity;
import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.bean.LoginBean;
import com.bawei.monthexammoni.contact.IContact;
import com.bawei.monthexammoni.presenter.LoginPresenter;
import com.bawei.monthexammoni.util.MyApplication;
import com.bawei.monthexammoni.util.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录页
 */
public class LoginActivity extends BaseActivity {
    private EditText edit_phone,edit_pass;
    private Button register_do,login_do;
    private Map<String,String> params;
    private LinearLayout except;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    protected BasePresenter getPresenter() {
        return new LoginPresenter(new IContact.IView() {
            @Override
            public void requestSuccess(String json) {
                LoginBean loginBean = MyApplication.getGson().fromJson(json, LoginBean.class);
                Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_LONG).show();
                //判断标识
                if(loginBean.getStatus().equals("0000")){
                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void requestError(String error) {
                Toast.makeText(LoginActivity.this, "参数错误！", Toast.LENGTH_LONG).show();
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
        except = findViewById(R.id.except);
        //点击事件
        register_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,100);
            }
        });
        login_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断网络
                if(VolleyUtil.getVolleyUtil().hasNet()){
                    //获取文本框内容
                    String phone = edit_phone.getText().toString();
                    String pwd = edit_pass.getText().toString();
                    //判断是否非空
                    if(phone.isEmpty() || pwd.isEmpty()){
                        Toast.makeText(LoginActivity.this, "请输入合法的用户名和密码！", Toast.LENGTH_LONG).show();
                    } else {
                        //设置Map集合
                        params = new HashMap<>();
                        params.put("phone",phone);
                        params.put("pwd",pwd);
                        //发起请求
                        getPresenter().request(Request.Method.POST,params);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "找不到网络 (ㄒoㄒ)", Toast.LENGTH_LONG).show();
                }
            }
        });
        //模拟异常
        except.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1/0;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //结果判断
        if(requestCode == 100 && resultCode == 200){
            //获取并设置值
            String phone = data.getStringExtra("phone");
            String pwd = data.getStringExtra("pwd");
            edit_phone.setText(phone);
            edit_pass.setText(pwd);
        }
    }
}
