package com.bawei.monthexammoni.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.monthexammoni.R;
import com.bawei.monthexammoni.base.BaseActivity;
import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.view.SearchHistoryShowView;

/**
 * 搜索界面
 */
public class SearchActivity extends BaseActivity {
    //定义
    private EditText edit_search_text;
    private Button submit_search;
    private SearchHistoryShowView search_history_show;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initView() {
        //获取ID
        edit_search_text = findViewById(R.id.edit_search_text);
        submit_search = findViewById(R.id.submit_search);
        search_history_show = findViewById(R.id.search_history_show);
        //监听事件
        search_history_show.setOnSearchHistoryViewListener(new SearchHistoryShowView.OnSearchHistoryViewListener() {
            @Override
            public void searchHistoryView(String searchText) {
                //调用统一跳转方法
                searchStart(searchText);
            }
        });
        //点击事件
        submit_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取搜索内容
                String searchText = edit_search_text.getText().toString();
                //判断是否非空
                if(searchText.isEmpty()){
                    Toast.makeText(SearchActivity.this,"搜索框不能为空！",Toast.LENGTH_LONG).show();
                } else {
                    //调用添加方法
                    search_history_show.addText(searchText);
                    edit_search_text.setText("");
                    //调用统一跳转方法
                    searchStart(searchText);
                }
            }
        });
    }
    //统一跳转方法
    private void searchStart(String searchText){
        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
        intent.putExtra("search_text",searchText);
        startActivity(intent);
    }
}
