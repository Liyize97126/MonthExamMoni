package com.bawei.monthexammoni.presenter;

import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.contact.IContact;

/**
 * 搜索Presenter
 */
public class SearchPresenter extends BasePresenter {
    //方法实现
    public SearchPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword";
    }
}
