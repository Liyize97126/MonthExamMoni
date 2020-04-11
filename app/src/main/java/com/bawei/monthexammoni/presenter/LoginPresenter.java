package com.bawei.monthexammoni.presenter;

import com.bawei.monthexammoni.base.BasePresenter;
import com.bawei.monthexammoni.contact.IContact;

/**
 * 登录Presenter
 */
public class LoginPresenter extends BasePresenter {
    //方法实现
    public LoginPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected String getUrl(String... args) {
        return "http://mobile.bwstudent.com/small/user/v1/login";
    }
}
