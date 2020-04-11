package com.bawei.monthexammoni.contact;

/**
 * 契约类接口
 */
public interface IContact {
    interface IView{
        void requestSuccess(String json);
        void requestError(String error);
    }
    interface IModel{
        void requestSuccess(String json);
        void requestError(String error);
    }
}
