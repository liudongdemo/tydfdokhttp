package com.tydfd.tydfdokhttp.util;



import com.alibaba.fastjson.JSON;
import com.tydfd.tydfdokhttp.http.UserBean;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liudo
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest mIHttpRequest;
    private UserBean mUserBean;

    public HttpTask(T requestData, String url, UserBean userBean,IHttpRequest httpRequest, CallbackListener callbackListener){
        this.mIHttpRequest = httpRequest;
        this.mUserBean = userBean;
        httpRequest.setUrl(url);
        httpRequest.setListener(callbackListener);
        String content = JSON.toJSONString(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            mIHttpRequest.execute(mUserBean);
        }catch (Exception e){
            /**
             * 将失败的任务添加到重试队列中
             */
            ThreadPoolManager.getInstance().addDelayTask(this);
        }

    }

    private long delayTime;
    private int retryCount;

    public int getRetryCount(){
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        // 设置延迟时间  3000
        this.delayTime = System.currentTimeMillis()+delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
