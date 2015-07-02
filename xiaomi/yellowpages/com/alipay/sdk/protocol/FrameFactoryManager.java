package com.alipay.sdk.protocol;

import com.alipay.sdk.exception.AppErrorException;

public class FrameFactoryManager {
    public static FrameData a(FrameData frameData) {
        if (frameData == null) {
            throw new AppErrorException(FrameFactoryManager.class, "frame data is null");
        }
        MiniFrameFactory miniFrameFactory = new MiniFrameFactory();
        FrameData a = miniFrameFactory.a(frameData);
        if (a != null) {
            frameData = a;
        }
        miniFrameFactory.b(frameData);
        return frameData;
    }
}
