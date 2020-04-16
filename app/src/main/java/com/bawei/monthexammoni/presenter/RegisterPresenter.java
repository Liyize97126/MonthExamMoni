package com.bawei.monthexammoni.presenter;

import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.contact.IContact;

/**
 * 注册Presenter
 */
public class RegisterPresenter extends BasePresenter {
    //方法实现
    public RegisterPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/user/v1/register";
    }
}
