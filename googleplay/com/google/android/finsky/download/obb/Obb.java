package com.google.android.finsky.download.obb;

import java.io.File;

public interface Obb {
    boolean finalizeTempFile();

    File getFile();

    String getPackage();

    long getSize();

    int getState();

    File getTempFile();

    String getUrl();

    boolean isPatch();

    void syncStateWithStorage();
}
