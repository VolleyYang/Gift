package com.yangshenglong.ok;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

/**
 * Created by VolleyYang on 16/12/20.
 */
public class OkHttpClient {
    public void setConnectTimeout(int i, TimeUnit seconds) {
    }

    public void setWriteTimeout(int i, TimeUnit seconds) {
    }

    public void setReadTimeout(int i, TimeUnit seconds) {
    }

    public void setCookieHandler(CookieManager cookieHandler) {
        this.cookieHan
        dler = cookieHandler;
    }
}
