package com.tydfd.tydfdokhttp.util;


import com.tydfd.tydfdokhttp.http.UserBean;

/**
 * @author liudo
 */
public interface IHttpRequest {
    /**
     * 封装请求接口
     * @param url
     */
    void setUrl(String url);

    /**
     * 设置数据
     * @param data
     */
    void setData(byte[] data);

    /**
     * 设置回调
     * @param callbackListener
     */
    void setListener(CallbackListener callbackListener);

    /**
     * 执行线程
     */
    void execute(UserBean userBean);



}
