package com.tydfd.tydfdokhttp.util;

public interface IJsonDataListener<T> {

    /**
     * 请求成功
     * @param m
     */
    void onSuccess(T m);

    /**
     * 请求失败
     */
    void onFailure();

}
