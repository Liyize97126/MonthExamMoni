package com.bawei.monthexammoni.presenter;

import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.contact.IContact;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 搜索Presenter
 */
public class SearchPresenter extends BasePresenter {
    //方法实现
    public SearchPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected String getUrl(String... args) {
        //定义一个字符串
        String searchText = null;
        //给汉字进行编码
        try {
            searchText = URLEncoder.encode(args[0], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //拼接链接
        return "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword?keyword=" + searchText + "&page=" + args[1] + "&count=" + args[2];
    }
}
