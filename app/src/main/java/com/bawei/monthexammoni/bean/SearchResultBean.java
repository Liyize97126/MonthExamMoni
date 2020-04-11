package com.bawei.monthexammoni.bean;

import java.io.Serializable;
import java.util.List;

/**
 * SearchResult顶层Bean类
 */
public class SearchResultBean implements Serializable {
    private List<GoodsBean> result;
    private String message;
    private String status;
    public List<GoodsBean> getResult() {
        return result;
    }
    public void setResult(List<GoodsBean> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
