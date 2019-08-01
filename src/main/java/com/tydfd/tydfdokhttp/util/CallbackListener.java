package com.tydfd.tydfdokhttp.util;

import java.io.InputStream;

public interface CallbackListener {

    void onSuccess(InputStream inputStream);

    void onFailure();

}
