package com.miui.yellowpage.a;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: RequestLoader */
public interface c {
    BaseResult onParseRequest(int i, Object obj, BaseResult baseResult);

    void onPreRequest(int i);

    void onRequestFinished(int i, BaseResult baseResult);
}
