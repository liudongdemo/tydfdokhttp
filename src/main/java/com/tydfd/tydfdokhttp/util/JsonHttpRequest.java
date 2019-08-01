package com.tydfd.tydfdokhttp.util;


import android.util.Base64;


import com.tydfd.tydfdokhttp.http.UserBean;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author liudo
 */
public class JsonHttpRequest implements IHttpRequest{

    private String url;
    private byte[] data;
    private CallbackListener mCallbackListener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallbackListener callbackListener) {
        this.mCallbackListener = callbackListener;
    }

    @Override
    public void execute(UserBean userBean) {
        URL url = null;
        try {
            url = new URL(this.url);
            /**
             * 打开http连接
             */
            urlConnection = (HttpURLConnection) url.openConnection();
            /**
             * 连接的超时时间
             */
            urlConnection.setConnectTimeout(6000);
            /**
             * 不使用缓存
             */
            urlConnection.setUseCaches(false);
            /**
             * 是成员函数，仅作用于当前函数,设置这个连接是否可以被重定向
             */
            urlConnection.setInstanceFollowRedirects(true);
            /**
             * 响应的超时时间
             */
            urlConnection.setReadTimeout(3000);
            /**
             * 设置这个连接是否可以写入数据
             */
            urlConnection.setDoInput(true);
            /**
             * 设置这个连接是否可以输出数据
             */
            urlConnection.setDoOutput(true);
            /**
             * 设置请求的方式
             */
            if(data==null){
                urlConnection.setRequestMethod("GET");
            }else {
                urlConnection.setRequestMethod("POST");
            }
            /**
             * 设置Basic认证
             */
            if(userBean!=null||userBean.getUsername()!= ""||userBean.getPassword()!=""){
            String userMsg = userBean.getUsername() + ":" + userBean.getPassword();
            String base64UserMsg = Base64.encodeToString(userMsg.getBytes(),Base64.DEFAULT);
            final String tokenStr = "Basic " + base64UserMsg;
            urlConnection.addRequestProperty("Authorization", tokenStr);

            }


            /**
             * 设置消息的类型
             */
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            /**
             * 连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接
             */
            urlConnection.connect();
            /**
             * -------------使用字节流发送数据--------------
             */
            OutputStream out = urlConnection.getOutputStream();
            /**
             * 缓冲字节流包装字节流
             */
            BufferedOutputStream bos = new BufferedOutputStream(out);
            /**
             * 把这个字节数组的数据写入缓冲区中
             */
            bos.write(data);
            /**
             * 刷新缓冲区，发送数据
             */
            bos.flush();
            out.close();
            bos.close();
            /**
             * ------------字符流写入数据------------
             */

            /**
             * 得到服务端的返回码是否连接成功
             */
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                mCallbackListener.onSuccess(in);
            }else{
                // 访问失败，重试
                throw new RuntimeException("请求失败");
            }
    }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }finally{
            urlConnection.disconnect();
        }
    }



}
