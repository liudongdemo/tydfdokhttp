package com.tydfd.tydfdokhttp.http;


import com.tydfd.tydfdokhttp.util.CallbackListener;
import com.tydfd.tydfdokhttp.util.HttpTask;
import com.tydfd.tydfdokhttp.util.IHttpRequest;
import com.tydfd.tydfdokhttp.util.IJsonDataListener;
import com.tydfd.tydfdokhttp.util.JsonCallbackListener;
import com.tydfd.tydfdokhttp.util.JsonHttpRequest;
import com.tydfd.tydfdokhttp.util.ThreadPoolManager;

/**
 * @author liudo
 */
public class NeOkHttp {

    public static<T,M> void sendJsonRequest(T requestData, String url,UserBean userBean,
                                            Class<M> response, IJsonDataListener listener){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallbackListener callbackListener = new JsonCallbackListener<>(response,listener);
        HttpTask httpTask = new HttpTask(requestData,url,userBean,httpRequest,callbackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }


}
