package com.xiaomi.kenai.jbosh;

import android.content.Context;

interface HTTPSender {
    void destroy();

    void init(BOSHClientConfig bOSHClientConfig);

    HTTPResponse send(CMSessionParams cMSessionParams, AbstractBody abstractBody, Context context);
}
