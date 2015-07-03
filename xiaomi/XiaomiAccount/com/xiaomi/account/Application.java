package com.xiaomi.account;

import miui.external.ApplicationDelegate;

public class Application extends miui.external.Application {
    public ApplicationDelegate onCreateApplicationDelegate() {
        return new XiaomiAccountAppDelegate();
    }
}
