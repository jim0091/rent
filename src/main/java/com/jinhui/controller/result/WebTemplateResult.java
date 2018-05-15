package com.jinhui.controller.result;

import com.jinhui.controller.base.WebResult;

/**
 * Created by jinhui on 2018/4/9.
 */
public class WebTemplateResult<T> extends WebResult {

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
