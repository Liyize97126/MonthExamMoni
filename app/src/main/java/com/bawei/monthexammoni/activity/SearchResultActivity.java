package com.bawei.monthexammoni.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.bawei.monthexammoni.R;
import com.bawei.monthexammoni.adapter.GoodsSearchResultListAdapter;
import com.bawei.monthexammoni.base.BaseActivity;
import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.bean.SearchResultBean;
import com.bawei.monthexammoni.contact.IContact;
import com.bawei.monthexammoni.presenter.SearchPresenter;
import com.bawei.monthexammoni.util.MyApplication;
import com.bawei.monthexammoni.util.VolleyUtil;

/**
 * 搜索结果展示
 */
public class SearchResultActivity extends BaseActivity {
    //定义
    private EditText edit_search_text;
    private Button submit_search;
    private RecyclerView search_result_show;
    private GoodsSearchResultListAdapter goodsSearchResultListAdapter;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_result;
    }
    @Override
    protected BasePresenter getPresenter() {
        return new SearchPresenter(new IContact.IView() {
            @Override
            public void requestSuccess(String json) {
                //解析数据
                SearchResultBean searchResultBean = MyApplication.getGson().fromJson(json, SearchResultBean.class);
                //判断
                if(searchResultBean.getStatus().equals("0000")){
                    int size = searchResultBean.getResult().size();
                    //判断
                    if(size != 0){
                        Toast.makeText(SearchResultActivity.this, searchResultBean.getMessage() + "，找到" + size + "件商品", Toast.LENGTH_LONG).show();
                        goodsSearchResultListAdapter.getList().addAll(searchResultBean.getResult());
                        goodsSearchResultListAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(SearchResultActivity.this, searchResultBean.getMessage() + "，未找到任何商品", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SearchResultActivity.this, searchResultBean.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void requestError(String error) {
                Toast.makeText(SearchResultActivity.this, "参数错误！", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void initView() {
        //获取ID
        edit_search_text = findViewById(R.id.edit_search_text);
        submit_search = findViewById(R.id.submit_search);
        search_result_show = findViewById(R.id.search_result_show);
        //设置Recyclerview
        search_result_show.setLayoutManager(new GridLayoutManager(this,2));
        //设置适配器
        goodsSearchResultListAdapter = new GoodsSearchResultListAdapter();
        search_result_show.setAdapter(goodsSearchResultListAdapter);
        //点击事件
        goodsSearchResultListAdapter.setOnGoodsSearchResultListener(new GoodsSearchResultListAdapter.OnGoodsSearchResultListener() {
            @Override
            public void goodsSearchResult() {
                //跳转至WebViewActivity
                Intent intent = new Intent(SearchResultActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
        //初次加载，发起请求
        String searchText = getIntent().getStringExtra("search_text");
        edit_search_text.setText(searchText);
        searchStart(searchText);
        //点击事件
        submit_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的内容
                String searchText = edit_search_text.getText().toString();
                //判断
                if(searchText.isEmpty()){
                    Toast.makeText(SearchResultActivity.this,"搜索框不能为空！",Toast.LENGTH_LONG).show();
                } else {
                    //发起搜索
                    searchStart(searchText);
                }
            }
        });
    }
    //发起搜索方法
    private void searchStart(String searchText){
        //网络判断
        if(VolleyUtil.getVolleyUtil().hasNet()){
            goodsSearchResultListAdapter.getList().clear();
            getPresenter().request(Request.Method.GET,null,searchText,"1","30");
        } else {
            Toast.makeText(SearchResultActivity.this, "找不到网络 (ㄒoㄒ)", Toast.LENGTH_LONG).show();
        }
    }
}
